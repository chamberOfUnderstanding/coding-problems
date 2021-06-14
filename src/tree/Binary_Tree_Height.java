package tree;

/**
 * @author 47un
 * 
 * Find the height of the given binary tree
 * 
 * http://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If the node is null, return 0 (Termination clause, a null node does not contribute to the tree's height)
 * Else the height of the subtree rooted at this node is the maximum of the heights of its LST and RST
 * Return 1 + maximum height among LST, RST (1 as this node will contribute to the height)
 *
 * TIME     : O(n)
 * SPACE    : O(n)  // No tail recursion
 * 
 */

public class Binary_Tree_Height {
	
	public static void main(String...strings){
		Node node7 = new Node(null, 7, null);
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);
		System.out.print("\nHeight of the tree : "+ height(node1));		
	}
	
	private static int height(Node node){
		return (node == null) ? 
				0 
				: 
				1 + Math.max(height(node.left), height(node.right));
	}
	
	@SuppressWarnings("unused")
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
