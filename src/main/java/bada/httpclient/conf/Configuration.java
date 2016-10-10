package bada.httpclient.conf;

import java.util.regex.Pattern;

public abstract class Configuration {
	public abstract String getURL();
	public abstract boolean isHT();
	public Pattern getLinkPattern(){return Pattern.compile("^/[a-z-]+");};
	public abstract int getSLEEPTime();
}
