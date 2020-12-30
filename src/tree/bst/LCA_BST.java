package tree.bst;

/**
 * @author 47un
 *
 * Given pointer to the root of the binary search tree and two values data1 and data2 
 * Find the lowest common ancestor (LCA) of the nodes with the data data1 and data2 in the binary search tree.
 * 
 * ====================
 * METHOD 1 : Recursive
 * ==================== 
 *             

 * 
 * ====================
 * METHOD 2 : Iterative
 * ==================== 
 * The auxiliary space of n can be avoided by using iteration.
 * Set the current node as root
 *  Iterate until current node is not null
 *   If both data are greater than current node's data, move right
 *   If both data are less than current node's data, move left
 *   Else break (This is the LCA)
 * 
 * TIME    : O(n)
 * SPACE   : O(1)
 * 
 */

public class LCA_BST{	
	
	public static void main(String[] args) {
		/*
		 *              10
		 *           /     \
		 *          2      15
		 *           \    /  \
		 *            5  12  27
		 */
		Node node5 = new Node(5);
		Node node12 = new Node(12); 
		Node node27 = new Node(27);
		Node node15 = new Node(node12, 15, node27);
		Node node2 = new Node(null, 2, node5);
		Node node10 = new Node(node2, 10, node15);
		
		System.out.println("Recursive method : " + lcaBSTRecursive(node10, 12, 27).data);
		System.out.println("Iterative method : " + lcaBSTIterative(node10, 12, 27).data);
	}
	
	/*
	 * TIME    : O(n)
	 * SPACE   : O(h)
	 */
	static Node lcaBSTRecursive(Node node, int data1, int data2) {
		if(node == null)
			return null;
		
		// If both data is greater than the root's data then LCA is in the RST as we need a larger value
		if(data1 > node.data && data2 > node.data)			
			return lcaBSTRecursive(node.right, data1, data2);
		
		// Else if both data is smaller than the root's data then LCA is in the LST as we need a smaller value
		else if(data1 < node.data && data2 < node.data)
			return lcaBSTRecursive(node.left, data1, data2);
		return node;
	}
	
	static Node lcaBSTIterative(Node root, int data1, int data2) {
		Node current = root;
		while(current != null)
			if(data1 > current.data && data2 > current.data)
				current = current.right;
			else if(data1 < current.data && data2 < current.data)
				current = current.left;
			else
				break;
		return current;
	}
	
	private static class Node{
		int data;
		Node left;
		Node right;
		
		public Node(Node left, int data, Node right) {
			this.left = left;
			this.data = data;
			this.right = right;
		}

		public Node(int data) {
			this.data = data;
		}
	}
}
