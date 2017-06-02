package ua.kh.butov.subpub.service.impl;

import java.math.BigDecimal;
import java.util.List;

import ua.kh.butov.subpub.annotation.jdbc.Transactional;
import ua.kh.butov.subpub.entity.Account;
import ua.kh.butov.subpub.entity.Voucher;
import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.form.LoginForm;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.model.SocialAccount;
import ua.kh.butov.subpub.repository.AccountRepository;
import ua.kh.butov.subpub.repository.AccountSubscriptionTotalRepository;
import ua.kh.butov.subpub.repository.VoucherRepository;
import ua.kh.butov.subpub.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final VoucherRepository vaucherRepository;
	private final AccountSubscriptionTotalRepository accountSubscriptionTotalRepository;

	public AccountServiceImpl(ServiceManager serviceManager) {
		accountRepository = serviceManager.accountRepository;
		vaucherRepository = serviceManager.vaucherRepository;
		accountSubscriptionTotalRepository = serviceManager.accountSubscriptionTotalRepository;
	}

	@Override
	@Transactional(readOnly = false)
	public CurrentAccount authentificate(SocialAccount socialAccount) {
		Account account = accountRepository.findByEmail(socialAccount.getEmail());
		if (account == null) {
			account = new Account(socialAccount.getFirstName(), socialAccount.getLastName(), socialAccount.getEmail());
			accountRepository.create(account);
		}
		return account;
	}

	@Override
	@Transactional(readOnly = false)
	public CurrentAccount registrateAccount(Account account) throws ValidationException {
		if (accountRepository.findByEmail(account.getEmail()) != null) {
			throw new ValidationException("registration.account.exist");
		}
		accountRepository.create(account);
		return account;
	}

	@Override
	@Transactional
	public CurrentAccount login(LoginForm form) throws ValidationException {
		Account account = accountRepository.findByEmail(form.getEmail());
		if (account == null) {
			throw new ValidationException("login.validation-error.email");
		}
		if (!account.getPassword().equals(form.getPassword())) {
			throw new ValidationException("login.validation-error.password");
		}
		if (!account.isActive()) {
			throw new ValidationException("login.validation-error.active");
		}
		return account;
	}

	@Override
	@Transactional(readOnly = false)
	public void addMoneyToAccountByVaucher(CurrentAccount currentAccount, Long voucherCode) throws ValidationException {
		Voucher voucher = vaucherRepository.findByCode(voucherCode);
		if (voucher == null || voucher.getActive() == false) {
			throw new ValidationException("Vaucher not found by code");
		}
		vaucherRepository.deactivateVaucher(voucher);
		BigDecimal newBalance = accountRepository.addMoneyToAccount(currentAccount.getId(), voucher.getValue());
		currentAccount.setMoney(newBalance);
	}

	@Override
	@Transactional
	public List<Account> listAllAccounts(int page, int limit) {
		List<Account> result = accountRepository.listAllAccounts(page, limit);
		for (Account a : result) {
			a.setTotalSum(accountSubscriptionTotalRepository.getTotalSumForAccount(a.getId()));
		}
		return result;
	}

	@Override
	@Transactional
	public int countAllAccounts() {
		return accountRepository.countAllAccounts();
	}

	@Override
	@Transactional(readOnly = false)
	public void changeAccountStatus(int idAccount) {
		accountRepository.changeActiveStatus(idAccount);
	}
}
