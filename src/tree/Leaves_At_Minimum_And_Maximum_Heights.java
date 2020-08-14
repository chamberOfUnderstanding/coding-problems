package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Find the minimum and maximum height in a binary tree. Print the nodes at these heights.
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * Do a level order traversal.
 * For each level  
 *  If the node is a leaf and the level is < minimum level, record this level and the node
 *  If the node is a leaf and the level is > minimum level, record this level and the node
 *  Hoist the flag to make sure no more leaves of this level are verified for the above conditions
 *  Enqueue the non null children
 *  Bring down the flag, so that it can be used for the next level
 *  Update level (height) by 1
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * Tree in input
 *              1
 *            /   \ 
 *           2     3
 *          / \   / \ 
 *         4   5 6   7
 *        /           \
 *       11            8
 *       /              \
 *      12               9
 *      /                 \   
 *     13                 10
 *     /                        
 *    14      
 *       
 */

public class Leaves_At_Minimum_And_Maximum_Heights {

    public static void main(String[] args) {
        Node node10 = new Node(null, 10, null);
        Node node14 = new Node(null, 14, null);
        Node node13 = new Node(node14, 13, null);
        Node node12 = new Node(node13, 12, null);
        Node node11 = new Node(node12, 11, null);
        Node node9 = new Node(node10, 9, null);
        Node node8 = new Node(null, 8, node9);
        Node node7 = new Node(null, 7, node8);
        Node node6 = new Node(null, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node4 = new Node(node11, 4, null);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        
        findMaximumAndMinimumHeightedLeaves(node1);
    }

    private static void findMaximumAndMinimumHeightedLeaves(Node root) {
        if(root == null) {
            System.out.println("There's no tree dammit!");
            return;
        }            
        int height = 1;
        int minheight = Integer.MAX_VALUE;
        int minheightNode = 0;
        int maxheight = Integer.MIN_VALUE;
        int maxheightNode = 0;
        boolean leafHasBeenFound = false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Node current = queue.remove();
                if(isLeaf(current) && !leafHasBeenFound) {
                    if(height < minheight) {
                        minheight = height;
                        minheightNode = current.data;
                    }
                    else if(height > maxheight) {
                        maxheight = height;
                        maxheightNode = current.data;
                    }
                    leafHasBeenFound = true;
                }
                if(current.left != null)
                    queue.add(current.left);
                if(current.right != null)
                    queue.add(current.right);             
            }
            height++;
            leafHasBeenFound = false;
        }
        System.out.println("Minimum height : " + minheight + " & Leaf Node : " + minheightNode);
        System.out.println("Maximum height : " + maxheight + " & Leaf Node : " + maxheightNode);
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
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
