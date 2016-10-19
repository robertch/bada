package bada.httpclient.conf;

public class HomeConf extends Configuration {

	@Override
	public String getURL() {
		return "http://www.badapak.pl";
	}

	@Override
	public boolean isHT() {
		return false;
	}

	@Override
	public int getSLEEPTime() {
		return 3000;
	}

}
