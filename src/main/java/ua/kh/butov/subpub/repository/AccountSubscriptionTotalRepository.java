package ua.kh.butov.subpub.repository;

import java.math.BigDecimal;

import ua.kh.butov.subpub.entity.AccountSubscriptionTotal;

public interface AccountSubscriptionTotalRepository {
	
	AccountSubscriptionTotal findByIdSubscription(long idSubscription);
	
	AccountSubscriptionTotal addNewAccountSubscriptionStatistic(AccountSubscriptionTotal accountSubscriptionTotal);
	
	void updateAccountSubscriptionStatistic(AccountSubscriptionTotal accountSubscriptionTotal);
	
	BigDecimal getTotalSumForAccount(int idAccount);

}
