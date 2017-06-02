package ua.kh.butov.subpub.entity;

import java.math.BigDecimal;

import ua.kh.butov.subpub.annotation.jdbc.Column;

public class AccountSubscriptionTotal extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 4556974889271878719L;
	@Column("id_account")
	private Integer idAccount;
	@Column("total_sum")
	private BigDecimal totalSum;
	@Column("id_subscription")
	private Long idSubscription;
	
	public AccountSubscriptionTotal() {
		super();
	}

	public AccountSubscriptionTotal(Integer idAccount, BigDecimal totalSum, Long idSubscription) {
		super();
		this.idAccount = idAccount;
		this.totalSum = totalSum;
		this.idSubscription = idSubscription;
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	public Long getIdSubscription() {
		return idSubscription;
	}

	public void setIdSubscription(Long idSubscription) {
		this.idSubscription = idSubscription;
	}
}
