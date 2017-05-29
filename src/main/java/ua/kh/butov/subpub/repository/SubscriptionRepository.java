package ua.kh.butov.subpub.repository;

import java.util.List;

import ua.kh.butov.subpub.entity.Subscription;
import ua.kh.butov.subpub.model.CurrentAccount;

public interface SubscriptionRepository {
	
	List<Subscription> listMySubscriptions(CurrentAccount currentAccount, int page, int limit);
	
	List<Subscription> listMySubscriptions(CurrentAccount currentAccount);
	
	int countMySubscriptions(CurrentAccount currentAccount);
	
	Subscription findById(long id, CurrentAccount currentAccount);
	
	public void createNewSubscription(Subscription subscription);

}
