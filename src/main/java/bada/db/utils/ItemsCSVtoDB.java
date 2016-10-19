package bada.db.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bada.httpclient.model.Item;
import bada.httpclient.utils.TakeCareOfFile;

public class ItemsCSVtoDB {

	List<Item> items_list;

	public ItemsCSVtoDB() {}
	
	public ItemsCSVtoDB readFromFile() {
		try {
			this.items_list = TakeCareOfFile.readItems("items_prices.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public String toSQL(){
		if(this.items_list == null){
			return "Brak danych";
		}
		StringBuffer sql = new StringBuffer();
		StringBuffer items = new StringBuffer();
		Set<String> categories = new HashSet<String>();
		for (Item item : this.items_list) {
			categories.add(item.getCategory());
			items.append("INSERT INTO towary(nazwa,cena,jednostka,ilosc,link) "
					+ "	VALUES ('"+item.getName()+"',"+item.getPrice()+","
							+ "'"+item.getUnit_of_measure()+"',"+item.getUnit()+",'"+item.getLink()+"');\n");
		}
		for (String category : categories) {
			sql.append("INSERT INTO kategorie(nazwa) VALUES('"+category+"');\n");
		}
		sql.append(items);
		return sql.toString();
	}
}
