package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 47un
 * 
 * Find the path from root to a given key in a binary tree
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If this node is null, return false
 * If this node has the data, return true
 * Scan the LST and RST for the node, if found, add this node to the path and return true
 * If none of the above happened, return false
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Root_To_Key_Path {

    public static void main(String[] args) {
        Node node13 = new Node(13);
        Node node7  = new Node(7);
        Node node9  = new Node(9);
        Node node8  = new Node(8);
        Node node4  = new Node(null, 4, node8);
        Node node5  = new Node(node9, 5, null);
        Node node12 = new Node(null, 12, node13);
        Node node11 = new Node(null, 11, node12);
        Node node6  = new Node(node11, 6, null);
        Node node3  = new Node(node6, 3, node7);
        Node node2  = new Node(node4, 2, node5);
        Node root   = new Node(node2, 1, node3);
        int key = 13;
        
        findPathWrapper(root, key);
    }

    private static void findPathWrapper(Node root, int key) {
        List<Integer> path = new ArrayList<>();
        findPath(root, key, path);
        Collections.reverse(path);
        System.out.println("Path to reach " + key + " : " + path);
    }

    private static boolean findPath(Node node, int key, List<Integer> path) {
        if(node == null)
            return false;
        if(node.data == key)
            return true;
        if(findPath(node.left, key, path) || findPath(node.right, key, path)) {
            path.add(node.data);
            return true;
        }
        return false;
    }

    public static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node(Node left, int data, Node right) {
            this.left = left;
            this.data = data;
            this.right = right;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }
}