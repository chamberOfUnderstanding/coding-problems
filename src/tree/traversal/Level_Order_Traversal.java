package tree.traversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Print level order traversal of a given tree
 * Traversal the tree level by level. 
 * This is Breadth First Search on a tree
 * 
 * http://www.geeksforgeeks.org/level-order-tree-traversal/
 * 
 * ================
 * METHOD 1 : Queue
 * ================
 * If root is not null (Corner)
 * Enqueue root
 * While queue is not empty
 *  Dequeue
 *  Print the node
 *  If the node has a left child, enqueue it
 *  If the node has a right child, enqueue it
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Level_Order_Traversal {

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
        System.out.println("\nEach level of the tree : ");
        printEachLevel(node1);
    }

    private static void levelOrderTraversal(Node root){
        if(root != null){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                Node node = queue.remove();
                System.out.print(node.data + " ");
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            } 
        }       
    }

    private static void printEachLevel(Node root){
        if(root != null){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                int nodesInThisLevel = queue.size();
                while(nodesInThisLevel-- > 0){
                    Node node = queue.remove();
                    System.out.print(node.data + " ");
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }
                System.out.println();
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
