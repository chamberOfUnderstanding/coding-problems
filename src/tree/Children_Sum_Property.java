package tree;

/**
 * @author 47un
 * 
 * Children sum property is : For every node, data value must be equal to sum of data values in left and right children.
 * Consider data value as 0 for NULL children.
 * Check if the given tree obeys this property
 * 
 * http://www.geeksforgeeks.org/check-for-children-sum-property-in-a-binary-tree/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If the node is null or is a leaf node, return true (Termination clause)
 * Calculate the sum of left and right children. If either child is null, take the value as 0
 * This sum has to be = node.data and recursively check if the left and right sub trees obey this property
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Children_Sum_Property {
    
	public static void main(String...strings){
		Node node7 = new Node(null, 9, null);
		Node node6 = new Node(null, -6, null);
		Node node5 = new Node(null, -6, null);
		Node node4 = new Node(null, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, -2, node5);
		Node node1 = new Node(node2, 1, node3);
		System.out.print("\nSatisfies Children Sum property?  : " + (isChildrenSumPropertyValid(node1)? "Yes" : "No"));	
	}

	private static boolean isChildrenSumPropertyValid(Node node) {
		if(node == null || isLeaf(node))
			return true;
		int sum = (node.left == null? 0 : node.left.data) + (node.right == null? 0 : node.right.data);
		return sum == node.data && isChildrenSumPropertyValid(node.left) && isChildrenSumPropertyValid(node.right);
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
