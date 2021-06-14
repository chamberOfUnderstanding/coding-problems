package tree;

/**
 * @author 47un
 * 
 * Count leaf nodes of a binary tree
 * 
 * http://www.geeksforgeeks.org/write-a-c-program-to-get-count-of-leaf-nodes-in-a-binary-tree/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If node is null, return 0, If node is a leaf, return 1 (Termination Clauses)
 * Recursively calculate leaves of left and right sub trees, add them and return it            
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Count_Leaf_Nodes {
    
	public static void main(String...strings){
		Node node7 = new Node(null, 7, null);
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);
		System.out.print("Number of leaf nodes : " + countLeafNodes(node1));	
	}

	private static int countLeafNodes(Node node) {
		return ( node == null ) ? 
				0 
				: 
				isLeaf(node) ? 
						1 
						: 
						countLeafNodes(node.left) + countLeafNodes(node.right);
	}

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
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
