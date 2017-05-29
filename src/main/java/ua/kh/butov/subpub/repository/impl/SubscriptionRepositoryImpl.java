package ua.kh.butov.subpub.repository.impl;

import java.util.List;

import ua.kh.butov.subpub.entity.Subscription;
import ua.kh.butov.subpub.factory.JDBCConnectionUtils;
import ua.kh.butov.subpub.handler.DefaultListResultSetHandler;
import ua.kh.butov.subpub.handler.DefaultUniqueResultSetHandler;
import ua.kh.butov.subpub.handler.IntResultSetHandler;
import ua.kh.butov.subpub.handler.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.repository.SubscriptionRepository;

public class SubscriptionRepositoryImpl implements SubscriptionRepository {
	
	private final ResultSetHandler<List<Subscription>> subscriptionsResultSetHandler = new DefaultListResultSetHandler<>(Subscription.class);
	private final ResultSetHandler<Subscription> subscriptionResultSetHandler = new DefaultUniqueResultSetHandler<>(Subscription.class);
	private final ResultSetHandler<Integer> countResultSetHandler = new IntResultSetHandler();

	@Override
	public List<Subscription> listMySubscriptions(CurrentAccount currentAccount, int page, int limit) {
		int offset = (page - 1) * limit;
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(), "select s.* from subscription s where s.id_account=? order by created desc limit ? offset ?",
				subscriptionsResultSetHandler, currentAccount.getId(), limit, offset);
	}
	
	@Override
	public List<Subscription> listMySubscriptions(CurrentAccount currentAccount) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(), "select s.* from subscription s where s.id_account=?",
				subscriptionsResultSetHandler, currentAccount.getId());
	}

	@Override
	public int countMySubscriptions(CurrentAccount currentAccount) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(), "select count(*) from subscription s where s.id_account=?", countResultSetHandler, currentAccount.getId());
	}

	@Override
	public Subscription findById(long id, CurrentAccount currentAccount) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(), "select * from subscription where id=?", subscriptionResultSetHandler, id);
	}

	@Override
	public void createNewSubscription(Subscription subscription) {
		Subscription createdSubscription = JDBCUtils.insert(JDBCConnectionUtils.getCurrentConnection(),
				"insert into subscription (id, id_account, expiration_date, id_publication) values (nextval('subscription_seq'),?,?,?)",
				subscriptionResultSetHandler, subscription.getIdAccount(), subscription.getExpirationDate(), subscription.getIdPublication());
		subscription.setId(createdSubscription.getId());
	}

}
