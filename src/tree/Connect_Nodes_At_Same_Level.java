package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Connect the nodes at the same level of a binary tree
 * 
 * http://www.geeksforgeeks.org/connect-nodes-at-same-level/
 * http://www.geeksforgeeks.org/connect-nodes-at-same-level-with-o1-extra-space/
 * http://code.geeksforgeeks.org/FyCx8Q
 * 
 * Input Tree
 *       A
 *      / \
 *     B   C
 *    / \   \
 *   D   E   F
 *
 *
 * Output Tree
 *       A--->NULL
 *      / \
 *     B-->C-->NULL
 *    / \   \
 *   D-->E-->F-->NULL
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * Maintain a queue and perform level order traversal
 *  Fetch level by level
 *    Have a node to track the previous node
 *    For each level scanned, connect the node to previous and update previous
 *    Default previous to null, once the level is fully scanned
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * ================================
 * METHOD 2 : Space optimized
 * ================================
 * Empty tree or Leaf node? return
 * Get neighboring child OF THIS NODE'S CONNECTOR [NOTE : neighboring child is at the same level as this node]
 *   Until we see a null
 *      If there's left child, grab it
 *      Grab the right child if it's there
 *      Neither children exist? move via the connections we have made (Genius!)
 * If this node has both children : 
 *  Connect this node's left to this node's right child
 *  Connect the right child to the neighboring child
 * Else if this node has only a left child, connect it to the neighboring child
 * Else if this node has only a right child, connect it to the neighboring child
 * Move on and connect the nodes of the RIGHT child, then the left child
 *  Connecting the right first assists in getting the neighboring child via connector
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Connect_Nodes_At_Same_Level {

    public static void main(String...strings){
        Node node11 = new Node(null, 11, null);
        Node node10 = new Node(null, 10, null);
        Node node9 = new Node(null, 9, null);
        Node node8 = new Node(null, 8, null); 
        Node node6 = new Node(null, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node7 = new Node(node10, 7, node11);
        Node node4 = new Node(node8, 4, node9);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        System.out.println("Connecting nodes at the same level...");
        connectNodesAtSameLevelSpaceOptimized(node1);
    }

    @SuppressWarnings("unused")
    private static void connectNodesAtSameLevelLOT(Node root) {
        Node previous = null;
        // queue for level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){			
        	// get size to fetch level by level
            int nodeCount = queue.size();
            // as long as items are left in this level
            while(nodeCount-- > 0){
            	// connect this to the previous
                Node current = queue.remove();
                if(previous == null)
                    previous = current;
                else{
                    System.out.println("Connecting " + previous.data + " to " + current.data);
                    previous.connector = current;
                    previous = current;
                }			
                // add kids
                if(current.left != null)
                    queue.add(current.left);
                if(current.right != null)
                    queue.add(current.right);
            }
            // reset previous for the next level
            previous = null;
        }
    }

    private static void connectNodesAtSameLevelSpaceOptimized(Node node){
        if(node == null || isLeaf(node))
            return;
        Node neighboringChild = getNeighboringChild(node.connector);
        if(!isLeaf(node)){
            node.left.connector  = node.right;
            node.right.connector = neighboringChild;
        }
        else if(node.left != null)
            node.left.connector = neighboringChild;
        else
            node.right.connector = neighboringChild;
        connectNodesAtSameLevelSpaceOptimized(node.right);
        connectNodesAtSameLevelSpaceOptimized(node.left);
    }

    private static Node getNeighboringChild(Node node) {
        while(node != null){
            if(node.left != null)
                return node.left;
            if(node.right != null)
                return node.right;
            node = node.connector;
        }
        return node;
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private static class Node{
        int data;
        Node left;
        Node right;
        Node connector;

        Node(Node left,int data,Node right){            
            this.left = left;
            this.data = data;
            this.right = right;
        }
    }
}
