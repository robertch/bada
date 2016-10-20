package bada.httpclient.model;

public class Item {
	private String category;
	private String name;
	private double price;
	private String unit_of_measure;
	private double unit;
	private String link;

	public Item() {}

	public Item(String category, String name, double price, String unit_of_measure, double unit, String link) {
		super();
		this.category = category;
		this.name = name;
		this.price = price;
		this.unit_of_measure = unit_of_measure;
		this.unit = unit;
		this.link = link;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit_of_measure() {
		return unit_of_measure;
	}

	public void setUnit_of_measure(String unit_of_measure) {
		this.unit_of_measure = unit_of_measure;
	}

	public double getUnit() {
		return unit;
	}

	public void setUnit(double unit) {
		this.unit = unit;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return category + ";" + name + ";" + price + ";"+ unit_of_measure + ";" + unit + ";" + link;
	}
}
