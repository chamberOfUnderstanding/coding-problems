package tree.traversal;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Print the level order traversal in spiral form
 * For the below tree, function should print 1 2 3 7 6 5 4
 * 
 *     1
 *   2   3
 *  4 5 6 7 
 * 
 * http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
 * 
 *  
 * ======================
 * METHOD 1 : Two stacks
 * ======================
 * Maintain two stacks, one stores the normal order, another the reverse order
 * Iterate until either stack is empty
 *  While the reverse order stack is not empty
 *   Pop out a node and print it
 *   Since we want the normal order for the next level, push the right node first, then the left, into the normal order stack
 *    Because in the normal order, left comes before right.
 *    To pop out the left before the right from a stack, the contents must be inserted in reverse order to begin with
 *  While the normal order stack is not empty
 *   Pop out a node and print it
 *   Since we want the reverse order for the next level, push the left node first, then the right
 *    Reason is same as the one given above
 *    
 * TIME     : O(n)
 * SPACE    : O(n) //Even though two stacks are being used, max space utilized is still n
 *
 * 
 */

public class Level_Order_Traversal_Spiral {

    public static void main(String...strings){
        Node node7 = new Node(7);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.print("\nSpiral Level order traversal : ");
        spiralLevelOrderTraversal(node1);
    }

    private static void spiralLevelOrderTraversal(Node root){
        Stack<Node> reverseOrderStack = new Stack<>();
        Stack<Node> normalOrderStack = new Stack<>();
        reverseOrderStack.push(root);
        while(!reverseOrderStack.isEmpty() || !normalOrderStack.isEmpty()){
            Node node = null;
            while(!reverseOrderStack.isEmpty()){
                node = reverseOrderStack.pop();
                System.out.print(node.data + " ");
                if(node.right != null)
                    normalOrderStack.push(node.right);
                if(node.left != null)
                    normalOrderStack.push(node.left);
            }
            while(!normalOrderStack.isEmpty()){
                node = normalOrderStack.pop();				
                System.out.print(node.data + " ");
                if(node.left != null)
                    normalOrderStack.push(node.left);
                if(node.right != null)
                    normalOrderStack.push(node.right);
            }
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
