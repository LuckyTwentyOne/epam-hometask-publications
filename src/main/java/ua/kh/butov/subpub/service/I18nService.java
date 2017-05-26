package ua.kh.butov.subpub.service;

import java.util.Locale;

public interface I18nService {

	/**
	 * Gets value from {@code ResourceBundle} depends on Locale.
	 * 
	 * @param key property key
	 * @return internationalized value
	 */
	String getMessage(String key, Locale sessionLocale);
	
}
