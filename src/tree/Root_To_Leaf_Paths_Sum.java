package tree;

/**
 * @author 47un
 * 
 * In a binary tree, each node holds a digit and the root to leaf paths forms numbers.
 * Find the sum of the numbers formed by all root to leaf paths
 * 
 * http://www.geeksforgeeks.org/sum-numbers-formed-root-leaf-paths/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * Empty node? return 0 (Termination clause)
 * Update the number formed so far down this path with the value of the current node.
 * If this is a leaf node, then a path has been fully scanned, return the number
 * Get the numbers formed by LST and RST, add them up and return
 *           
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Root_To_Leaf_Paths_Sum {

	public static void main(String...strings){
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node9 = new Node(null, 9, null);
		Node node8 = new Node(null, 8, node9); 
		Node node7 = new Node(null, 7, node8);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);

		System.out.println(findSum(node1, 0));
	}

	private static int findSum(Node node, int number) {
		if(node == null)
			return 0;
		// update the number
		number = number * 10 + node.data;
		// if it's a leaf, return it
		if(isLeaf(node))
			return number;
		// else add the sums of the LST and RST
		return findSum(node.left, number) 
				+ findSum(node.right, number);
	}

	private static boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}

	private static class Node{
		int data;
		Node left;
		Node right;

		Node(Node left, int data, Node right){			
			this.left = left;
			this.data = data;
			this.right = right;			
		}
	}
}