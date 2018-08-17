package altima.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TreeTest {

	@Test(expected=NullPointerException.class)
	public void emptyTreeTest() {
		new Tree(null);
	}
	
	@Test
	public void addNodeToTheRootTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		
		Tree tree = new Tree(list);
		
		String expected = "Ivan\n"
						+ "    Adam\n";
		
		assertEquals(expected, tree.toString());
	}
	
	@Test
	public void addMultipleNodesToTheRootTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		list.add("Darko Bruno");
		list.add("Stjepan Rajko");
		
		Tree tree = new Tree(list);
		
		String expected = "Ivan\n"
						+ "    Adam\n"
						+ "Bruno\n"
						+ "    Darko\n"
						+ "Rajko\n"
						+ "    Stjepan\n";
		
		assertEquals(expected, tree.toString());
	}
	
	@Test
	public void addNodeToALeafNodeTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		list.add("Darko Bruno");
		list.add("Stjepan Adam");
		
		Tree tree = new Tree(list);
		
		String expected = "Ivan\n"
						+ "    Adam\n"
						+ "        Stjepan\n"
						+ "Bruno\n"
						+ "    Darko\n";
		
		assertEquals(expected, tree.toString());
	}
	
	@Test
	public void addMultipleNodesToANodeTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		list.add("Darko Ivan");
		list.add("Stjepan Ivan");
		list.add("Stiv Ivan");
		
		Tree tree = new Tree(list);
		
		String expected = "Ivan\n"
						+ "    Adam\n"
						+ "    Darko\n"
						+ "    Stjepan\n"
						+ "    Stiv\n";
		
		assertEquals(expected, tree.toString());
	}
	
	@Test
	public void addExistingLeafNodeToANodeTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		list.add("Darko Bruno");
		list.add("Stjepan Adam");
		list.add("Lovro Mijo");
		list.add("Darko Adam");
		
		Tree tree = new Tree(list);
		
		String expected = "Ivan\n"
						+ "    Adam\n"
						+ "        Stjepan\n"
						+ "        Darko\n"
						+ "Bruno\n"
						+ "    Darko\n"
						+ "Mijo\n"
						+ "    Lovro\n";
		
		assertEquals(expected, tree.toString());
	}
	
	@Test
	public void addExistingParentNodeToANodeTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		list.add("Darko Bruno");
		list.add("Stjepan Adam");
		list.add("Lovro Darko");
		list.add("Darko Stjepan");
		
		Tree tree = new Tree(list);
		
		String expected = "Ivan\n"
						+ "    Adam\n"
						+ "        Stjepan\n"
						+ "            Darko\n"
						+ "                Lovro\n"
						+ "Bruno\n"
						+ "    Darko\n"
						+ "        Lovro\n";
		
		assertEquals(expected, tree.toString());
	}
	
	@Test
	public void addNodeWithRootAsAParentToANodeTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		list.add("Darko Bruno");
		list.add("Bruno Adam");
		
		Tree tree = new Tree(list);
		
		String expected = "Ivan\n"
						+ "    Adam\n"
						+ "        Bruno\n"
						+ "            Darko\n";
		
		assertEquals(expected, tree.toString());
	}
	
	@Test(expected=IllegalTreeInsertionException.class)
	public void addIllegalChildTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		list.add("Ivan Adam");
		
		new Tree(list);
	}
	
	@Test(expected=IllegalTreeInsertionException.class)
	public void repeatInsertionTest() {
		List<String> list = new ArrayList<>();
		list.add("Adam Ivan");
		list.add("Adam Ivan");
		
		new Tree(list);
	}
	
	@Test(expected=IllegalTreeInsertionException.class)
	public void sameChildAndParentTest() {
		List<String> list = new ArrayList<>();
		list.add("Ivan Ivan");
		
		new Tree(list);
	}
	
}
