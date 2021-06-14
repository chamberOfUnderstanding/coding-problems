package tree;

/**
 * @author 47un
 * 
 * Get the maximum sum among all root to leaf paths
 * 
 * http://www.geeksforgeeks.org/find-the-maximum-sum-path-in-a-binary-tree/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If node is empty, return 0
 * If it's a leaf node
 *  You have reached the end of a path
 *  Return sum + node.data  
 * Recurse for LST and RST, get their sum and return the maximum value among them
 *           
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Root_To_Leaf_Path_Max_Sum {

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

		System.out.println("Maximum root to leaf path sum is : " + findMaximumRootToLeafPathSum(node1, 0));
	}

	private static int findMaximumRootToLeafPathSum(Node node, int sum) {
		if(node == null)
			return 0;		
		// update the sum with current node's value
		int updatedSum = sum + node.data;
		// if node is a leaf, then cant process any further, return the updatedSum
		if(isLeaf(node))
			return updatedSum;
		// else visit the kids and let them add to the updatedSum
		// this node returns the largest among those
		return Math.max(
				findMaximumRootToLeafPathSum(node.left, updatedSum),
				findMaximumRootToLeafPathSum(node.right, updatedSum)
				);
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