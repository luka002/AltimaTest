package altima.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class responsible for creation of special kind of tree structure.
 * Trough the constructor is passed a list of strings and a tree is 
 * constructed based on it. Each string has a following structure: 
 * "(child name) (parent name)". That structure represents the relationship 
 * between nodes that are about to be created. Node with the name 
 * "child name" and node with the name "parent name" will be created 
 * and they will have a child-parent relationship. If nodes with those names
 * already exist in the tree, they won't be created because than there would
 * be two nodes with the same name. Instead, they will just be connected so 
 * they make child-parent relationship. If a node does not have a parent its 
 * parent will then be the root node. If same child-parent relationship is 
 * defined twice an error will occur. An error will also occur if there is a 
 * cyclic relationship between nodes (A->B->C->A). Nodes can have multiple 
 * children and parents.
 * 
 * @author Luka Grgić
 * @version 1.0
 */
public class Tree {

	/**
	 * Root node.
	 */
	private Node root;
	/**
	 * Defines structure of the tree.
	 */
	private List<String> structure;
	/**
	 * Child node that will be inserted into the tree.
	 */
	private Node child;
	/**
	 * Parent node that will be inserted into the tree.
	 */
	private Node parent;
	/**
	 * Default indentation when creating a string from the tree.
	 */
	private final int DEFAULT_INDENTATION = 0;
	/**
	 * Amount of indentation increment when accessing a deeper level of the tree
	 * when creating a string from the tree..
	 */
	private final int INDENTATION_INCREMENT = 4;
	
	/**
	 * Constructor initializes this object and builds a tree.
	 * 
	 * @param structure Defines how the tree will look like.
	 * @throws NullPointerException if the <code>structure</code> is null.
	 */
	public Tree(List<String> structure) {
		Objects.requireNonNull(structure, "Passed data can not be null.");
		
		this.root = new Node(null);
		this.structure = structure;
		
		buildTree();
	}

	/**
	 * Builds a tree based on its structure.
	 * 
	 * @throws IllegalArgumentException If structure list does not follow
	 * the following structure: "(child name) (parent name)".
	 * 									
	 */
	private void buildTree() {
		for (String row : structure) {
			String[] childAndParent = row.split(" ");
			
			if (childAndParent.length != 2) {
				throw new IllegalArgumentException("Every row has to follow" + 
						"the following structure: \"(child name) (parent name)\"");
			}
			
			child = new Node(childAndParent[0]);
			parent = new Node(childAndParent[1]);
			
			searchForChildAndParent(root);
			add();
		}
	}
	
	/**
	 * Searches the tree to see if it contains a child and parent nodes that are
	 * about to be inserted into it. There can not be two nodes with the same name
	 * so for example if node named "John" exists and new node named "John" has to 
	 * be created, instead of creating that new node the old node named "John" will
	 * be used in its place.
	 *
	 * @param root Node whose children are searched.
	 */
	private void searchForChildAndParent(Node root) {
		for (Node node : root.getChildren()) {
			if (child.equals(node)) {
				child = node;
			}
			
			if (parent.equals(node)) {
				parent = node;
			}
			
			if (!child.isEmpty() && !parent.isEmpty()) {
				return;
			}
			
			searchForChildAndParent(node);
		}
	}

	/**
	 * Adds requested nodes into the tree.
	 * 
	 * @throws IllegalTreeInsertionException If an illegal 
	 * insertion has been made. Illegal insertion is an
	 * insertion that makes tree structure circular. That means
	 * that one node can not have a child and a parent with the same
	 * name. Also exception will be thrown if the same insertion
	 * is requested again.
	 */
	private void add() {
		child.addParent(parent);
		
		if (root.getChildren().contains(child)) {
			child.parents.remove(root);
			root.children.remove(child);
		}
		
		parent.addChild(child);
		
		if (parent.parents.isEmpty()) {
			root.addChild(parent);
			parent.addParent(root);
		}
	}

	@Override
	public String toString() {
		return toString(DEFAULT_INDENTATION, root.getChildren());
	}
	
	/**
	 * Returns a string representation of the tree.
	 * String in build in such a way that every line contains
	 * only one node and before every node comes specified number
	 * of spaces that varies based on how deep that node is in the tree
	 * hierarchy compared to the root node. Child of the root node
	 * has 0 spaces, child of a child of the root node has 4 spaces and
	 * every next child will have 4 spaces more. Order is defined in such
	 * a way that firstly the first node from the root node child list is taken,
	 * then if that node has children his first children is taken and if
	 * that node does not have children the second children from the 
	 * root node will be taken. Following the pattern described it is 
	 * evident that nodes are taken recursively.
	 * 
	 * @param depth Current tree depth level. Children from root node have
	 * depth level 0, their children have depth level 1 and so on.
	 * @param children List of children from which name is fetched.
	 * @return String representation of the tree.
	 */
	private String toString(int depth, List<Node> children) {
		StringBuilder builder = new StringBuilder();
		
		for (Node node : children) {
			builder.append(String.format(
					spaces(depth*INDENTATION_INCREMENT) + "%s\n", node.name)
			);
			builder.append(toString(depth+1, node.getChildren()));
		}
		
		return builder.toString();
	}
	
	/**
	 * Returns string with number of spaces defined as parameter.
	 * 
	 * @param numberOfSpaces Defines how many spaces will the returned
	 * string have.
	 * @return String that is constructed of <code>numberOfSpaces</code>
	 * white spaces.
	 */
	private String spaces(int numberOfSpaces) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < numberOfSpaces; i++){
			builder.append(" ");
		}
		
		return builder.toString();
	}

	/**
	 * Class that represents one node in a tree.
	 * 
	 * @author Luka Grgić
	 */
	private static class Node {
		
		/**
		 * Node parents.
		 */
		private List<Node> parents;
		/**
		 * Node name.
		 */
		private String name;
		/**
		 * Node children.
		 */
		private List<Node> children;
		
		/**
		 * Constructor initializes object.
		 * 
		 * @param name Node name.
		 */
		public Node(String name) {
			this.parents = new ArrayList<>();
			this.name = name;
		}

		/**
		 * Initializes node children if necessary and returns it.
		 * 
		 * @return Node children.
		 */
		public List<Node> getChildren() {
			if (children == null) {
				children = new ArrayList<>();
			}
			
			return children;
		}

		/**
		 * Adds parent to the node.
		 * 
		 * @param parent Parent to be added.
		 * @throws IllegalTreeInsertionException If this node contains
		 * child named same as <code>parent</code>.
		 */
		public void addParent(Node parent) {	
			checkIfContainsChild(parent, getChildren());
			parents.add(parent);
		}
		
		/**
		 * Checks if node contains a child. If it does,
		 * exception is thrown.
		 * 
		 * @param targetNode Node that is searched for.
		 * @param children List in which <code>targetNode</code> is searched for.
		 * @throws IllegalTreeInsertionException If <code>targetNode</code>
		 * exists in the <code>children</code>.
		 */
		private void checkIfContainsChild(Node targetNode, List<Node> children) {
			for (Node node : children) {
				if (node.equals(targetNode)) {
					throw new IllegalTreeInsertionException("Can not add parent \"" + 
							targetNode.name + "\" to the node \"" + name + "\" "
							+ "because node \"" + name + "\" already has child "
							+ "named \"" + targetNode.name + "\".");
				}
				
				checkIfContainsChild(targetNode, node.children);
			}
		}

		/**
		 * Adds child to the node.
		 * 
		 * @param child Child to be added.
		 * @throws IllegalTreeInsertionException If this node contains parent
		 * named same as <code>child</code>, if this node has the same name
		 * as <code>child</code> or if this node already contains child named 
		 * same as <code>child</code>.
		 */
		public void addChild(Node child) {
			if (getChildren().contains(child)) {
				throw new IllegalTreeInsertionException("Can not repeat elements.");
			}
			
			if (this.equals(child)) {
				throw new IllegalTreeInsertionException("Parent and child can not be the same.");
			}
			
			checkIfContainsParent(child, parents);
			getChildren().add(child);
		}
		
		/**
		 * Checks if node contains a parent. If it does, 
		 * exception is thrown.
		 * 
		 * @param targetNode Node that is searched for.
		 * @param parents List in which <code>targetNode</code> is searched for.
		 * @throws IllegalTreeInsertionException If <code>targetNode</code>
		 * exists in the <code>parent</code>.
		 */
		private void checkIfContainsParent(Node targetNode, List<Node> parents) {
			for (Node node : parents) {
				if (node.equals(targetNode)) {
					throw new IllegalTreeInsertionException("Can not add child \"" + 
							targetNode.name + "\" to the node \"" + name + "\" "
							+ "because node \"" + name + "\" already has parent "
							+ "named \"" + targetNode.name + "\".");
				}
				
				checkIfContainsParent(targetNode, node.parents);
			}
			
		}
		
		/**
		 * Checks if node is empty. It is considered empty if <code>parents</code>
		 * and <code>children</code> lists do not contain any node.
		 * 
		 * @return True if <code>parents</code> and <code>children</code> 
		 * lists do not contain any node.
		 */
		public boolean isEmpty() {
			return parents.isEmpty() && children == null;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
	}
	
}
