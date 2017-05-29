package ua.kh.butov.subpub.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.ValidationException;

import ua.kh.butov.subpub.annotation.jdbc.Transactional;
import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.entity.Subscription;
import ua.kh.butov.subpub.exception.AccessDeniedException;
import ua.kh.butov.subpub.exception.InternalServerErrorException;
import ua.kh.butov.subpub.exception.ResourceNotFoundException;
import ua.kh.butov.subpub.factory.TransactionSynchronization;
import ua.kh.butov.subpub.factory.TransactionSynchronizationManager;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.repository.AccountRepository;
import ua.kh.butov.subpub.repository.PublicationRepository;
import ua.kh.butov.subpub.repository.SubscriptionRepository;
import ua.kh.butov.subpub.service.NotificationContentBuilderService;
import ua.kh.butov.subpub.service.NotificationService;
import ua.kh.butov.subpub.service.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService {
	
	private final PublicationRepository publicationRepository;
	private final SubscriptionRepository subscriptionRepository;
	private final AccountRepository accountRepository;
	private final NotificationService notificationService;
	private final NotificationContentBuilderService notificationContentBuilderService;

	public SubscriptionServiceImpl(ServiceManager serviceManager) {		
		publicationRepository = serviceManager.publicationRepository;
		subscriptionRepository = serviceManager.subscriptionRepository;
		accountRepository = serviceManager.accountRepository;
		notificationService = serviceManager.getNotificationService();
		notificationContentBuilderService = serviceManager.getNotificationContentBuilderService();
	}
	
	@Override
	@Transactional
	public List<Subscription> listMySubscriptions(CurrentAccount currentAccount, int page, int limit) {
		return subscriptionRepository.listMySubscriptions(currentAccount, page, limit);
	}
	
	@Override
	@Transactional
	public int countMySubscriptions(CurrentAccount currentAccount) {
			return subscriptionRepository.countMySubscriptions(currentAccount);
	}

	@Override
	@Transactional(readOnly=false)
	public long makeSubscription(Publication publication, int numberOfMonthes, CurrentAccount account) {
		BigDecimal totalSum = publication.getPrice().multiply(BigDecimal.valueOf(numberOfMonthes));
		BigDecimal restAccountBalance = account.getMoney().subtract(totalSum);
		accountRepository.updateAccountBalance(restAccountBalance, account.getId());
		Subscription result = new Subscription(account.getId(), calculateExpirationDate(numberOfMonthes), publication.getId());
		subscriptionRepository.createNewSubscription(result);
		account.setMoney(restAccountBalance);
		TransactionSynchronizationManager.addSynchronization(new TransactionSynchronization() {	
			@Override
			public void afterCommit() {
				String content = notificationContentBuilderService.buildNewSubscriptionCreatedNotificationMessage(result);
				notificationService.sendNotificationMessage(account.getEmail(), content);
			}
		});
		return result.getId();
	}
	
	@Transactional
	@Override
	public void checkSubscriptionPosibility(Publication publication, int numberOfMonthes, CurrentAccount account) throws ValidationException {
		BigDecimal totalSum = publication.getPrice().multiply(BigDecimal.valueOf(numberOfMonthes));
		List<Subscription>accountSubscriptions = subscriptionRepository.listMySubscriptions(account);
		for(Subscription subscription: accountSubscriptions){
			if(subscription.getIdPublication().equals(publication.getId())){
				throw new ValidationException("subscription.request.alreadyExist");
			}
		}
		validateSubscriptionRequest(publication, numberOfMonthes, account, totalSum);
	}

	private void validateSubscriptionRequest(Publication publication, int numberOfMonthes, CurrentAccount account, BigDecimal totalSum) throws ValidationException {
		if (publication == null) {
			throw new InternalServerErrorException("Publication does't exist");
		}
		if (account == null) {
			throw new InternalServerErrorException("Account does't exist in the session");
		}
		if (numberOfMonthes < 1 || numberOfMonthes > 12) {
			throw new InternalServerErrorException("Number of monthes does't match to needed number");
		}
		if (account.getMoney().compareTo(totalSum) < 0){
			throw new ValidationException("subscription.request.notEnoughMoney");
		}
	}

	private Timestamp calculateExpirationDate(int numberOfMonthes) {
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.MONTH, numberOfMonthes);
		return new Timestamp(cl.getTimeInMillis());
	}

	@Override
	@Transactional
	public Subscription findById(long id, CurrentAccount currentAccount) {
		Subscription subscription = subscriptionRepository.findById(id, currentAccount);
		if (subscription == null) {
			throw new ResourceNotFoundException("Subscription not found by id: " + id);
		}
		if (!subscription.getIdAccount().equals(currentAccount.getId())) {
			throw new AccessDeniedException("Account with id=" + currentAccount.getId() + " is not owner for subscription with id=" + id);
		}
		subscription.setPublication(publicationRepository.findById(subscription.getIdPublication()));
		return subscription;
	}
}
