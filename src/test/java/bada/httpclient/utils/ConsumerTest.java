package bada.httpclient.utils;

import java.util.function.Consumer;

public class ConsumerTest {
	private static void printNames(String name){
		System.out.println(name);
	}
	public static void main(String[] args) {
		Consumer<String> consumer = ConsumerTest::printNames;
		consumer.accept("Jan");
		consumer.accept("Anna");
		consumer.accept("Robert");
	}
}
