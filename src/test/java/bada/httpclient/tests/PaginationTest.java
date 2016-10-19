package bada.httpclient.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PaginationTest {

	public static void main(String[] args) {
		try {
			Document doc = Jsoup.parse(new File("pages/orzechy-i-pestki.html"), "UTF-8");
			Elements p = doc.select("p.pagination");
			Elements next_pages = p.select("a[href]");
			List<String> hrefs = new ArrayList<>();
			for (Element link_p : next_pages) {
				if(link_p.attr("href").contains("?")){
//					if(hrefs.size() > 0 && hrefs.contains(link_p.attr("href"))){ continue;}
					hrefs.add(link_p.attr("href"));
					System.out.println(link_p.attr("href")+" "+link_p.text());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
