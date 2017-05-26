package ua.kh.butov.subpub.form;

import org.apache.commons.lang.StringUtils;

public class SearchForm {
	private String query;
	private String sortedBy;

	public SearchForm(String query, String sortedBy) {
		super();
		this.query = query;
		this.sortedBy = sortedBy;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSortedBy() {
		return sortedBy;
	}

	public void setSortedBy(String sortedBy) {
		this.sortedBy = sortedBy;
	}

	public boolean isSorted() {
		return sortedBy != "id";
	}

	public boolean isEmpty() {
		boolean result = false;
		if (StringUtils.isBlank(query) && sortedBy.equals("id")) {
			result = true;
		}
		return result;
	}

}
