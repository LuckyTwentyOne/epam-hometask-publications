package ua.kh.butov.subpub.model;

import java.math.BigDecimal;

public interface CurrentAccount {
	
	Integer getId();

	String getDescription();
	
	String getEmail();
	
	String getRole();
	
	BigDecimal getMoney();
	
	void setMoney(BigDecimal money);
}
