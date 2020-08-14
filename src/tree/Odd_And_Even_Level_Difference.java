package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Find the difference between the sums of odd and even level nodes
 * 
 * http://www.geeksforgeeks.org/difference-between-sums-of-odd-and-even-levels/
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * Level order traverse the tree
 * Keep an odd level flag that decides the operation to be performed
 *  If the flag is up, that means the node is in a odd level, so add the node's data to the difference
 *  If the flag is down, the node is in an even level, so subtract the node's data from the difference (add -1 * the data)
 *  Flip the flag
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 *
 */

public class Odd_And_Even_Level_Difference {

    public static void main(String...strings){
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node9 = new Node(9);
        Node node8 = new Node(8, node9); 
        Node node7 = new Node(7, node8);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.println("Difference is : " + findDifference(node1));
    }

    private static int findDifference(Node root) {
        if(root == null)
            return Integer.MAX_VALUE;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean oddLevel = false;
        int difference = 0;
        while(!queue.isEmpty()){
            int nodeCount = queue.size();
            while(nodeCount-- > 0){
                Node current = queue.remove();
                difference = difference + (oddLevel? current.data : - current.data);
                if(current.left != null)
                    queue.add(current.left);
                if(current.right != null)
                    queue.add(current.right);
            }
            oddLevel = !oddLevel;
        }
        return difference;
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
