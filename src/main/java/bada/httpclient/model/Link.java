package bada.httpclient.model;

public class Link {
	private String category;
	private String link;
	private String name;
	
	public Link() {}

	public Link(String link, String name,String category) {
		super();
		this.link = link;
		this.name = name;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toWrite() {
		return category + ";" + link + ";" + name;
	}
	
	@Override
	public String toString() {
		return "Link [category=" + category + ", link=" + link + ", name=" + name + "]";
	}
	
}
