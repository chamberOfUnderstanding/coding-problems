package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Find the level with maximum width (most nodes)
 * 
 * http://www.geeksforgeeks.org/maximum-width-of-a-binary-tree/
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * Do level order traversal
 * For each level, count the number of nodes in it (Queue size)
 * Update maximum width with queue size (if a larger queue was seen for this level)
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Level_With_Maximum_Width {

    public static void main(String...strings){
        Node node7 = new Node(7);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node8 = new Node(node6, 8, node7);
        Node node3 = new Node(3, node8);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        
        System.out.print("Maximum width of the tree : " + findMaximumWidth(node1));		
    }

    private static int findMaximumWidth(Node root) {
        if(root == null)
            return 0;
        int maximumWidth = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);		
        while(!queue.isEmpty()){
            int nodesInThisLevel = queue.size();
            maximumWidth = Math.max(maximumWidth, nodesInThisLevel);
            while(nodesInThisLevel-- > 0){
                Node node = queue.remove();			
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
        }
        return maximumWidth;
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
