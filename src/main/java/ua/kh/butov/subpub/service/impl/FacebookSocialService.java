package ua.kh.butov.subpub.service.impl;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ExtendedPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;

import ua.kh.butov.subpub.model.SocialAccount;
import ua.kh.butov.subpub.service.SocialService;

public class FacebookSocialService implements SocialService {
	private String idClient;
	private String secret;
	private String host;
	
	FacebookSocialService(ServiceManager serviceManager) {
		idClient = serviceManager.getApplicationProperty("social.facebook.idClient");
		secret = serviceManager.getApplicationProperty("social.facebook.secret");
		host = serviceManager.getApplicationProperty("app.host") + "/from-social";
	}

	@Override
	public String getAuthorizeUrl() {
		ScopeBuilder scopeBuilder = new ScopeBuilder();
		scopeBuilder.addPermission(ExtendedPermissions.EMAIL);
		FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_5);
		return client.getLoginDialogUrl(idClient, host, scopeBuilder);
	}

	@Override
	public SocialAccount getSocialAccount(String authToken) {
		FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_5);
		AccessToken accessToken = client.obtainUserAccessToken(idClient, secret, host, authToken);
		client = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_2_5);
		User user = client.fetchObject("me", User.class, Parameter.with("fields", "name,email,first_name,last_name"));
		return new SocialAccount(user.getFirstName(), user.getLastName(), user.getEmail());
	}
}
