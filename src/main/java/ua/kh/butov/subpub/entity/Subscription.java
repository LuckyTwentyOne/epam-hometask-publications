package ua.kh.butov.subpub.entity;

import java.sql.Timestamp;

import ua.kh.butov.subpub.annotation.jdbc.Column;
import ua.kh.butov.subpub.annotation.jdbc.Transient;

public class Subscription extends AbstractEntity<Long>{
	private static final long serialVersionUID = -1434063017481118318L;
	
	@Column("id_account")
	private Integer idAccount;
	private Timestamp created;
	@Column("expiration_date")
	private Timestamp expirationDate;
	@Column("id_publication")
	private Integer idPublication;
	@Transient
	private Publication publication;
	
	public Subscription() {
	}
	
	public Subscription(Integer idAccount, Timestamp expirationDate,Integer idPublication) {
		this.idAccount = idAccount;
		this.expirationDate = expirationDate;
		this.idPublication = idPublication;
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

	public Integer getIdPublication() {
		return idPublication;
	}

	public void setIdPublication(Integer idPublication) {
		this.idPublication = idPublication;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
}
