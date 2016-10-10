package bada.httpclient.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParserTest {
	public static void main(String[] args) {
		String text = "<label><input type=\"radio\" name=\"qty\"    class=\"setpropRadio\" mag_id=\"579\" checked=\"checked\">"
				+ "  1   kg - <span>19,00&nbsp;zł</span></label>"
				+ "                                 <label><input type=\"radio\" name=\"qty\"        class=\"setpropRadio\" mag_id=\"580\">"
				+ "   							5 kg -   <span>85,00&nbsp;zł</span></label>"
				+ " <label><input type=\"radio\" name=\"qty\" class=\"setpropRadio\" mag_id=\"2723\" checked=\"checked\">"
				+ " 					0.1 kg - <span>34,    85&nbsp;zł</span></label>     ";
			Pattern patt = Pattern.compile("\\d\\+skg\\s+-\\s+<span>\\d+,\\d{2}");
			Matcher matcher = patt.matcher(text);
			if(matcher.find()){ 
				System.out.println(matcher.group());
			}
	}
}
