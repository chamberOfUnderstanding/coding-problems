package tree.traversal;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Postorder traverse a given binary tree
 * Postorder traversal is Left -> Right -> Parent
 *
 * http://www.geeksforgeeks.org/iterative-postorder-traversal/
 * http://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
 * 
 * =================================
 * METHOD 1 : Iterative + Two stacks
 * =================================
 * Push the root onto first stack
 * Pop the first stack and push the node onto second
 *  This node is destined to go deep inside the stack 2 as it occurs after the children in a post order traversal
 * Now that the parent is finalized, the next node to be seen is the right child.
 *  For the right child to appear in the top of stack1, it must be pushed last (LIFO)
 *  So push the left child into stack1, then the right child
 *  This ensure the order Left -> Right -> Parent
 * After this "scanning" completes, stack 2 is expected to have the post order traversal in reverse, so popping it will give the correct order
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * ===================================================
 * METHOD 2 : Iterative + One stack (aka Brain Melter)
 * ===================================================
 *         1
 *      /    \
 *     2      3
 *   /   \  /   \
 *  4    5 6     7
 *  
 * 1. Right child of 1 exists. 
 * Push 3 to stack. Push 1 to stack. Move to left child.
 *      Stack: 3, 1
 * 
 * 2. Right child of 2 exists. 
 * Push 5 to stack. Push 2 to stack. Move to left child.
 *      Stack: 3, 1, 5, 2
 * 
 * 3. Right child of 4 doesn't exist. '
 * Push 4 to stack. Move to left child.
 *      Stack: 3, 1, 5, 2, 4
 * 
 * 4. Current node is NULL. 
 * Pop 4 from stack. Right child of 4 doesn't exist. 
 * Print 4. Set current node to NULL.
 *      Stack: 3, 1, 5, 2
 * 
 * 5. Current node is NULL. 
 * Pop 2 from stack. Since right child of 2 equals stack top element, 
 * Pop 5 from stack. Now push 2 to stack.     
 *   Move current node to right child of 2 i.e. 5
 *      Stack: 3, 1, 2
 * 
 * 6. Right child of 5 doesn't exist. Push 5 to stack. Move to left child.
 *      Stack: 3, 1, 2, 5
 * 
 * 7. Current node is NULL. Pop 5 from stack. Right child of 5 doesn't exist. 
 * Print 5. Set current node to NULL.
 *      Stack: 3, 1, 2
 * 
 * 8. Current node is NULL. Pop 2 from stack. 
 * Right child of 2 is not equal to stack top element. 
 * Print 2. Set current node to NULL.
 *      Stack: 3, 1
 * 
 * 9. Current node is NULL. Pop 1 from stack. 
 * Since right child of 1 equals stack top element, pop 3 from stack. 
 * Now push 1 to stack. Move current node to right child of 1 i.e. 3
 *      Stack: 1
 * 
 * 10. Repeat the same as above steps and Print 6, 7 and 3. 
 * Pop 1 and Print 1.
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 * ====================
 * METHOD 3 : Recursive
 * ====================
 * If node is not null
 *  Scan left
 *  Scan right
 *  Print node
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Postorder_Traversal {

	public static void main(String...strings){
		Node node7 = new Node(7);
		Node node6 = new Node(6);
		Node node5 = new Node(5);
		Node node4 = new Node(4);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);

		System.out.print("Iterative postorder traversal (2 stacks): ");
		iterativePostorderTraversalTwoStacks(node1);
		System.out.print("\nRecursive postorder traversal : ");
		recursivePostorderTraversal(node1);
		System.out.print("\nIterative postorder traversal (1 stack): ");
		iterativePostorderTraversalOneStack(node1);
	}

	private static void iterativePostorderTraversalTwoStacks(Node root) {
		if(root == null)
			return;
		Stack<Node> toProcess = new Stack<>();
		Stack<Node> postOrder = new Stack<>();
		toProcess.push(root);
		while(!toProcess.isEmpty()){
			Node parent = toProcess.pop();
			// Parent node is visited in the end, so it goes straight to the post order stack
			postOrder.push(parent);
			// Left before right on a stack gives right first when popping
			if(parent.left != null)
				toProcess.push(parent.left);
			if(parent.right != null)
				toProcess.push(parent.right);
		}
		// Stack top has the first item in post order
		while(!postOrder.isEmpty())
			System.out.print(postOrder.pop().data + " ");
	}

	private static void iterativePostorderTraversalOneStack(Node root) {
		if(root == null)
			return;
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		Node node = root;
		while(true) {
			// Parent will be in the middle
			while(node != null) {
				if(node.right != null)
					stack.push(node.right);
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if(stack.isEmpty())
				break;
			// If this is the parent node and the stack has the right child of this node, pop the right child from stack
			// Parent is pushed back in as it needs to be processed AFTER the right child has been processed
			// Move right and repeat the steps to process the right child
			if(node.right != null && stack.peek() == node.right) {
				stack.pop();
				stack.push(node);
				node = node.right;
			}
			// If it's the left child, print it
			// Set node to null to ensure the stack top is picked up for next iteration
			else {
				System.out.print(node.data + " ");
				node = null;
			}
		}
	}

	private static void recursivePostorderTraversal(Node node) {
		if(node != null){  
			recursivePostorderTraversal(node.left);
			recursivePostorderTraversal(node.right);
			System.out.print(node.data + " ");
		}
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

		Node(Node left, int data){
			this.left = left;
			this.data = data;
		}

		Node(int data, Node right){
			this.data = data;
			this.right = right;
		}

		Node(int data){
			this.data = data;
		}
	}
}
