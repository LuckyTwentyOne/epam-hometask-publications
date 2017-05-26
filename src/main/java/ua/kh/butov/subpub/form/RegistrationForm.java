package ua.kh.butov.subpub.form;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.service.I18nService;

public class RegistrationForm extends AbstractForm {

	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	
	public RegistrationForm() {
		super();
	}
	
	public RegistrationForm(String email, String firstName, String lastName, String password, String confirmPassword) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Override
	public void validate(I18nService i18nService) throws ValidationException {
		if(!EmailValidator.getInstance().isValid(email)) {
			throw new ValidationException(i18nService.getMessage("form.invalid.email", getLocale()));
		}
		if(StringUtils.isBlank(password)||StringUtils.isBlank(confirmPassword)) {
			throw new ValidationException(i18nService.getMessage("form.invalid.password", getLocale()));
		}
		if(StringUtils.isBlank(firstName)) {
			throw new ValidationException(i18nService.getMessage("form.invalid.firstName", getLocale()));
		}
		if(StringUtils.isBlank(lastName)) {
			throw new ValidationException(i18nService.getMessage("form.invalid.lastName", getLocale()));
		}
	}
}
