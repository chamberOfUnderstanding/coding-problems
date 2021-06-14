package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Check if two trees are identical
 * 
 * http://www.geeksforgeeks.org/iterative-function-check-two-trees-identical/
 * 
 * ====================
 * METHOD 1 : Recursive
 * ====================
 * Return false if either node is null (Termination clause)
 * If they are both not null, compare the data and recurse for lst and rst
 * If they are both null, return true
 *            
 * TIME     : O(n)  // Number of nodes
 * SPACE    : O(h)  // Height
 * 
 * ====================
 * METHOD 2 : Iterative
 * ====================
 * If both roots are null, return true
 * If either roots are null, return false
 * Level Order Traverse both trees & compare the dequeued data 
 *  After two nodes of each tree have been dequeued
 *   Compare their data, different? return false
 *   Check if they both have/not have left and right children, if one has a child the other doesnt have, return false 
 *   If it reaches here, both nodes have the same data and they both have children at the same position (left or right) 
 *   Enqueue the children on the respective queues and repeat        
 *            
 * TIME     : O(n)  // Number of nodes
 * SPACE    : O(n)
 *
 * 
 */

public class Identical_Trees {
    
	public static void main(String...strings){
		Node node7 = new Node(null, 7, null);
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);
		Node node12 = new Node(node2, 1, node4);
		Node node11 = new Node(node12, 11, node7);

		System.out.print("\nThe trees are " + (areTheyIdenticalRecursive(node1, node11)? "" : "not ") + "identical");
		System.out.print("\nThe trees are " + (areTheyIdenticalIterative(node1, node11)? "" : "not ") + "identical");
	}

	private static boolean areTheyIdenticalRecursive(Node node1, Node node2) {
		// xor for null check
		// xor gives true if they are different
		if(node1 == null ^ node2 == null)
			return false;
		if(node1 != null && node2 != null)
			return (node1.data == node2.data) && 
			        areTheyIdenticalRecursive(node1.left, node2.left) && 
			        areTheyIdenticalRecursive(node1.right, node2.right);
		return true;
	}

	private static boolean areTheyIdenticalIterative(Node root1, Node root2){
		if(root1 == null && root2 == null)
			return true;
		if(root1 == null ^ root2 == null)
			return false;
		Queue<Node> queue1 = new LinkedList<Node>();
		Queue<Node> queue2 = new LinkedList<Node>();
		queue1.add(root1);
		queue2.add(root2);
		while(!queue1.isEmpty() && !queue2.isEmpty()){
			Node node1 = queue1.remove();
			Node node2 = queue2.remove();
			if(node1.data != node2.data)
				return false;
			if((node1.left == null ^ node2.left == null) || (node1.right == null ^ node2.right == null))
				return false;
			if(node1.left != null){
				queue1.add(node1.left);
				queue2.add(node2.left);
			}
			if(node1.right != null){
				queue1.add(node1.right);
				queue2.add(node2.right);
			}
		}
		return true;
	}

	private static class Node{
		int data;
		Node left;
		Node right;

		Node(Node left, int data, Node right){
			this.data = data;
			this.left = left;
			this.right = right;			
		}
	}
}
