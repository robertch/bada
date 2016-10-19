package bada.httpclient.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SupplierTest {
	
	static void printNames(Supplier arg) {
		System.out.println(arg.get());
	}
	
	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		lista.add("Robert");
		lista.add("Stefan");
		lista.add("Tomek");
		
		lista.stream().forEach((x)->{
			printNames(()->x);
		});
	}
}
