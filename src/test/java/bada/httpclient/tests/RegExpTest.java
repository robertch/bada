package bada.httpclient.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpTest {
	public static void main(String[] args) {
		Pattern patt = Pattern.compile("^/[a-z-]+");
		Matcher match = patt.matcher("* a: </orzechy-i-pestki>  (ORZECHY I PESTKI)");
	}
}
