package bada.httpclient;

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bada.httpclient.conf.Configuration;
import bada.httpclient.conf.HTBadapakConf;
import bada.httpclient.utils.TakeCareOfFile;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify
 * the process of processing the HTTP response and releasing associated
 * resources.
 */
public class Badapak {

	
	CloseableHttpClient httpclient;
	HashMap<String, List<Link>> site_map = new HashMap<String, List<Link>>(); 
	Configuration conf = new HTBadapakConf();
	LocalHttpClientBuilder client;

	public Badapak() {
		client = new LocalHttpClientBuilder().build(conf);
	}

	public void process() throws IOException {
		List<Link> links = parseLinks(parseLinks(client.getHTML(conf.getURL())));
		TakeCareOfFile.save(links, "badapak_linki.txt");
		client.close();
	}

	private List<Link> parseLinks(List<Link> links) {
		List<Link> links_out;
		if (links.size() > 0){
			for (Link link : links) {
				String html = client.getHTML(conf.getURL() + link.getLink());
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				links_out = parseLinks(html);
			}
		}
		return null;
	}
	
	private List<Link> parseLinks(String html) {
		Matcher match;
		List<Link> links_out = new ArrayList<Link>();
		if (html != null && !html.equals("")) {
			Document doc = Jsoup.parse(html);
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				match = conf.getLinkPattern().matcher(link.attr("href"));
				if (match.find()){
					links_out.add(new Link(link.attr("href"),link.text()));
					print(" * a: <%s>  (%s)", link.attr("href"), link.text());
				}
			}
		} else {
			System.out.println("NULL!!!");
		}
		return links_out;
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	public final static void main(String[] args) throws Exception {
		new Badapak().process();
	}
}
