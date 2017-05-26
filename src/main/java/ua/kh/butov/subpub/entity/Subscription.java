package ua.kh.butov.subpub.entity;

import java.sql.Timestamp;

public class Subscription extends AbstractEntity<Long>{
	private static final long serialVersionUID = -1434063017481118318L;
	
	private Integer idAccount;
	private Timestamp created;
	private Timestamp expirationDate;
	private int idPublication;
	
	public Subscription() {
	}
	
	public Subscription(Integer idAccount, Timestamp created, Timestamp expirationDate) {
		this.idAccount = idAccount;
		this.created = created;
		this.expirationDate = expirationDate;
	}


	public Integer getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getIdPublication() {
		return idPublication;
	}

	public void setIdPublication(int idPublication) {
		this.idPublication = idPublication;
	}
}
