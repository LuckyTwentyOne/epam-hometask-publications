package ua.kh.butov.subpub.service.impl;

import java.util.Locale;
import java.util.ResourceBundle;

import ua.kh.butov.subpub.service.I18nService;

class I18nServiceImpl implements I18nService{

	@Override
	public String getMessage(String key, Locale sessionLocale) {
		Locale locale = null;
		if (sessionLocale == null) {
			locale = Locale.getDefault();
		}else{
			locale = sessionLocale;
		}
		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		String value = rb.getString(key);
		return value;
	}

}
