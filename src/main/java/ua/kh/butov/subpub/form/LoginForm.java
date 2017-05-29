package ua.kh.butov.subpub.form;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.service.I18nService;

public class LoginForm extends AbstractForm{
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void validate(I18nService i18nService) throws ValidationException {
		if(!EmailValidator.getInstance().isValid(email)) {
			throw new ValidationException("form.invalid.email");
		}
		if(StringUtils.isBlank(password)) {
			throw new ValidationException("form.invalid.password");
		}
	}
}
