package ua.kh.butov.subpub.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import ua.kh.butov.subpub.entity.Account;
import ua.kh.butov.subpub.exception.InternalServerErrorException;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.jdbc.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.ResultSetHandlerFactory;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.model.SocialAccount;
import ua.kh.butov.subpub.service.AccountService;

public class AccountServiceImpl implements AccountService {
	private static final ResultSetHandler<Account> accountResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.ACCOUNT_RESULT_SET_HANDLER);
	private final DataSource dataSource;

	public AccountServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public CurrentAccount authentificate(SocialAccount socialAccount) {
		try (Connection c = dataSource.getConnection()) {
			Account account = JDBCUtils.select(c, "select * from account where email=?",
					accountResultSetHandler, socialAccount.getEmail());
			if (account == null) {
				account = new Account(socialAccount.getFirstName(), socialAccount.getLastName(),
						socialAccount.getEmail());
				account = JDBCUtils.insert(c,
						"insert into account (id, first_name, last_name, email) values (nextval('account_seq'),?,?,?)",
						accountResultSetHandler, account.getFistName(), account.getLastName(), account.getEmail());
				c.commit();
			}
			return account;
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

}
