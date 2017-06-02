package ua.kh.butov.subpub.repository.impl;

import java.math.BigDecimal;

import ua.kh.butov.subpub.entity.AccountSubscriptionTotal;
import ua.kh.butov.subpub.factory.JDBCConnectionUtils;
import ua.kh.butov.subpub.handler.BigDecimalResultSetHandler;
import ua.kh.butov.subpub.handler.DefaultUniqueResultSetHandler;
import ua.kh.butov.subpub.handler.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.repository.AccountSubscriptionTotalRepository;

public class AccountSubscriptionTotalRepositoryImpl implements AccountSubscriptionTotalRepository {

	private final ResultSetHandler<AccountSubscriptionTotal> accountSubscriptionResultSetHandler = new DefaultUniqueResultSetHandler<>(
			AccountSubscriptionTotal.class);
	private final ResultSetHandler<BigDecimal> bigDecimalResultSetHandler = new BigDecimalResultSetHandler();

	@Override
	public AccountSubscriptionTotal findByIdSubscription(long idSubscription) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select * from account_subscription_total where id_subscription = ?",
				accountSubscriptionResultSetHandler, idSubscription);
	}

	@Override
	public AccountSubscriptionTotal addNewAccountSubscriptionStatistic(
			AccountSubscriptionTotal accountSubscriptionTotal) {
		return JDBCUtils.insert(JDBCConnectionUtils.getCurrentConnection(),
				"insert into account_subscription_total values(nextval('account_subscription_total_seq'),?,?,?)",
				accountSubscriptionResultSetHandler, accountSubscriptionTotal.getIdSubscription(),
				accountSubscriptionTotal.getTotalSum(), accountSubscriptionTotal.getIdAccount());
	}

	@Override
	public void updateAccountSubscriptionStatistic(AccountSubscriptionTotal accountSubscriptionTotal) {
		JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(),
				"update account_subscription_total set total_sum = ? where id_account=? and id_subscription=?",
				accountSubscriptionTotal.getTotalSum(), accountSubscriptionTotal.getIdAccount(),
				accountSubscriptionTotal.getIdSubscription());
	}

	@Override
	public BigDecimal getTotalSumForAccount(int idAccount) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select sum(total_sum)from account_subscription_total where id_account=?", bigDecimalResultSetHandler,
				idAccount);
	}

}
