package bada.httpclient.conf;

import java.util.regex.Pattern;

public abstract class Configuration {
	public Pattern getLinkPattern(){return Pattern.compile("^/[a-z-]+");}
	public boolean isDevelopment(){return true;}
	public abstract String getURL();
	public abstract boolean isHT();
	public abstract int getSLEEPTime();
}
