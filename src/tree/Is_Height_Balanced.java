package tree;

/**
 * @author 47un
 *
 * Check if the given binary tree is height balanced or not
 * 
 * http://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
 * http://stackoverflow.com/questions/742844/how-to-determine-if-binary-tree-is-balanced (Answer by jiaji.li)
 * 
 * ============================
 * METHOD 1 : Height comparison 
 * ============================
 * If root is null, return true
 * LST and RST are balanced? and the difference in their heights is <= 1 ? return true
 * 
 * Since height calculation occurs for each node, n*n => n^2
 *             
 * TIME    : O(n^2)
 * SPACE   : O(height)
 * 
 * =============================
 * METHOD 2 : Balance comparison
 * =============================
 * If node is null, return 0 (Balanced)
 * Get the balance of left sub tree
 *  If it's -1 left is imbalanced, return -1
 *  If it's -1 right is imbalanced, return -1
 *  If the difference between the balances of left and right is > 1, then the tree is imbalanced, return -1
 *  Else return 1 + Max balance   
 * 
 * TIME    : O(n)
 * SPACE   : O(height)
 * 
 */

public class Is_Height_Balanced {

	public static void main(String...strings){
//		Node node9 = new Node(null, 9, null);
//		Node node8 = new Node(null, 8, node9);
//		Node node7 = new Node(null, 7, node8);
		Node node7 = new Node(null, 7, null);
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);
		
		System.out.print("METHOD 1 : Is this tree height balanced? ");
		System.out.println(isHeightBalanced(node1)? "Yes " : "No ");
		System.out.print("METHOD 2 : Is this tree height balanced? ");
		System.out.println(isHeightBalancedPostorderTraversal(node1)? "Yes" : "No");		
	}

	private static boolean isHeightBalanced(Node root) {
		if(root == null)
			return true;
		return isHeightBalanced(root.left) &&
			   isHeightBalanced(root.right) &&
			   Math.abs(height(root.left) - height(root.right)) <= 1;
	}

	private static int height(Node root){		
		return root == null? 0 : 1 + Math.max(height(root.left), height(root.right));
	}

	private static boolean isHeightBalancedPostorderTraversal(Node root) {
		return isBalanced(root) != -1;
	}
	
	private static int isBalanced(Node node) {
		// null node is balanced
		if(node == null)
			return 0;
		int lst = isBalanced(node.left);
		if(lst == -1)
			return -1;
		int rst = isBalanced(node.right);
		if(rst == -1)
			return -1;
		return Math.abs(lst - rst) > 1 ?
				-1 
				: 
				1 + Math.max(lst, rst);
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
