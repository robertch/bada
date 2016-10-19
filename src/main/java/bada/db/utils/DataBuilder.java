package bada.db.utils;

public class DataBuilder {
	public static void main(String[] args) {
		String sql = new ItemsCSVtoDB()
							.readFromFile()
							.toSQL();
		System.out.println(sql);
	}
}
