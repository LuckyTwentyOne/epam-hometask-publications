package ua.kh.butov.subpub;

public class Constants {
	
	public static final String ACCOUNT_ACTIONS_HISTORY = "ACCOUNT_ACTIONS_HISTORY ";
	
	public static final int MAX_PUBLICATIONS_PER_HTML_PAGE = 12;
	
	public static final int MAX_SUBSCRIPTIONS_PER_HTML_PAGE = 5;
	
	public static final String CATEGORY_LIST = "CATEGORY_LIST";
	
	public static final String CURRENT_ACCOUNT = "CURRENT_ACCOUNT";
	
	public static final String SUCCESS_REDIRECT_URL_AFTER_SIGNIN = "SUCCESS_REDIRECT_URL_AFTER_SIGNIN";
	
	public static final String CURRENT_REQUEST_URL = "CURRENT_REQUEST_URL";
	
	public static final String SESSION_LOCALE = "SESSION_LOCALE";
	
	public enum Cookie {
		// 1 year ttl
		AUTO_LOGIN("ALC", 60 * 60 * 24 * 365);

		private final String name;
		private final int ttl;

		private Cookie(String name, int ttl) {
			this.name = name;
			this.ttl = ttl;
		}

		public String getName() {
			return name;
		}

		public int getTtl() {
			return ttl;
		}
	}

}
