package bada.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bada.httpclient.conf.Configuration;
import bada.httpclient.model.Item;
import bada.httpclient.model.Link;
import bada.httpclient.utils.PageProcessor;
import bada.httpclient.utils.TakeCareOfFile;

public class OnlyItems extends PageProcessor{

	List<Link> items_link;
	
	public OnlyItems(Configuration conf,List<Link> items_link) {
		super(conf);
		this.items_link = items_link;
	}

	@Override
	public void process() {
		try {
			List<Item> items = new ArrayList<>();
			for (Link link : items_link) {
				items.addAll(getItemData(link));
			}
			TakeCareOfFile.saveItems(items, "items_prices.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<Item> getItemData(Link link) {
		List<Item> list_of_items = new ArrayList<>();
		String html = getHTML(link.getLink());
		try {
			TakeCareOfFile.saveHTMLString("items/"+link.getLink(), html);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(html !=null && !html.equals("")){
			Document doc = Jsoup.parse(html);
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
					list_of_items.add(new Item(link.getCategory(),name, parsed_price, 
											unit_of_measure, parsed_unit,link.getLink()));
				}
			}
		}
		return list_of_items;
	}
}
