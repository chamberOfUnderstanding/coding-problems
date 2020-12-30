package tree.bst;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Given preorder traversal of a binary search tree, construct the BST.
 * 
 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
 * 
 * ================
 * METHOD 1 : Stack
 * ================
 * 
 * For each item in the preorder traversal array
 *   If this item is < stack top
 *       Make this item the left child of the stack top
 *   Else if this item is > stack top
 *       Repeatedly pop the stack until the value on top of the stack is larger, record the last popped item
 *       The new node becomes the right child of the last popped item
 *   Push this item into stack
 * Verify by performing inorder/preorder again
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class BST_From_Preorder_Traversal {

    public static void main(String[] args) {
        int[] preorder = {10, 5, 1, 7, 40, 50};
        Node root = constructBST(preorder);
        inorderTraverse(root);
    }

    private static Node constructBST(int[] preorder) {
        Stack<Node> stack = new Stack<>();
        
        // In preorder traversal, first item is the root, so make a node out of the first item and set it as the root
        Node root = new Node(preorder[0]);
        
        // Push the root onto stack
        stack.push(root);
        int i = 0;
        while(++i < preorder.length) {
        	
        	// Make a node out of the element
            Node newNode = new Node(preorder[i]);
            
            // If it's < stack top, the it is a left child of stack top
            if(preorder[i] < stack.peek().data)
                stack.peek().left = newNode;
            
            // Else, pop till you find an element on stack that's > preorder[i]
            // This becomes the right child of the stack top
            else {
                Node lastPopped = null;
                while(!stack.isEmpty() && stack.peek().data < preorder[i])
                    lastPopped = stack.pop();
                lastPopped.right = newNode;
            }
            stack.push(newNode);
        }
        return root;
    }

    private static void inorderTraverse(Node node) {
        if(node != null) {
            inorderTraverse(node.left);
            System.out.print(node.data + " ");
            inorderTraverse(node.right);
        }
    }

    private static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){  
            this.data = data;
        }
    }
}
