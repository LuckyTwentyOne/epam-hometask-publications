package ua.kh.butov.subpub.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.sql.DataSource;
import javax.xml.bind.ValidationException;

import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.entity.Subscription;
import ua.kh.butov.subpub.exception.InternalServerErrorException;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.jdbc.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.ResultSetHandlerFactory;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.service.I18nService;
import ua.kh.butov.subpub.service.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService {

	private static final ResultSetHandler<Subscription> subscriptionResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.SUBSCRIPTION_RESULT_SET_HANDLER);

	private final DataSource dataSource;
	private final I18nService i18nService;

	public SubscriptionServiceImpl(DataSource dataSource, I18nService i18nService) {
		super();
		this.dataSource = dataSource;
		this.i18nService = i18nService;
	}

	@Override
	public long makeSubscription(Publication publication, int numberOfMonthes, CurrentAccount account) throws ValidationException {
		BigDecimal totalSum = publication.getPrice().multiply(BigDecimal.valueOf(numberOfMonthes));
		validateSubscriptionRequest(publication, numberOfMonthes, account, totalSum);
		BigDecimal restAccountBalance = account.getMoney().subtract(totalSum);
		try (Connection c = dataSource.getConnection()) {
			JDBCUtils.update(c, "update account set money=? where id=?", restAccountBalance, account.getId());
			Subscription result = JDBCUtils.insert(c,
					"insert into subscription (id, id_account, expiration_date, id_publication) values (nextval('subscription_seq'),?,?,?)",
					subscriptionResultSetHandler, account.getId(), calculateExpirationDate(numberOfMonthes),
					publication.getId());
			c.commit();
			account.setMoney(restAccountBalance);
			return result.getId();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
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

}
