package ua.kh.butov.subpub.entity;

import java.math.BigDecimal;

public class Account extends AbstractEntity<Integer>{
	private static final long serialVersionUID = -6889352515111174105L;

	private String name;
	private String email;
	private String password;
	private String role;
	private BigDecimal money;

	public Account() {
	}
	public Account(String name, String email, String password, String role, BigDecimal money) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
