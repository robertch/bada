package bada.httpclient.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bada.httpclient.model.Item;

public class ParseItem {
	public static void main(String[] args) {
		try {
			List<Item> list_of_items = new ArrayList<>();
//			Document doc = Jsoup.parse(new File("orzech.html"), "UTF-8");
			Document doc = Jsoup.parse(new File("platki_migdaly.html"), "UTF-8");
			Elements p = doc.select("div.top-info > div.title-line");
			String name = p.text();
			Elements radios = doc.select("div.radio-group");
			Elements input = radios.select("label");
			for (Element element : input) {
				System.out.println(element.text());
				String[] items_str  = element.text().split("-");
				if(items_str.length>1){
					String[] unit = items_str[0].trim().split("\\s+");
					String str = items_str[1].trim().replaceAll("\u00A0"," ");
					String[] price = str.split("\\s+");
					double parsed_unit  = new Double(unit[0].trim());
					String unit_of_measure = unit[1].trim();
					double parsed_price = new Double(price[0].replace(",", ".").trim());
					list_of_items.add(new Item("",name, parsed_price, unit_of_measure, parsed_unit,""));
				}
			}
			for (Item item : list_of_items) {
				System.out.println(item.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
