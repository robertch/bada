package bada.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import bada.httpclient.conf.Configuration;

public class LocalHttpClientBuilder {
	
	HttpHost proxy;
	CredentialsProvider	credentialsProvider;
	CloseableHttpClient l_client;
	
	public LocalHttpClientBuilder() {}

	public LocalHttpClientBuilder build(Configuration conf){
		
		if (conf.isHT()) {
			setUpProxy();
		}
		if (proxy != null && credentialsProvider != null) {
			l_client = HttpClientBuilder
					.create().setProxy(proxy)
					.setDefaultCredentialsProvider(credentialsProvider)
					.build();
		} else {
			l_client = HttpClientBuilder.create().build();
		}
		return this;
	}
	private void setUpProxy() {
		proxy = new HttpHost(HTProxy.HOST, HTProxy.PORT);
		Credentials credentials = new UsernamePasswordCredentials(HTProxy.USER, HTProxy.PASSSWORD);
		AuthScope authScope = new AuthScope(HTProxy.HOST, HTProxy.PORT);
		credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(authScope, credentials);
	}
	
	public String getHTML(String url) {
		String html = "";
		HttpGet httpget = new HttpGet(url);
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
			public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}
		};
		System.out.println("Executing request " + httpget.getRequestLine());

		System.out.println("---------<>>>>>>>>>>>>>");

		try {
			html = l_client.execute(httpget, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}

	public void close() {
		try {
			l_client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
