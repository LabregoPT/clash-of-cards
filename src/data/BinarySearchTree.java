package data;
import java.util.Comparator;
import java.util.Random;
import model.*;

/**
 * Data structure that will contain the different Cards to the event in a Binary Search Tree.
 * @author Jhon Edward Mora / Juan Andrés Orozco - Universidad ICESI
 * @version 1.0a - 05/2019
 */
public class BinarySearchTree {
	//Attributes
	/**Sorting criteria for the binary search tree*/
	private Comparator<Card> c;
	
	//Nested classes.
	/**Nested class to handle each Card as a node in the binary search tree data structure*/
	private class Node extends Card{
		//Relations
		/**Left child node of this node.*/
		private Node left;
		/**Right child node of this node.*/
		private Node right;
		/**Parent node of this node.*/
		private Node p;
		
		//Methods
		/**Simple constructor.*/
		public Node(Card e) {
			super(e.getName(), e.image, e.getAttributes());
		}
		
		/**Returns the left child node of this node in the BST*/
		public Node getLeft() {
			return left;
		}
		
		/**Returns the right child node of this node in the BST*/
		public Node getRight() {
			return right;
		}
		
		/**Returns the parent node of this node in the BST.*/
		public Node getP() {
			return p;
		}
		
		/**
		 * Sets the left child node of this BNS to the one in the argument.
		 * @param e node to be set as left child node.
		 */
		public void setLeft(Node e) {
			left = e;
		}
		
		/**
		 * Sets the right child node of this BNS to the one in the argument.
		 * @param e node to be set as right child node.
		 */
		public void setRight(Node e) {
			right = e;
		}
		
		/**
		 * Sets the parent node of this BNS to the one in the argument.
		 * @param e node to be set as parent node.
		 */
		public void setP(Node e) {
			p = e;
		}
	}

	//Relations
	private Node root;
	
	//Methods
	/**
	 * Constructor method. Initialises an instance of the class.
	 * @param comparator the sorting criteria for the binary search tree.
	 */
	public BinarySearchTree(Comparator<Card> comparator) {
		this.c = comparator;
	}
	
	/**
	 * Checks if the Binary search tree is empty and then proceeds to add the given element to it.
	 * @param element the element (node) to be added.
	 */
	public void add(Card element) {
		Node toAdd = new Node(element);
		if(root == null) {
			root = toAdd;
		}else {
			add(toAdd, root);
		}
	}
	
	/**
	 * Recursive function to add an element to a binary search tree.
	 * @param a the element (node) to be added.
	 * @param current the current element in which this recursive function is located.
	 */
	private void add(Node a, Node current) {
		if(c.compare(a, current) <= 0) {
			if(current.getLeft() == null) {
				current.setLeft(a);
				a.setP(current);
			}else {
				add(a, current.getLeft());
			}
		}else {
			if(current.getRight() == null) {
				current.setRight(a);
				a.setP(current);
			}else {
				add(a, current.getRight());
			}
		}
	}
	
	/**
	 * Calls the recursive function to get the minimum element in this binary search tree.
	 * @return the minimum element in this binary search tree.
	 */
	public Card getMin() {
		return getMin(root);
	}
	
	/**
	 * Calls itself until finding a Node that has no child left Node (minimum element in the binary search tree)
	 * @param current the current Node in the recursive function.
	 * @return the element to the left of the argument's node, or itself if there's no element to the left.
	 */
	private Node getMin(Node current) {
		if(current.getLeft() == null) {
			return current;
		}else {
			return getMin(current.getLeft());
		}
	}
	
	/**
	 * Calls the recursive function to get the maximum element in this binary search tree.
	 * @return the maximum element in this binary search tree.
	 */
	public Card getMax() {
		return getMax(root);
	}
	
	/**
	 * Calls itself until finding a Node that has no child right Node (maximum element in the binary search tree)
	 * @param current the current Node in the recursive function.
	 * @return the element to the right of the argument's node, or itself if there's no element to the right.
	 */
	private Node getMax(Node current) {
		if(current.getRight() == null) {
			return current;
		}else {
			return getMin(current.getRight());
		}
	}
	
	/**
	 * Calls the recursive function to search a given element in the binary search tree.
	 * @param toSearch the given element to be searched.
	 * @return either the element matching the searched element, or null if the tree is empty or doesn't have the searched element.
	 */
	public Card search(Card toSearch) {
		Node n = new Node(toSearch);
		return search(n, root);
	}
	
	/**
	 * Calls itself until finding an element that is equal to the current checked node, or a null element.
	 * @param toSearch the element to be searched.
	 * @param current the current element to be compared with the searched element.
	 * @return the left element of the current element if the searched element is lesser or equals than the current, right if not. May be null.
	 */
	private Node search(Node toSearch, Node current) {
		if(current == null || c.compare(toSearch, current) == 0) {
			return current;
		}else {
			if(c.compare(toSearch, current) <= 0) {
				return search(toSearch, current.getLeft());
			}else {
				return search(toSearch, current.getRight());
			}
		}
	}
	
	/**
	 * Returns the root of the tree
	 * @return the root of the tree with all of it's child nodes.
	 */
	public Card getRoot() {
		return root;
	}
	
	/**
	 * Calls the recursive function to get a random node and returns it's result.
	 * @return a random element of the tree.
	 */
	public Card getRandom() {
		return getRandom(root);
	}
	
	/**
	 * Calls itself a random number of times until finding a node or a null node.
	 * @param current the current node in the recursive function.
	 * @return either the child left, child right or parent node of this node, or null.
	 */
	private Node getRandom(Node current) {
		if(current == null) {
			return current;
		}else {
			Random rnd = new Random();
			int pointer = rnd.nextInt(3);
			if(pointer == 0) {
				return getRandom(current.getP());
			}else if(pointer == 1) {
				return getRandom(current.getLeft());
			}else {
				return getRandom(current.getRight());
			}
		}
	}
	
	/**
	 * Returns the successor in preorder of a given Card
	 * @param current given Card
	 * @return successor in preorder of the given Card
	 */
	public Card successorPreOrder(Card current) {
		Node c = new Node(current);
		return preOrderRec(c);
	}
	
	/**
	 * Returns the successor in preorder of a given Node
	 * @param current given Node
	 * @return successor in preorder of the given Node
	 */
	private Node preOrderRec(Node c) {
		if(c.getLeft() != null) {
			return c.getLeft();
		}else {
			Node p = c.getP();
			while(p!= null && p.getRight() == c) {
				c = c.getP();
				p = p.getP();
			}
			if(p == null) {
				return p;
			}else {
				return p.getRight();
			}
		}
	}
	
	
	public void delete(Card c) {
		Node todelete = new Node(c);
		delete(search(root, todelete));
	}
	
	private void delete(Node todelete) {
		if(todelete != null) {
			if(todelete.getLeft() == null && todelete.getRight() == null) {	
				if(todelete.getP().getLeft() == todelete) {
					todelete.getP().setLeft(null);
				}else {
					todelete.getP().setRight(null);
				}
			}else if (todelete.getLeft() != null && todelete.getRight() == null) {
				if(todelete.getP().getLeft() == todelete) {
					todelete.getP().setLeft(todelete.getLeft());
				}else {
					todelete.getP().setRight(todelete.getLeft());
				}
			}else if(todelete.getLeft() == null && todelete.getRight() != null) {
				if(todelete.getP().getLeft() == todelete) {
					todelete.getP().setLeft(todelete.getRight());
				}else {
					todelete.getP().setRight(todelete.getRight());
				}
			}
		}
	}
	
}
