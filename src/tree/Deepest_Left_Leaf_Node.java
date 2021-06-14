package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Find the deepest left leaf node of a binary tree
 * 
 * http://www.geeksforgeeks.org/deepest-left-leaf-node-in-a-binary-tree/
 * 
 * =================================
 * METHOD 1 : Level order traversal
 * =================================
 * Perform level order traversal
 * For each scanned level,
 *  If a node is a leaf node, record it's data and hoist a flag signifying that the left most leaf node value has been retrieved
 *  In each level, only the first leaf node needs to be considered, so the flag prevents further fetching of leaf nodes for this level
 *  Also, once the left most leaf is found, continue to the next level
 *  Once a level is scanned, flip the flag to false
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * NOTE : The deepest leaf can come anywhere in the tree, so scanning down the left most fringe wont help!
 * 
 */

public class Deepest_Left_Leaf_Node {

    public static void main(String...strings){
        Node node6 = new Node(null, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node4 = new Node(null, 4, null);
        Node node9 = new Node(null, 9, null);
        Node node8 = new Node(null, 8, node9); 
        Node node7 = new Node(null, 7, node8);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);		
        System.out.println("\nDeepest Left Leaf Node is : " + getDeepestLeftLeafLOT(node1));
    }

    private static int getDeepestLeftLeafLOT(Node root) {
        if(root == null)
            return -1;
        // level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        
        int dataOfDeepestNode   = 0;
        boolean foundLeftMostNode = false;
        
        while(!queue.isEmpty()){
            int nodeCount = queue.size();
            // go level by level
            while(nodeCount-- > 0){
                Node node = queue.remove();
                // record the FIRST level of this level
                // set it as the required node
                // set the flag to avoid further checks for this level
                if(isLeaf(node)){
                    if(!foundLeftMostNode){
                        dataOfDeepestNode = node.data;
                        foundLeftMostNode = true;												
                    }					
                    continue;
                }
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);				
            }
            foundLeftMostNode = false;
        }
        return dataOfDeepestNode;
    }

    private static boolean isLeaf(Node currentNode) {
        return currentNode.left == null && currentNode.right == null;
    }

    private static class Node{
        int data;
        Node left;
        Node right;

        Node(Node left,int data,Node right){            
            this.left = left;
            this.data = data;
            this.right = right;         
        }
    }
}
