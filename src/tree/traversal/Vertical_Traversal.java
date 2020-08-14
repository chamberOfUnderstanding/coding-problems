package tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author 47un
 * 
 * Print a binary tree in vertical order
 *       1
 *     /   \
 *    2      3
 *   / \    / \
 *  4   5  6   7
 *  
 * The tree has 5 vertical lines
 *
 * Vertical-Line-1  4
 * Vertical-Line-2: 2
 * Vertical-Line-3: 1, 5, 6
 * Vertical-Line-4: 3
 * Vertical-Line-5: 7
 * 
 * http://www.geeksforgeeks.org/print-binary-tree-vertical-order/
 * http://javabypatel.blogspot.in/2015/10/print-binary-tree-in-vertical-order.html
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
 * Maintain a map, verticalOrder, that maps the horizontal distances to the vertical lines
 * This map must be common to all method calls, hence it is static
 *  Or you can instantiate it outside and give it to the method as an argument
 * If the node is null, return
 * Get the vertical line for this horizontal distance
 *  If it is null, instantiate it
 * Add the current node to it and update the map
 * Recurse for the LST with hd - 1
 * Recurse for the LST with hd + 1
 * 
 * TIME     : O(width * n)  // n^2 for a complete tree
 * SPACE    : O(n)
 *
 * 
 */

public class Vertical_Traversal {

    public static void main(String...strings){
        Node node9 = new Node(9);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node7 = new Node(7, node9);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        verticalOrder(0, node1);
        for(Integer horizontalDistance : verticalOrder.keySet())
            System.out.println(verticalOrder.get(horizontalDistance));
    }

    private static Map<Integer, List<Integer>> verticalOrder = new TreeMap<>();

    private static void verticalOrder(int horizontalDistance, Node node) {
        if(node == null)
            return;
        List<Integer> verticalLine = verticalOrder.get(horizontalDistance);
        if(verticalLine == null)	
            verticalLine = new ArrayList<>();
        verticalLine.add(node.data);			
        verticalOrder.put(horizontalDistance, verticalLine);
        verticalOrder(horizontalDistance - 1, node.left);
        verticalOrder(horizontalDistance + 1, node.right);
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
