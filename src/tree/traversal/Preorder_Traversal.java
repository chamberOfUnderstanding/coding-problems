package tree.traversal;

import java.util.Stack;

/**
 * @author 47un
 *
 * Preorder traverse a given tree.
 * 
 * Parent -> Left -> Right
 * 
 * ====================
 * METHOD 1 : Iterative
 * ====================
 * Empty tree check
 * Push root
 * Pop a node, print it
 * Preorder traversal requires us to go left after processing the root, so we push right child onto stack then the left child
 *  This is because of stack operation. Pushing R, then L gives L then R on pop, which is exactly what we want for preorder.
 *
 * TIME    : O(n)
 * SPACE   : O(h)
 * 
 */

public class Preorder_Traversal {

    public static void main(String...strings){
        Node node7 = new Node(null, 7, null);
        Node node6 = new Node(null, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node4 = new Node(null, 4, null);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.print("Iterative preorder traversal : ");
        iterativePreorderTraversal(node1);
        System.out.print("\nRecursive preorder traversal : ");
        recursivePreorderTraversal(node1);
    }

    private static void iterativePreorderTraversal(Node root) {
        if(root == null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node node = null;
        while(!stack.isEmpty()){
            node = stack.pop();
            // Visit parent
            System.out.print(node.data + " ");
            // Right should come AFTER left, so push right in first
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
    }

    private static void recursivePreorderTraversal(Node node) {
        if(node != null){
            System.out.print(node.data + " ");
            recursivePreorderTraversal(node.left);
            recursivePreorderTraversal(node.right);
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
