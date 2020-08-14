package tree;

/**
 * @author 47un
 * 
 * Check if two binary trees are mirror images of each other
 * eg : The below trees are mirror images of each other
 * 
 *          1                     1  
 *       /    \                 /    \
 *      2      3       =>      3      2  
 *     / \    / \             / \    / \
 *    4   5  6   7           7   6  5   4
 *              /             \
 *             8               8
 *              \             /
 *               9           9
 * 
 * http://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If both nodes are null, return true (Termination clause)
 * If either node is null, return false (Termination clause)
 * There is symmetry if the nodes have same data & the LST of node1 is the mirror of RST of node2 & viz.
 *
 * TIME     : O(max(n1, n2))
 * SPACE    : O(h)
 *
 * 
 */

public class Is_Mirror_Image {
	
	public static void main(String...strings){
		Node node9 = new Node(null, 9, null);
		Node node8 = new Node(node9, 8, null);
		Node node7 = new Node(null, 7, node8);
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);

		Node node29 = new Node(null, 9, null);
		Node node28 = new Node(null, 8, node29);
		Node node27 = new Node(node28, 7, null);
		Node node26 = new Node(null, 6, null);
		Node node25 = new Node(null, 5, null);
		Node node24 = new Node(null, 4, null);
		Node node23 = new Node(node27, 3, node26);
		Node node22 = new Node(node25, 2, node24);
		Node node21 = new Node(node23, 1, node22);
		
		System.out.print("Are they mirror images? : " + areTheyMirrored(node1, node21));		
	}

	private static boolean areTheyMirrored(Node node1, Node node2) {
		if(null == node1 && null == node2)
			return true;
		if(null == node1 || null == node2)
			return false;
		return node1.data == node2.data &&
		       areTheyMirrored(node1.left, node2.right) &&
		       areTheyMirrored(node1.right, node2.left);
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
