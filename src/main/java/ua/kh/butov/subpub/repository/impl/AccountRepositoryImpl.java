package ua.kh.butov.subpub.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import ua.kh.butov.subpub.entity.Account;
import ua.kh.butov.subpub.factory.JDBCConnectionUtils;
import ua.kh.butov.subpub.handler.DefaultListResultSetHandler;
import ua.kh.butov.subpub.handler.DefaultUniqueResultSetHandler;
import ua.kh.butov.subpub.handler.IntResultSetHandler;
import ua.kh.butov.subpub.handler.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.repository.AccountRepository;

public class AccountRepositoryImpl implements AccountRepository {

	private final ResultSetHandler<Account> accountResultSetHandler = new DefaultUniqueResultSetHandler<>(
			Account.class);
	private final ResultSetHandler<List<Account>> accountsResultSetHandler = new DefaultListResultSetHandler<>(
			Account.class);
	private final ResultSetHandler<Integer> countResultSetHandler = new IntResultSetHandler();

	@Override
	public int updateAccountBalance(BigDecimal newBalance, int accountId) {
		return JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(), "update account set money=? where id=?",
				newBalance, accountId);
	}

	@Override
	public Account findByEmail(String email) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select a.*, r.name as role from account a, role r where a.id_role=r.id and a.email=?",
				accountResultSetHandler, email);
	}

	@Override
	public void create(Account account) {
		Account createdAccount = JDBCUtils.insert(JDBCConnectionUtils.getCurrentConnection(),
				"insert into account (id, first_name, last_name, email, password) values (nextval('account_seq'),?,?,?,?)",
				accountResultSetHandler, account.getFirstName(), account.getLastName(), account.getEmail(),
				account.getPassword());
		account.setId(createdAccount.getId());
	}

	@Override
	public BigDecimal addMoneyToAccount(int accountId, int sumToAdd) {
		JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(), "update account set money = money+? where id =?",
				sumToAdd, accountId);
		Account account = JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select a.*, r.name as role from account a, role r where a.id_role=r.id and a.id=?",
				accountResultSetHandler, accountId);
		return account.getMoney();
	}

	@Override
	public List<Account> listAllAccounts(int page, int limit) {
		int offset = (page - 1) * limit;
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select a.*, r.name as role from account a, role r where a.id_role=r.id and r.id =1 order by a.id limit ? offset ?",
				accountsResultSetHandler, limit , offset);
	}

	@Override
	public Account findById(int idAccount) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select a.*, r.name as role from account a, role r where a.id_role=r.id and a.id=?",
				accountResultSetHandler, idAccount);
	}

	@Override
	public boolean changeActiveStatus(int accountId) {
		Account account = findById(accountId);
		if(account.isActive()) {
			JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(), "update account set active=false where id=?", accountId);
			return false;
		}else {
			JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(), "update account set active=true where id=?", accountId);
			return true;
		}
	}
	
	@Override
	public int countAllAccounts() {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(), "select count(*) from account", countResultSetHandler);
	}

}
