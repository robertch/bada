package bada.httpclient.tests;

public class Splittest {
	public static void main(String[] args) {
		String test = "/orzechy-i-pestki";
		String test2 = "/orzechy-i-pestki/orzechy";
		System.out.println(test.split("/").length);
		System.out.println(test2.split("/").length);
	}
}
