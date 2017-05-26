package ua.kh.butov.subpub.service;

import ua.kh.butov.subpub.model.SocialAccount;

public interface SocialService {

	String getAuthorizeUrl();

	SocialAccount getSocialAccount(String authToken);
}
