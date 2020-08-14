package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Given a node, find the level it appears within in the tree 
 * 
 * http://www.geeksforgeeks.org/get-level-of-a-node-in-a-binary-tree/
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * Level order traverse the tree
 * For each level scanned,
 *  Increase level by 1
 *  Scan each item on queue to see if any match the one we're looking for
 *   If it does, then return level
 *   Else enqueue its children
 *   
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Level_Of_A_Node {

    public static void main(String...strings){
        Node node9 = new Node(9);
        Node node8 = new Node(8, node9);
        Node node7 = new Node(7, node8);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.println("\nLevels of this node is: " + getLevel(node1, node9.data));
    }

    private static int getLevel(Node root, int key) {
        if(root != null){
            int level = 0;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                level++;
                int nodeCount = queue.size();
                while(nodeCount-- > 0){
                    Node node = queue.remove();
                    if(node.data == key)
                        return level;
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }
            }
        }
        return 0;
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