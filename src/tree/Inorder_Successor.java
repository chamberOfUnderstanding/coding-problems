package tree;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Given a tree with an extra field in each node.
 * Populate this field of each node with the its inorder successor.
 * 
 * http://www.geeksforgeeks.org/populate-inorder-successor-for-all-nodes/
 * 
 * ===================================================
 * METHOD 1 : Extension of iterative Inorder traversal
 * ===================================================
 * The next node to be visited during an inorder traversal is its inorder successor.
 * As the left nodes get pushed onto stack, connect the current node (the one getting pushed) to the node on top of the stack
 * When a node has a right child, backup the current node, traverse the right child's left nodes (connecting the successors along the way,
 *  i.e. the previous step) , and once you hit rock bottom (null), connect the backed up node to the stack top.
 * Worst case occurs when the tree is skewed
 * 
 * TIME     : O(n)
 * SPACE    : O(h)
 *
 * ===================================================
 * METHOD 1 : Extension of recursive Inorder traversal
 * ===================================================
 * Fill the successors of the left sub tree
 * If there is a previous node, the current node is it's successor
 * Update the previous node to current node
 * Fill the successors of the right sub tree
 * 
 * TIME     : O(n)
 * SPACE    : O(h)
 * 
 */

public class Inorder_Successor {

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
        
        System.out.print("Normal inorder traversal : ");
        inorderTraversal(node1);
        fillInorderSuccessorsIterative(node1);
        fillInorderSuccessorsRecursive(node1);
        System.out.print("\nInorder traversal through successor nodes : ");
        inorderTraversalv2(node1);
    }

    private static void fillInorderSuccessorsIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node previous = null;
        while(current != null){
            current.inorderSuccessor = stack.isEmpty()? null : stack.peek();			
            stack.push(current);
            current = current.left;
        }
        while(!stack.isEmpty()){
            current = stack.pop();
            if(current.right != null){
                previous = current;
                current  = current.right;
                while(current != null){
                    current.inorderSuccessor = stack.isEmpty()? null : stack.peek();					
                    stack.push(current);
                    current = current.left;
                }
                previous.inorderSuccessor = stack.peek();
            }
        }
    }

    static Node previous = null;

    private static void fillInorderSuccessorsRecursive(Node current){
        if(current != null){
            fillInorderSuccessorsRecursive(current.left);
            if(previous != null)
                previous.inorderSuccessor = current;
            previous = current;
            fillInorderSuccessorsRecursive(current.right);
        }
    }

    private static void inorderTraversal(Node node) {
        if(node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    private static void inorderTraversalv2(Node node) {
        Node current = node;
        while(current.left != null)
            current = current.left;
        while(current != null) {
            System.out.print(current.data + " ");
            current = current.inorderSuccessor;
        }
    }

    private static class Node{
        int data;
        Node left;
        Node right;
        Node inorderSuccessor;

        Node(Node left, int data, Node right){            
            this.left = left;
            this.data = data;
            this.right = right;         
        }
    }
}
