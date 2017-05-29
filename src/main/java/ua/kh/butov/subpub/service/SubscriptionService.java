package ua.kh.butov.subpub.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.entity.Subscription;
import ua.kh.butov.subpub.model.CurrentAccount;

public interface SubscriptionService {
	
	List<Subscription> listMySubscriptions(CurrentAccount currentAccount, int page, int limit);
	
	long makeSubscription(Publication publication, int numberOfMonthes, CurrentAccount account);
	
	int countMySubscriptions(CurrentAccount currentAccount);
	
	Subscription findById(long id, CurrentAccount currentAccount);
	
	public void checkSubscriptionPosibility(Publication publication, int numberOfMonthes, CurrentAccount account) throws ValidationException;

}
