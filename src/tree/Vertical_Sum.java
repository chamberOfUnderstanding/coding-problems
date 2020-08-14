package tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author 47un
 *
 * Find the vertical sum of a binary tree
 * 
 * http://www.geeksforgeeks.org/vertical-sum-in-a-given-binary-tree/
 * 
 * ===========================
 * METHOD 1 : Recursion + Map
 * ===========================
 * Horizontal distance
 *   It is the distance of a vertical line w.r.t the root. 
 *   Root has a hd of 0, things to it's left have -ve hd and to the right have +ve hd
 *   In the example 
 *     vertical line 1 has a hd of -1 (Since it is to the left of root)
 *     vertical line 2 has -2
 *     vertical line 3 has  0 (Houses the root)
 *     vertical line 2 has  1 (To the right of root)
 *     vertical line 2 has  2
 * 
 * Vertical line
 *   The nodes with the same hd fall under the same vertical line
 *   
 * Vertical order
 *   Collection of all vertical lines in the tree
 *   
 * Maintain a map, verticalSum, that maps the horizontal distances to the vertical sum
 * This map must be common to all method calls, hence it is static
 *  Or you can instantiate it outside and give it to the method as an argument
 * If the node is null, return
 * If there is no sum for this hd, then set the sum as this node's value
 * Else add the node's data to the vertical sum of this horizontal distance
 * Update the map
 * Recurse for the LST with hd - 1
 * Recurse for the LST with hd + 1
 * 
 * TIME     : O(width * n)  // n^2 for a complete tree
 * SPACE    : O(n)
 *
 * 
 */

public class Vertical_Sum {

    public static void main(String...strings){
        Node node9 = new Node(9);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node7 = new Node(7, node9);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        
        verticalSum(node1, 0);
        for(Integer distance : map.keySet())
            System.out.print(map.get(distance) + " ");
    }

    private static Map<Integer, Integer> map = new TreeMap<>();

    private static void verticalSum(Node node, int horizontalDistance) {
        if(node == null)
            return;
        Integer verticalSum = map.get(horizontalDistance);
        map.put(horizontalDistance, verticalSum == null? node.data : verticalSum + node.data);
        verticalSum(node.left, horizontalDistance - 1);
        verticalSum(node.right, horizontalDistance + 1);
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
