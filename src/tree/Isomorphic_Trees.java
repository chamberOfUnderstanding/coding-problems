package tree;

/**
 * @author 47un
 * 
 * Check if two trees are isomorphic.
 * Two trees are called isomorphic if one of them can be obtained from other by a series of flips, i.e. by swapping left and right children of a number of nodes.
 * Any number of nodes at any level can have their children swapped.
 * Two empty trees are isomorphic.
 * 
 * http://www.geeksforgeeks.org/tree-isomorphism-problem/
 * 
 *      1
 *    /   \
 *   2     3
 *  / \   /
 * 4   5 6 
 *    / \
 *   7   8
 *   
 *     1
 *   /   \
 *  3     2
 *   \   / \
 *    6 4   5 
 *         / \
 *        7   8
 * 
 * =========
 * METHOD 1
 * =========
 * If both nodes are null, return true
 * If either is null, return false
 * If both are not null :
 *  The data of the nodes MUST be equal
 *  And either of the following should hold : 
 *    Left subtrees and right sub trees are isomorphic
 *    Left subtree is isomorphic with the right sub tree of the other node and viz.
 * 
 * TIME     : O(m + n)
 * SPACE    : O(largest(m,n))
 * 
 */

public class Isomorphic_Trees {

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
		System.out.println(areTheyIsomorphic(node1, node2));
	}

	private static boolean areTheyIsomorphic(Node node1, Node node2) {
		if(node1 == null && node2 == null)
			return true;
		if(node1 == null ^ node2 != null)
			return false;
		return (node1.data == node2.data) && 
		       (areTheyIsomorphic(node1.left, node2.left) && areTheyIsomorphic(node1.right, node2.right)) ||
			   (areTheyIsomorphic(node1.left, node2.right) && areTheyIsomorphic(node1.right, node2.left));
	}

    private static class Node{
        int data;
        Node left;
        Node right;

        Node(Node left,int data,Node right){            
            this.left = left;
            this.data = data;
            this.right = right;         
        }
    }
}
