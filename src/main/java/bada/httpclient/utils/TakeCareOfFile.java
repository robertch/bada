package bada.httpclient.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import bada.httpclient.model.Item;
import bada.httpclient.model.Link;

public class TakeCareOfFile {
	
	public static void save(List<Link> linkslist,String file_name) throws IOException{
		Path path = Paths.get(file_name);
		try(BufferedWriter writer = Files.newBufferedWriter(path)){
			for(Link link : linkslist){
				writer.write(link.toString()+"\n");
			}
			writer.close();
		}
	}
	
	public static void saveHTMLString(String file_name,String content) throws IOException{
		Path path = Paths.get(file_name+".html");
		try(BufferedWriter writer = Files.newBufferedWriter(path)){
			writer.write(content);
			writer.close();
		}
	}

	public static List<Link> read(String file_name) throws IOException {
		List<Link> list = new ArrayList<Link>();
		try(Stream<String> stream = Files.lines(Paths.get(file_name))){
			stream.forEach((x)->{operate(x,list);});
		}
		return list;
	}
	
	private static void operate(String x,List<Link> list) {
		String[] data = x.split(";");
		list.add(new Link(data[2], data[1],data[0]));
	}

	public static void saveItems(List<Item> items, String file_name) throws IOException {
		Path path = Paths.get(file_name);
		try(BufferedWriter writer = Files.newBufferedWriter(path)){
			for(Item item: items){
				writer.write(item.toString()+"\n");
			}
			writer.close();
		}	
	}
	
	public static List<Item> readItems(String file_name) throws IOException {
		List<Item> list = new ArrayList<>();
		try(Stream<String> stream = Files.lines(Paths.get(file_name))){
			stream.forEach((x)->{operateOnItem(x,list);});
		}
		return list;
	}
	
	private static void operateOnItem(String x,List<Item> list) {
		String[] data = x.split(";");
		list.add(new Item(data[0], data[1], new Double(data[2]).doubleValue(), data[3], new Double(data[4]).doubleValue(), data[5]));
	}
}