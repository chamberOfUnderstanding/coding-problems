package tree.special;

/**
 * @author 47un
 *
 * Implement a ternary search tree
 *  
 * http://www.geeksforgeeks.org/ternary-search-tree/
 * http://ideone.com/0WdSsK
 * http://algs4.cs.princeton.edu/lectures/52Tries.pdf?bcsi_scan_64741547a7fe261b=0&bcsi_scan_filename=52Tries.pdf
 * https://www.cs.usfca.edu/~galles/visualization/TST.html
 * https://discuss.leetcode.com/topic/14569/ternary-search-trie-implementation-with-java
 * http://www.sanfoundry.com/java-program-ternary-search-tree/
 * 
 * Insertion
 * =========
 * Get the character to be inserted
 * If the node is null, turn the current character to a node and instantiate it
 * If the character to be inserted < current node
 *  Move left for the same index (Because the character has not been inserted yet!)
 *  Whatever value is returned after processing the LST, becomes the left child of current node
 * If the character is > current node
 *  Do the same as above, but for the right sub tree
 * Else the character = current node
 *  So this node's middle = result of inserting the next character in the middle sub tree
 *  Since the next character is to be processed, make sure the index is within bounds
 * Else
 *  Mark the end of string as true for this node
 * Return the node 
 *  
 * Search
 * ====== 
 * During a search, once you see character match, look for the rest of the string by scanning just the middle child.
 * If this scan leads to a null, then bingo! else the string is not in the tree
 * 
 * If the node is null, return false
 * Get the character to be looked for
 * If this character is < current node, return the status of searching for this character in the LST
 * If this character is > current node, return the status of searching for this character in the RST
 * If this character is = current node, return the status of searching for the next character in the MST
 *  Make sure index is within bounds
 * Return the value of end of string for this node   
 *  
 * TIME    : O()
 * SPACE   : O()
 * 
 */

public class Ternery_Search_Tree {

	private static Node root = null;

	private class Node{
		char data;
		Node left;
		Node middle;
		Node right;
		boolean endOfString;

		Node(char data){
			this.data = data;
		}
	}

	public static void main(String...strings){
		Ternery_Search_Tree ternarySearchTree = new Ternery_Search_Tree();
		String string1 = "BROOKE";
		String string2 = "BREAK";
		String string3 = "BRICKBREAK";
		String string4 = "CALLISTHENICS";
		ternarySearchTree.insert(string1);
		ternarySearchTree.insert(string2);
		ternarySearchTree.insert(string3);
		System.out.println(string1 + " " + ternarySearchTree.search("BROOKE", root));
		System.out.println(string2 + " " + ternarySearchTree.search("BREAK", root));
		System.out.println(string3 + " " + ternarySearchTree.search("BRICKBREAK", root));
		System.out.println(string4 + " " + ternarySearchTree.search("CALLISTHENICS", root));
		ternarySearchTree.delete(string1);
		ternarySearchTree.delete(string2);
		ternarySearchTree.delete(string3);
		ternarySearchTree.insert(string4);
		System.out.println(string1 + " " + ternarySearchTree.search("BROOKE", root));
		System.out.println(string2 + " " + ternarySearchTree.search("BREAK", root));
		System.out.println(string3 + " " + ternarySearchTree.search("BRICKBREAK", root));
		System.out.println(string4 + " " + ternarySearchTree.search("CALLISTHENICS", root));
	}

	private void insert(String word) {
		if(word != null && !word.isEmpty())
			root = insert(word, 0, root);
	}

	private Node insert(String word, int index, Node node) {
		char charToBeInserted = word.charAt(index);
		if(node == null)
			node = new Node(charToBeInserted);
		if(charToBeInserted < node.data)
			node.left = insert(word, index, node.left);
		else if(charToBeInserted > node.data)
			node.right = insert(word, index, node.right);
		else if(index < word.length() - 1)
			node.middle = insert(word, index + 1, node.middle);
		else
			node.endOfString = true;
		return node;
	}

	private boolean search(String word, Node root) {
		return word != null && root != null && !word.isEmpty() && search(word, 0, root);
	}

	private boolean search(String word, int index, Node node) {
		if(node == null)
			return false;
		char charToBeSearched = word.charAt(index);
		if(charToBeSearched < node.data)
			return search(word, index, node.left);
		else if(charToBeSearched > node.data)
			return search(word, index, node.right);
		else if(index < word.length() - 1)
			return search(word, index + 1, node.middle);
		return node.endOfString;
	}

	private boolean delete(String word) {
		return word != null && !word.isEmpty() && delete(word, 0, root);
	}
	
	// TODO Broken
	private boolean delete(String word, int index, Node node){
		if(node == null)
			return false;
		char charToBeDeleted = word.charAt(index);
		if(charToBeDeleted < node.data && delete(word, index, node.left))
			node.left = null;
		else if(charToBeDeleted > node.data && delete(word, index, node.right))
			node.right = null;
		else if(index < word.length() - 1 && delete(word, index + 1, node.middle))
			node.middle = null;
		return node.endOfString;
	}
}
