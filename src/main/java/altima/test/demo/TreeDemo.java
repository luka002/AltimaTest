package altima.test.demo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import altima.test.Tree;

/**
 * Program that demonstrates functionality of the Tree class.
 * User can specify path to the file with data that will be given
 * to the tree. If file path is not specified, default path will be
 * used. File that is used is expected to have the following structure:
 * "(child name) (parent name)". When tree is constructed it will be printed
 * out on the screen.
 * 
 * @author Luka Grgić
 * @version 1.0
 */
public class TreeDemo {
	
	/**
	 * Default path to the default document.
	 */
	private static final String DEFAULT_DOC_PATH = "src/main/resources/example1.txt";

	/**
	 * Method that is first executed when program starts.
	 * 
	 * @param args Command line arguments. If 1 argument is present it will
	 * be used as file path, otherwise default file path will be used.
	 */
	public static void main(String[] args) {
		String documentPath = DEFAULT_DOC_PATH;
		
		if (args.length == 1) {
			documentPath = args[0];
		}
		
		List<String> lines = null;
		Tree tree = null;
		
		try {
			lines = Files.readAllLines(Paths.get(documentPath));
			tree = new Tree(lines);
		
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		} 
		
		System.out.println(tree);
	}

}
