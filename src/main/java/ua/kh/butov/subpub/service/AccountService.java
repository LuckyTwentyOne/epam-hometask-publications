package ua.kh.butov.subpub.service;

import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.model.SocialAccount;

public interface AccountService {

	CurrentAccount authentificate(SocialAccount socialAccount);
}
