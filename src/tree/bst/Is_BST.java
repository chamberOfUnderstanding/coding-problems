package tree.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 47un
 * 
 * Check if the given binary tree is BST or not
 * 
 * https://www.hackerrank.com/challenges/is-binary-search-tree
 *
 * ============================
 * Method 1 : Inorder Traversal
 * ============================
 * Inorder traverse the tree
 * Save the traversal in a list
 * Verify whether the list is in ascending order
 * 
 * TIME  : O(n)
 * SPACE : O(n)
 *            
 * ====================
 * Method 2 : Recursion
 * ====================
 * Recursively check if every left node is < parent and right node is > parent
 * Wrapper will start things off by setting the minimum value as -infinity and maximum value as +infinity
 * If the node is null, return true (Empty tree is a BST) (Termination clause)
 * Every node is expected to fall in between minimum value and maximum value, else return false
 * Now recurse and check LST and RST for the BST property
 *  While doing this, make sure the maximum value is this node's value while going down the LST
 *   and minimum value is this node's value while going down the RST 
 * 
 * TIME  : O(n)
 * SPACE : O(n)
 * 			  
 */

public class Is_BST {

	public static void main(String[] args) {
		Node node13 = new Node(13);
		Node node7 = new Node(7);
		Node node9 = new Node(9);
		Node node8 = new Node(8);
		Node node4 = new Node(null, 4, node8);
		Node node5 = new Node(node9, 5, null);
		Node node12 = new Node(null, 12, node13);
		Node node11 = new Node(null, 11, node12);
		Node node6 = new Node(node11, 6, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node root = new Node(node2, 1, node3);
		System.out.println("Iterative " + isBST(root));
		System.out.println("Recursive " + isBSTRecursive(root));
	}

	static boolean isBST(Node root) {
		List<Integer> inorderTraversal = new ArrayList<>();
		inorderTraversal(root, inorderTraversal);
		return verifyInorderTraversal(inorderTraversal);
	}

	static boolean verifyInorderTraversal(List<Integer> inorderTraversal){
		for(int i = 1; i < inorderTraversal.size(); i++)
			if(inorderTraversal.get(i - 1) > inorderTraversal.get(i))
				return false;
		return true;
	} 

	static void inorderTraversal(Node node, List<Integer> inorderList){
		if(null == node)
			return;
		inorderTraversal(node.left,inorderList);
		inorderList.add(node.data);
		inorderTraversal(node.right,inorderList);
	}

	static boolean isBSTRecursive(Node root) {      
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	static boolean isBST(Node node, int minimumValue, int maximumValue){
		return  node == null ||
		        minimumValue < node.data &&
		        node.data < maximumValue &&
		        isBST(node.left, minimumValue, node.data) &&
		        isBST(node.right, node.data, maximumValue);
	}

	public static class Node{
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
		}

		public Node(Node left, int data, Node right) {
			this.left = left;
			this.data = data;
			this.right = right;
		}

		@Override
		public String toString() {
			return "" + data;
		}
	}
}
