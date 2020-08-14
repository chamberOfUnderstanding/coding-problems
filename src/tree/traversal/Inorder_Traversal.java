package tree.traversal;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Inorder traverse a binary tree
 * 
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 * 
 * ====================
 * METHOD 1 : Iterative
 * ====================
 * Root is null? return null (Corner case)
 * As long as there are nodes left to be processed
 *  Push all nodes into the stack until left most node is reached (Hence we move left after the push)
 *  For all nodes in the stack,
 *    Pop a node and print it
 *    Move right
 * Stack is empty? No more nodes left to process, hoist the flag that breaks the loop
 *
 * TIME     : O(n)
 * SPACE    : O(height)
 *
 * ====================
 * METHOD 2 : Recursive
 * ====================
 * Root is null? return null (Termination clause)
 * Recurse left
 * Print node
 * Recurse right
 *
 * TIME     : O(n)
 * SPACE    : O(height)
 * 
 */

public class Inorder_Traversal {

	public static void main(String...strings){
		Node node7 = new Node(null, 7, null);
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);
		
		System.out.print("\nIterative inorder traversal : ");
		iterativeInorderTraversal(node1);
		System.out.print("\nRecursive inorder traversal : ");
		recursiveInorderTraversal(node1);
	}

	private static void iterativeInorderTraversal(Node root){
		if(null == root)
			return;
		Stack<Node> stack = new Stack<>();
		Node current = root;
		boolean nodesLeftForProcessing = true;
		while(nodesLeftForProcessing){
			while(current != null){
				stack.push(current);
				current = current.left;
			}
			if(!stack.isEmpty()){
				current = stack.pop();
				System.out.print(current.data + " ");
				current = current.right;
			}
			else
				nodesLeftForProcessing = false;				
		}
	}

	private static void recursiveInorderTraversal(Node node) {
		if(null != node){
			recursiveInorderTraversal(node.left);
			System.out.print(node.data + " ");
			recursiveInorderTraversal(node.right);
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
