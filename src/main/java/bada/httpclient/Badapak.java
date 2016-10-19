package bada.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bada.httpclient.conf.Configuration;
import bada.httpclient.model.Item;
import bada.httpclient.model.Link;
import bada.httpclient.utils.PageProcessor;
import bada.httpclient.utils.TakeCareOfFile;

public class Badapak extends PageProcessor{

	Configuration conf;
	public Badapak(Configuration conf) {
		super(conf);
		this.conf = conf;
	}

	public void process() {
		List<Link> categories = getListOfCategories();
		if (categories.isEmpty())
			try {
				throw new NoDataException();
			} catch (NoDataException e) {
				e.printStackTrace();
			}
		List<Link> items = getListOfItems(categories);
		if (items.isEmpty())
			try {
				throw new NoDataException();
			} catch (NoDataException e) {
				e.printStackTrace();
			}
		try {
			TakeCareOfFile.save(items, "items.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		OnlyItems onlyItems = new OnlyItems(conf, items);
		onlyItems.process();
	}
	
	private List<Link> getListOfCategories(){
		List<Link> links = new ArrayList<>();
		Matcher match;
		String html = getHTML("");
		if (html != null && !html.equals("")) {
			writeOnDisc("main", html);
			Document doc = Jsoup.parse(html);
			Elements div = doc.select("div.boxes-promo");
			Elements links_elem = div.select("a[href]");
			for (Element link : links_elem) {
				match = conf.getLinkPattern().matcher(link.attr("href"));
				if (match.find()){
					if(link.attr("href").split("/").length==2){
						links.add(new Link(link.attr("href"),link.text(),link.text()));
						print(" * a: <%s>  (%s)", link.attr("href"), link.text());
					}
				}
			}
		}
		return links;
	}

	private List<Link> getListOfItems(List<Link> links){
		List<Link> items = new ArrayList<Link>();
		Document doc = null;
		Elements p = null;
		Elements next_pages = null;
		for (Link link : links) {
			String html = getHTML(link.getLink());
			writeOnDisc(link.getLink().split("/")[1], html);
			if (html != null && !html.equals("")) {
				doc = Jsoup.parse(html);
				items.addAll(getItems(Jsoup.parse(html),link.getCategory()));
			}
			p = doc.select("p.pagination");
			next_pages = p.select("a[href]");
			List<String> hrefs = new ArrayList<>();
			for (Element link_p : next_pages) {
				if(link_p.attr("href").contains("?")){
					if(hrefs.size() > 0 && hrefs.contains(link_p.attr("href"))){ continue;}
					hrefs.add(link_p.attr("href"));
					print(" * a: <%s>  (%s)", link_p.attr("href"), link_p.text());
					html = getHTML(link.getLink()+link_p.attr("href"));
					items.addAll(getItems(Jsoup.parse(html),link.getCategory()));
				}
			}
		}
		return items;
	}
	
	private List<Link> getItems(Document doc,String category) {
		List<Link> links = new ArrayList<>();
		Elements p = doc.select("div.p-name");
		Elements next_pages = p.select("a[href]");
		Matcher match;
		for (Element link_p_name : next_pages) {
			match = conf.getLinkPattern().matcher(link_p_name.attr("href"));
			if (match.find()){
				if(!link_p_name.attr("href").equals("")){
					links.add(new Link(link_p_name.attr("href"),link_p_name.text(),category));
					print(" * a: <%s>  (%s)", link_p_name.attr("href"), link_p_name.text());
				}
			}
		}
		return links;
	}
	
	private List<Item> getItemData(Link link) {
		List<Item> list_of_items = new ArrayList<>();
		String html = getHTML(link.getLink());
		if(html !=null && !html.equals("")){
			writeOnDisc(link.getLink().split("/")[1], html);
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
	
	private void writeOnDisc(String file_name,String content){
		if(conf.isDevelopment()){
			try {
				TakeCareOfFile.saveHTMLString("pages/"+file_name, content);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
