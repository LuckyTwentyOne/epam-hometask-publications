package ua.kh.butov.subpub.util;

public class UrlUtils {

	public static boolean isAjaxUrl(String url) {
		return url.startsWith("/subpub/ajax/");
	}
	
	public static boolean isAjaxJsonUrl(String url) {
		return url.startsWith("/subpub/ajax/json/");
	}
	
	public static boolean isAjaxHtmlUrl(String url) {
		return url.startsWith("/subpub/ajax/html/");
	}

	public static boolean isStaticUrl(String url) {
		return url.startsWith("/subpub/static/");
	}

	public static boolean isMediaUrl(String url) {
		return url.startsWith("/subpub/media/");
	}
	
	public static boolean isSwitchUrl(String url) {
		return url.startsWith("/subpub/switch");
	}

	private UrlUtils() {
	}
}
