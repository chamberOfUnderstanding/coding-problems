package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author 47un
 *
 * Reverse alternate levels of a binary tree
 * 
 * http://www.geeksforgeeks.org/reverse-alternate-levels-binary-tree/
 * 
 * Given tree: 
 *                a
 *             /     \
 *            b       c
 *          /  \     /  \
 *         d    e    f    g
 *        / \  / \  / \  / \
 *        h  i j  k l  m  n  o 
 * 
 * Modified tree:
 *                a
 *             /     \
 *            c       b
 *          /  \     /  \
 *         d    e    f    g
 *        / \  / \  / \  / \
 *       o  n m  l k  j  i  h 
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * The call is made with the left and right children of the root and NOT WITH THE ROOT
 * If either node is null, return
 * If the level is even, swap the data of left and right children
 * Notice the recursive calls send node1.left, node2.right and node1.right, node2.left
 *  If the calls were done using node1.left, node1.left and node1.right, node2.right
 *   then the result would have just the children of one parent swapped and this wont recursively apply the swaps
 *   e.g. 1 2 3 6 7 4 5 8 9 is the result for such a call while 1 2 3 7 6 5 4 8 9 is the correct output.
 *        6 and 7 did'nt get swapped, so did 4 and 5
 *        The above e.g. was for level starting with 1 instead of 0 (odd level swaps)
 *              
 * TIME    : O(n)
 * SPACE   : O(h)
 * 
 * ================
 * METHOD 2 : Stack
 * ================
 * Traverse the tree and store the odd level nodes on a stack
 * Traverse the tree again and replace the odd level nodes with the one's popped from stack
 * 
 * TIME    : O(n) // More space than above method
 * SPACE   : O(h)
 * 
 * 
 */

public class Reverse_Alternate_Levels {

	public static void main(String...strings){
		Node node9 = new Node(null, 9, null);
		Node node8 = new Node(null, 8, null);
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node7 = new Node(null, 7, node9);
		Node node4 = new Node(node8, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);

		System.out.print("Reversing alternate levels recursively : ");
		reverse_Alternate_Levels_Preorder(node1.left, node1.right, 0);
		levelOrder(node1);

		System.out.print("\nReversing alternate levels again, using stack : ");
		reverseAlternateLevelsStack(node1);
		levelOrder(node1);
	}

	private static void reverse_Alternate_Levels_Preorder(Node node1, Node node2, int level){
		if(node1 == null || node2 == null)
			return;
		if(level % 2 == 0)
			swapData(node1, node2);
		reverse_Alternate_Levels_Preorder(node1.left, node2.right, level + 1);
		reverse_Alternate_Levels_Preorder(node1.right, node2.left, level + 1);
	}

	private static void swapData(Node node1, Node node2) {
		node1.data ^= node2.data;
		node2.data ^= node1.data;
		node1.data ^= node2.data;
	}

	private static Stack<Integer> stack = new Stack<>();

	private static void reverseAlternateLevelsStack(Node root) {
		inorderTraverse(root, 0, false);
		inorderTraverse(root, 0, true);
	}

	private static void inorderTraverse(Node node, int level, boolean reverse) {
		if(node != null){
			inorderTraverse(node.left, level + 1,reverse);
			if(level % 2 != 0)
				if(reverse)
					node.data = stack.pop();
				else
					stack.push(node.data);
			inorderTraverse(node.right, level + 1,reverse);
		}	
	}

	private static void levelOrder(Node root)
	{
		Queue<Node> queue = new LinkedList<Node>();
		if(root != null){
			queue.add(root);
			while(!queue.isEmpty()){
				Node node = queue.remove();
				System.out.print(node.data + " ");
				if(node.left != null)
					queue.add(node.left);
				if(node.right != null)
					queue.add(node.right);
			} 
		}       
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
