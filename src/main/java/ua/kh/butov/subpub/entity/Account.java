package ua.kh.butov.subpub.entity;

import java.math.BigDecimal;

import ua.kh.butov.subpub.model.CurrentAccount;

public class Account extends AbstractEntity<Integer> implements CurrentAccount{
	private static final long serialVersionUID = -6889352515111174105L;

	private String fistName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private BigDecimal money;

	public Account() {
	}
	
	public Account(String fistName, String lastName, String email, String password, String role, BigDecimal money) {
		super();
		this.fistName = fistName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.money = money;
	}
	
	public Account(String fistName, String lastName, String email) {
		super();
		this.fistName = fistName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFistName() {
		return fistName;
	}


	public void setFistName(String fistName) {
		this.fistName = fistName;
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
	@Override
	public String getDescription() {
		return lastName+" "+fistName+" ("+money.toString()+"$)";
	}
}
