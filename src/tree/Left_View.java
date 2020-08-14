package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Print left view of a binary tree
 * 
 * Left view of following tree is 12, 10, 25.
 *           12
 *        /     \
 *      10       30
 *             /    \
 *           25      40 
 * 
 * http://www.geeksforgeeks.org/print-left-view-binary-tree/ 
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * 1. Level order traverse the tree
 *    Maintain a flag that is hoisted after scanning the left most node of the level
 *    So if the flag is up, don't print the node
 *    Resume LOT as usual, but pull down the flag after scanning a level
 * 2. Level order traverse the tree. Record nodes as they are visited
 *    Add a special marker at the end of each level
 *    Scan the level order recording and print all nodes that appear right after the special marker to get the left view of the tree
 *    NOTE : All nodes that appear to the left of the special marker gives the right view of the tree
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * ====================
 * METHOD 2 : Recursion
 * ====================
 * Empty node ? return null
 * If maxLevel is less than level, then we are seeing this level for the first time, so print the first node and update maxLevel to level
 * Recurse for left and right subtrees for updated level value
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Left_View {

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

        leftViewLevelOrderTraversal(node1);
        System.out.println();
        leftViewRecursive(node1, 1);
    }

    private static void leftViewLevelOrderTraversal(Node root) {
        if(root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean gotLeftMostNode = false;
        while(!queue.isEmpty()){
            int nodeCount = queue.size();
            while(nodeCount-- > 0){
                Node currentNode = queue.remove();
                if(gotLeftMostNode == false){
                    System.out.print(currentNode.data + " ");
                    gotLeftMostNode = true;
                }
                if(currentNode.left != null)
                    queue.add(currentNode.left);
                if(currentNode.right != null)
                    queue.add(currentNode.right);				
            }
            gotLeftMostNode = false;
        }
    }

    static int maxLevel = 0;

    private static void leftViewRecursive(Node node, int level){
        if(node == null)
            return;
        if(maxLevel < level){
            System.out.print(node.data + " ");
            maxLevel = level;
        }
        leftViewRecursive(node.left, level+1);
        leftViewRecursive(node.right, level+1);
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
