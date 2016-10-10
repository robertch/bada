package bada.httpclient.conf;

public class HTBadapakItemConf extends Configuration {
	@Override
	public String getURL() {return "http://www.badapak.pl/dynia-luskana-pestka-dyni";}

	@Override
	public boolean isHT() {
		return true;
	}

	@Override
	public int getSLEEPTime() {
		return 8000;
	}
	
}
