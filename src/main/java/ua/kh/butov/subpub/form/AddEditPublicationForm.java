package ua.kh.butov.subpub.form;

import org.apache.commons.lang.StringUtils;

import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.service.I18nService;

public class AddEditPublicationForm extends AbstractForm {

	private int id;
	private String name;
	private String price;
	private String description;
	private String conditions;
	private int category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public void validate(I18nService i18nService) throws ValidationException {
		if (StringUtils.isBlank(name) || name.length() >= 60) {
			throw new ValidationException("'Name' field is incorrect");
		}
		try {
			Double.parseDouble(price);
		} catch (NumberFormatException e) {
			throw new ValidationException("Wrong price format, should be like 100.15 or 74.92", e);
		}
		double covertedPrice = Double.parseDouble(price);
		if (covertedPrice < 0) {
			throw new ValidationException("Price should be possitive");
		}
		if (StringUtils.isBlank(description) || StringUtils.isBlank(conditions)) {
			throw new ValidationException("Please fill all inputs");
		}
	}

}
