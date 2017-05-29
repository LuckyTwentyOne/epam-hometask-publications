package ua.kh.butov.subpub.entity;

import java.math.BigDecimal;

import ua.kh.butov.subpub.annotation.jdbc.Column;

public class Publication extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 6307696364153734237L;
	private String name;
	private String description;
	private String conditions;
	@Column("image_link")
	private String imageLink;
	private BigDecimal price;
	private String category;
	@Column("id_category")
	private Integer idCategory;

	
	public Publication(String name, String description, String conditions, BigDecimal price, int idCategory) {
		super();
		this.name = name;
		this.description = description;
		this.conditions = conditions;
		this.price = price;
		this.idCategory = idCategory;
	}

	public Publication() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	@Override
	public String toString() {
		return String.format("Product [id=%s, name=%s, description=%s, imageLink=%s, price=%s, category=%s]", getId(), name, description, imageLink, price, category);
	}
}
