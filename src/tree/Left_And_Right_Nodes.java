package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Find the next right node of a given key
 * 
 * For example, consider the following Binary Tree. Output for 2 is 6, output for 4 is 5. Output for 10, 6 and 5 is NULL. 
 *                   10
 *                /      \
 *               2         6
 *             /   \         \ 
 *           8      4          5
 * 
 * http://www.geeksforgeeks.org/find-next-right-node-of-a-given-key/
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * Level order traverse the tree
 * To find left node :
 *  For each node that does not match the key, record the data in a variable. Replace this when another non matching node is seen.
 *  If a node matches the key, return the recorded data.
 *  After a level is scanned (but the data was not found), reset the value of previous
 * To find right node :
 *  If a match is found, hoist a flag
 *  The first node that is seen after the hoisted flag is the right node
 *  After a level is scanned (but the data was not found), lower the flag
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Left_And_Right_Nodes {

    public static void main(String...strings){
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node9 = new Node(9);
        Node node8 = new Node(8, node9);
        Node node7 = new Node(7, node8);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        int leftNode  = leftNode(node1, 2);
        int rightNode = rightNode(node1, 3);
        System.out.println("Left node : " + (leftNode == -1? null : leftNode));
        System.out.println("Right node : " + (rightNode == -1? null : rightNode));        
    }

    private static int leftNode(Node root, int key) {
        if(root == null)
            return -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int previousNode = -1;
        while(!queue.isEmpty()){
            int nodeCount = queue.size();
            while(nodeCount-- > 0){
                Node currentNode = queue.remove();
                if(currentNode.data == key)
                    return previousNode;
                previousNode = currentNode.data;
                if(currentNode.left != null)
                    queue.add(currentNode.left);
                if(currentNode.right != null)
                    queue.add(currentNode.right);
            }
            previousNode = -1;
        }
        return -1;
    }

    private static int rightNode(Node root, int key) {
        if(root == null)
            return -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean grabNextNode = false;
        while(!queue.isEmpty()){
            int nodeCount = queue.size();
            while(nodeCount-- > 0){
                Node currentNode = queue.remove();
                if(grabNextNode)
                    return currentNode.data;
                if(currentNode.data == key)
                    grabNextNode = true;
                if(currentNode.left != null)
                    queue.add(currentNode.left);
                if(currentNode.right != null)
                    queue.add(currentNode.right);
            }
            grabNextNode = false;
        }
        return -1;
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
