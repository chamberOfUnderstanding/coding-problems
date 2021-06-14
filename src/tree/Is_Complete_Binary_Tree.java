package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 *
 * Check if the given tree is a complete binary tree or not 
 * A complete binary tree has all level filled, except the last.
 * The last level may or may not be filled. If it is filled, the filling should happen from left to right.
 * A heap is a complete binary tree
 * 
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================ 
 * Perform a typical level order traversal
 * 	Dequeue a node
 * 	Get its children
 * 	If it has a right and no left => Not Complete (Should never happen in a complete tree)
 * 	If it has a left and no right, listen carefully :
 *     If this is the first time such a node has been encountered, hoist the flag.
 * 	   Since there's not right, none of the remaining nodes in the queue should have any children (they all must be leaves)
 *     So we hoist a flag & if any other dequeued node has a child and the flag is up and "flagging", the tree is NOT complete
 * If the current node survives till here, enqueue its non null children
 * Return true if the input survives till here
 *             
 * TIME    : O(n)
 * SPACE   : O(n)
 * 
 * =====================
 * METHOD 2 : Recursion
 * ===================== 
 * A right child only node? Not complete
 * If you see a non leaf when only leaves are allowed, Not Complete
 * If the node is left child only, you can only see leaves now onwards
 * None of the above conditions hold? Proceed normally
 *
 * TIME    : O(n)
 * SPACE   : O(n)
 * 
 */

public class Is_Complete_Binary_Tree {

	public static void main(String...strings){
		Node node7 = new Node(null, 7, null);
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);
		
		System.out.println("Is this tree Complete? " + (isCompleteRecursive(node1, false)? "Yes" : "No"));
		System.out.println("Is this tree Complete? " + (isCompleteIterative(node1)? "Yes" : "No"));
	}

	private static boolean isCompleteIterative(Node root) {
		Node current      = null;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		boolean onlyLeavesCanBeSeen = false;
		while(!queue.isEmpty()){
			current = queue.remove();
			Node leftChild  = current.left;
			Node rightChild = current.right;
			if(rightChildOnly(current))
				return false;
			else if(leftChildOnly(current)){
				if(onlyLeavesCanBeSeen)
					return false;
				else
					onlyLeavesCanBeSeen = true;
			}
			if(leftChild != null)
				queue.add(leftChild);
			if(rightChild != null)
				queue.add(rightChild);
		}
		return true;
	}

	private static boolean isCompleteRecursive(Node node, boolean leavesOnly){
		if(node == null)
			return true;
		if(rightChildOnly(node) || leavesOnly && !isLeaf(node))
			return false;
		else if(leftChildOnly(node))
			leavesOnly = true;
		return isCompleteRecursive(node.left,  leavesOnly) && 
			   isCompleteRecursive(node.right, leavesOnly);
	}

	private static boolean isLeaf(Node node){
		return node.left == null && node.right == null;
	}

	private static boolean rightChildOnly(Node node){
		return node.right != null && node.left == null;	
	}

	private static boolean leftChildOnly(Node node){
		return node.left != null && node.right == null;
	}

	@SuppressWarnings("unused")
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
