package ua.kh.butov.subpub.form;

import java.util.Locale;

import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.service.I18nService;

public abstract class AbstractForm {
	protected Locale locale;
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Locale getLocale() {
		return locale;
	}
	public void validate(I18nService i18nService) throws ValidationException {

	}
}