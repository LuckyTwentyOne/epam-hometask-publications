package ua.kh.butov.subpub.entity;

import java.math.BigDecimal;

import ua.kh.butov.subpub.annotation.jdbc.Column;
import ua.kh.butov.subpub.annotation.jdbc.Transient;
import ua.kh.butov.subpub.model.CurrentAccount;

public class Account extends AbstractEntity<Integer> implements CurrentAccount {
	private static final long serialVersionUID = -6889352515111174105L;

	@Column("first_name")
	private String firstName;
	@Column("last_name")
	private String lastName;
	private String email;
	private String password = "password";
	private String role;
	private BigDecimal money;
	private Boolean active;
	@Transient
	private BigDecimal totalSum;

	public Account() {
		money = BigDecimal.ZERO;
		role = "reader";
	}

	public Account(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		money = BigDecimal.ZERO;
		role = "reader";
	}

	public Account(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		money = BigDecimal.ZERO;
		role = "reader";
	}
	
	public Account(String role) {
		super();
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	@Override
	public String getDescription() {
		return lastName + " " + firstName + " (" + money.toString() + "$)";
	}
}
