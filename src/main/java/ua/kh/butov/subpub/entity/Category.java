package ua.kh.butov.subpub.entity;

public class Category extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 4750809220016947998L;
	
	private String name;
	private String url;
	private Integer publicationCount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPublicationCount() {
		return publicationCount;
	}
	public void setPublicationCount(Integer publicationCount) {
		this.publicationCount = publicationCount;
	}
	@Override
	public String toString() {
		return String.format("Category [name=%s, url=%s, publicationCount=%s, id=%s]", name, url, publicationCount, getId());
	}

}
