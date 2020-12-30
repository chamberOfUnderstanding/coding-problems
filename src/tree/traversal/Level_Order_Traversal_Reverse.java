package tree.traversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author 47un
 * 
 * Level order traversal the tree, in reverse, i.e. last level first, then second last level and so on until first level
 * e.g. 
 *      1
 *     / \
 *    2   3
 *   / \
 *  4   5
 * 
 * Level Order Traversal         : 1 2 3 4 5
 * Reverse Level Order Traversal : 4 5 2 3 1
 * 
 * ========================
 * METHOD 1 : Stack + Queue
 * ========================
 * Level order traverse the tree 
 *  In the regular LOT, after getting a node from the queue, it is printed/taken as output, here push it to a stack
 *  If this node has any living children, enqueue them
 * After all levels have been scanned, print the stack
 *
 * TIME     : O(n)
 * SPACE    : O(n) // 2*n
 *
 * 
 */

public class Level_Order_Traversal_Reverse {

    public static void main(String...strings){
        Node node7 = new Node(7);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.print("Level order traversal : ");
        levelOrderTraversal(node1);
        System.out.print("\nReverse level order traversal : ");
        reverseLevelOrderTraversal(node1);
    }

    private static void reverseLevelOrderTraversal(Node root){
        if(root != null){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Stack<Node> stack = new Stack<>();
            while(!queue.isEmpty()){
                Node node = queue.remove();
                stack.push(node);
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            while(!stack.isEmpty())
                System.out.print(stack.pop().data + " ");
        }
    }

    private static void levelOrderTraversal(Node root) {
        if(root != null){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                Node node = queue.remove();
                System.out.print(node.data + " ");
                // Visit right first to preserve the reverse order in stack.
                // It does not matter to the queue as it'll still visit all nodes in the level.
                if(node.right != null)
                    queue.add(node.right);
                if(node.left != null)
                    queue.add(node.left);
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
