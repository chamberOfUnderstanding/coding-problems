package tree;

/**
 * @author 47un
 * 
 * A SumTree is a Binary Tree where the value of a node is equal to sum of the nodes present in its left subtree and right subtree. 
 * An empty tree is SumTree and sum of an empty tree can be considered as 0. 
 * A leaf node is also considered as SumTree.
 * 
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If the node is null or is a leaf node, return true (Termination clause)
 * If LST and RST are sum trees, then check this node for Sum Tree property
 *   left sum = 0 (if left child is null)
 *   left sum = left node's data (if left child is a leaf)
 *   left sum = 2 * left node's data (if left child is NOT a leaf) (Think about it!)
 *   Similarly right sum
 *   If left sum + right sum = node data then return true
 *
 * TIME     : O(n)
 * SPACE    : O(h)
 * 
 * Tree in the input is :
 *          26
 *         /  \
 *        10   3
 *       /  \   \
 *      4    6   3
 * 
 * If a node is NOT a leaf, then the value returned by it will be node's value + LST sum + RST sum.
 * If LST sum + RST sum gives node's value (LST and RST obey sum tree property) then the final return value will be 2 * node's value 
 * 
 */

public class Is_Sum_Tree {

	public static void main(String...strings){
		Node node6 = new Node(6);
		Node node4 = new Node(4);
		Node node3a = new Node(3);		
		Node node3 = new Node(null, 3, node3a);
		Node node10 = new Node(node4,  10,  node6);
		Node node1 = new Node(node10, 26, node3);
		
		System.out.print("Is this a sum tree? : " + (isSumTree(node1)? "Yes" : "No"));		
	}

	private static boolean isSumTree(Node node) {
		if(node == null || isLeaf(node))
			return true;
		if(isSumTree(node.left) && isSumTree(node.right)){
			int leftSum  = node.left  == null? 0 : node.left.data  * (isLeaf(node.left)  ? 1 : 2);
			int rightSum = node.right == null? 0 : node.right.data * (isLeaf(node.right) ? 1 : 2);			
			return leftSum + rightSum == node.data;				
		}
		return false;
	}

	private static boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}
	   
    private static class Node{
        int data;
        Node left;
        Node right;
        
        public Node(int data) {
        	this.data = data;
        }

        Node(Node left, int data, Node right){            
            this.left = left;
            this.data = data;
            this.right = right;         
        }
    }
}
