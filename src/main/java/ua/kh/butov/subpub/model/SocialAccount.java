package ua.kh.butov.subpub.model;

/**
 * Social account model.
 * 
 * @author V.Butov
 *
 */
public class SocialAccount {
	private final String firstName;
	private final String lastName;
	private final String email;

	public SocialAccount(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

}
