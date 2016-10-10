package bada.httpclient.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bada.httpclient.Link;
import junit.framework.TestCase;

public class TakeCareOfFileTest extends TestCase {
	List<Link> list;
	private String file_name = "links.txt";

	protected void setUp() throws Exception {
		super.setUp();
		list = new ArrayList<Link>();
		list.add(new Link("cebule", "/cebula"));
		list.add(new Link("marchewka","/marchewka"));
		list.add(new Link("ziemniaki","/ziemniaki"));
	}
	
	@Test
	public void testsaveList() throws IOException{
		TakeCareOfFile.save(list,file_name );
	}

	@Test
	public void testReadList() throws IOException {
		List<Link> llist = TakeCareOfFile.read(file_name);
		assertEquals(llist.size(), this.list.size());
	}
}
