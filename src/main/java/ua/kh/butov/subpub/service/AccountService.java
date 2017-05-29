package ua.kh.butov.subpub.service;

import java.util.List;

import ua.kh.butov.subpub.entity.Account;
import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.form.LoginForm;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.model.SocialAccount;

public interface AccountService {

	CurrentAccount authentificate(SocialAccount socialAccount);
	
	CurrentAccount registrateAccount(Account account) throws ValidationException;
	
	CurrentAccount login(LoginForm form) throws ValidationException;
	
	void addMoneyToAccountByVaucher(CurrentAccount currentAccount, Long voucherCode) throws ValidationException;
	
	List<Account> listAllAccounts(int page, int limit);
	
	int countAllAccounts();
	
	void changeAccountStatus(int idAccount);
}
