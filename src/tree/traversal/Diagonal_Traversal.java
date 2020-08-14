package tree.traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Traverse the diagonal nodes of a binary tree
 * 
 * http://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
 * 
 *         8
 *      /     \
 *     3       10
 *   /   \       \
 *  1    6       14
 *     /   \    /
 *    4     7  13
 *    
 * Diagonal traversal of the above tree is :
 * 8 10 14
 * 3 6 7 13
 * 1 4
 *     
 * =============================
 * METHOD 1 : Preorder Traversal
 * =============================
 * Same as vertical traversal.
 * Maintain a map that relates a diagonal to the list of elements in the diagonal
 * Root is in diagonal 0
 * The right children lie on the same diagonal as the parent (So retain the diagonal value when recursing for the RST)
 * The left children lie on the diagonal below the parent's diagonal (So update the diagonal value when recursing for the LST)
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * ================
 * METHOD 2 : Queue
 * ================
 * http://ideone.com/VYVZyU
 * 
 * Kinda like a level order traversal, but not necessarily
 * Enqueue the root
 * Repeat as long as the queue is not empty
 * Scan the current contents of the queue by fetching the queue's size
 *  Repeat until a node without a right child is seen
 *    Print the node
 *    If this node has a left child, it needs to appear in another diagonal, so enqueue it
 *    Move right 
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Diagonal_Traversal {

    public static void main(String[] args) {
        Node node13 = new Node(13);
        Node node14 = new Node(node13, 14);
        Node node10 = new Node(10, node14);
        Node node7 = new Node(7);
        Node node4 = new Node(4);
        Node node1 = new Node(1);
        Node node6 = new Node(node4, 6, node7);
        Node node3 = new Node(node1, 3, node6);
        Node node8 = new Node(node3, 8, node10);
        diagonalTraversal(node8);
        System.out.println();
        diagonalTraversalUsingQueue(node8);
    }

    private static void diagonalTraversal(Node root) {
        Map<Integer, List<Integer>> diagonalTraversal = new HashMap<>();
        diagonalTraversal(0, root, diagonalTraversal);
        System.out.println(diagonalTraversal);
    }

    private static void diagonalTraversal(int diagonal, Node node, Map<Integer, List<Integer>> diagonalTraversal) {
        if(node == null)
            return;
        List<Integer> diagonalLevel = diagonalTraversal.get(diagonal);
        if(diagonalLevel == null)
            diagonalLevel = new ArrayList<>();
        diagonalLevel.add(node.data);
        diagonalTraversal.put(diagonal, diagonalLevel);
        diagonalTraversal(diagonal - 1, node.left, diagonalTraversal);
        diagonalTraversal(diagonal, node.right, diagonalTraversal);
    }

    private static void diagonalTraversalUsingQueue(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int nodeCount = queue.size();
            while(nodeCount-- > 0) {
                Node node = queue.remove();
                while(node != null) {
                    System.out.print(node.data + " ");
                    if(node.left != null)
                        queue.add(node.left);
                    node = node.right;
                }
            }
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

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node right) {
            this.data = data;
            this.right = right;
        }

        public Node(Node left, int data) {
            this.data = data;
            this.left = left;
        }
    }
}
