package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Print each level of the tree
 *  
 * http://quiz.geeksforgeeks.org/print-level-order-traversal-line-line/
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * For each level, record the queue size as nodesInThisLevel
 * For each node dequeued
 *  Print the node
 *  Reduce nodesInThisLevel by 1 until it hits 0
 *  Once it hits 0, print a \n
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Print_Each_Level {

    public static void main(String...strings){
        Node node7 = new Node(7);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.println("\nEach level of the tree : ");
        printEachLevel(node1);
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
