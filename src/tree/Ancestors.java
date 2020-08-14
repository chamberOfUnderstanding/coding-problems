package tree;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Find the ancestors of a given node in a binary tree
 * 
 * http://www.geeksforgeeks.org/print-ancestors-of-a-given-node-in-binary-tree/
 * http://www.geeksforgeeks.org/print-ancestors-of-a-given-binary-tree-node-without-recursion/
 * 
 * =====================
 * METHOD 1 : Recursive
 * =====================
 * If the node is null, return false (Termination clause)
 * If this node has the data, return true (Target node has been found)
 * Else search for the target node in the left and right sub trees
 *  If either search results in a true, print the node and return true
 * If none of the above conditions hold, return false
 * 
 * This method prints the ancestors in reverse order
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * =====================
 * METHOD 2 : Iterative T_T
 * =====================
 * This is an extension of postorder traversal
 * If the node is null,return
 * As long as the node is not null and data is'nt found, push the node to a stack and move left
 * If the data is seen, break
 * Now the scan has reached the left most node
 * Check if the node has a right child, if not, then we have reached the end of a root to leaf path that does not contain the data
 *  So keep popping the stack until we hit a node who's the right child of another node (going back up the tree)
 *  This is like backtracking to a point from where we can go down another path and search for the data we want
 * Anywho, if the stack is empty return
 * Else set node as the right node of the top
 * Print the stack
 * 
 * TIME  : O(n)
 * SPACE : O(n)
 * 
 */

public class Ancestors {

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
		System.out.print("\nRecursive method : ");
		findAncestorsRecursive(node1, 3);
		System.out.print("\nIterative method : ");
		findAncestorsIterative(node1, 3);
	}

	private static boolean findAncestorsRecursive(Node node, int data) {
		if(node == null)
			return false;
		if(node.data == data)
			return true;
		if(findAncestorsRecursive(node.left, data) || findAncestorsRecursive(node.right, data)){
			System.out.print(node.data + " ");
			return true;
		}
		return false;
	}	

	private static void findAncestorsIterative(Node node, int data){
		if(node == null)
			return;
		Stack<Node> stack = new Stack<>();		
		while(true){
			while(node != null && node.data != data){
				stack.push(node);
				node = node.left;
			}
			if(node != null && node.data == data)
				break;
			if(stack.peek().right == null)
				node = stack.pop();
			while(!stack.isEmpty() && stack.peek().right == node)
                node = stack.pop();
			if(stack.isEmpty())
			    return;
			node = stack.peek().right;
		}
		while(!stack.isEmpty())
			System.out.print(stack.pop().data + " ");
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
