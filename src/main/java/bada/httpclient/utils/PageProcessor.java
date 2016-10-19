package bada.httpclient.utils;

import bada.httpclient.conf.Configuration;

public abstract class PageProcessor {

	private LocalHttpClientBuilder client;
	private Configuration conf;
	
	public PageProcessor(Configuration conf) {
		this.conf= conf;
		client = new LocalHttpClientBuilder().build(conf);
	}

	protected String getHTML(String link){
		String html = client.getHTML(conf.getURL() + link);
		try {
			Thread.sleep(conf.getSLEEPTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return html;
	}

	protected void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
	
	public LocalHttpClientBuilder getClient() {
		return client;
	}

	public abstract void process();
}
