package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Fetch the deepest odd level node 
 *
 * http://www.geeksforgeeks.org/find-depth-of-the-deepest-odd-level-node/
 * 
 * ================================
 * METHOD 1 : Level Order Traversal
 * ================================
 * Perform level order traversal
 * Keep track of the depth using a variable
 * Whenever the depth is odd, record that node's data
 * Return the recorded node data
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Deepest_Odd_Level_Node {

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
        System.out.println("\nDeepest Odd Level Node is : " + getDeepestOddLevelNodeLOT(node1));
    }

    private static int getDeepestOddLevelNodeLOT(Node root) {
        if(root == null)
            return -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        int deepestOddLevelNode = 0;
        while(!queue.isEmpty()){
            int nodeCount = queue.size();
            // go level by level
            while(nodeCount-- > 0){
                Node node = queue.remove();
                // odd level? record the node
                if(depth % 2 != 0)
                    deepestOddLevelNode = node.data;
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);				
            }
            // update depth
            depth++;
        }
        return deepestOddLevelNode;
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
