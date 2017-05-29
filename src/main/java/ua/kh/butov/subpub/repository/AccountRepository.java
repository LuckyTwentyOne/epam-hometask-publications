package ua.kh.butov.subpub.repository;

import java.math.BigDecimal;
import java.util.List;

import ua.kh.butov.subpub.entity.Account;

public interface AccountRepository {
	
	int updateAccountBalance(BigDecimal newBalance, int accountId);
	
	Account findByEmail(String email);
	
	public void create(Account account);
	
	BigDecimal addMoneyToAccount(int accountId, int sumToAdd);
	
	List<Account> listAllAccounts(int page, int limit);
	
	int countAllAccounts();
	
	Account findById(int idAccount);
	
	boolean changeActiveStatus(int accountId);

}
