package bada.httpclient;

public class Link {
	private String link;
	private String name;
	
	public Link() {
		super();
	}
	public Link(String link, String name) {
		super();
		this.link = link;
		this.name = name;
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
}
