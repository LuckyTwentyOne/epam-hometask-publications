package ua.kh.butov.subpub.entity;

public class Voucher extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 4255725416509916939L;
	private Long code;
	private Integer value;
	private Boolean active;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
