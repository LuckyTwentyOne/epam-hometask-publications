package ua.kh.butov.subpub.util;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.model.CurrentAccount;

public class SessionUtils {

	private SessionUtils() {
	}

	public static Cookie findAutoLoginCookie(HttpServletRequest req) {
		return WebUtils.findCookie(req, Constants.Cookie.AUTO_LOGIN.getName());
	}
	
	public static CurrentAccount getCurrentAccount(HttpServletRequest req) {
		return (CurrentAccount) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
	}

	public static void setCurrentAccount(HttpServletRequest req, CurrentAccount currentAccount) {
		req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, currentAccount);
	}

	public static boolean isCurrentAccountCreated(HttpServletRequest req) {
		return getCurrentAccount(req) != null;
	}

	public static Locale getSessionLocale(HttpServletRequest req) {
		return (Locale) req.getSession().getAttribute(Constants.SESSION_LOCALE);
	}
	
}
