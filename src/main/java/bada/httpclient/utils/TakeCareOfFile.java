package bada.httpclient.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import bada.httpclient.Link;

public class TakeCareOfFile {
	public static void save(List<Link> linkslist,String file_name) throws IOException{
		Path path = Paths.get(file_name);
		try(BufferedWriter writer = Files.newBufferedWriter(path)){
			for(Link link : linkslist){
				writer.write(link.getName()+";"+link.getLink()+"\n");
			}
			writer.close();
		}
	}
	public static List<Link> read(String file_name) throws IOException {
		List<Link> list = new ArrayList<Link>();
		try(Stream<String> stream = Files.lines(Paths.get(file_name))){
		}
		return list;
	}
	
}