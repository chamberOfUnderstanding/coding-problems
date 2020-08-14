package tree;

/**
 * @author 47un
 * 
 * Given a Binary Tree where each node has positive and negative values. 
 * Convert this to a tree where each node contains the sum of the left and right sub trees in the original tree. 
 * The values of leaf nodes are changed to 0.
 *  
 *       10
 *      /   \
 *   -2      6
 *   /  \   / \ 
 *   8  -4 7   5
 *   
 *      20(4-2+12+6)
 *       /   \
 *  4(8-4)   12(7+5)
 *   /   \      /  \ 
 *  0     0    0    0
 * 
 * http://www.geeksforgeeks.org/convert-a-given-tree-to-sum-tree/
 * 
 * ===================
 * METHOD 1 :Recursion
 * =====================
 * If node is null, return 0 (Termination clause)
 * Back up the node's value
 * New value of this node is sum of LST & RST values
 * Add backed up value and the new node value and return it
 *  This is because in a sum tree the value of a node = value of left child + value of right child
 *  Before the data of the node is updated, whatever value it had, that is needed by the node's parent. 
 *   So that data is backed up and added to the sum of LST and RST values
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Convert_To_Sum_Tree {

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
        System.out.println("\nSum tree is ");
        convertToSumTree(node1);
        inorderTraversal(node1);
    }

    private static int convertToSumTree(Node node){
        if(node == null)
            return 0;
        int backup = node.data;
        node.data  = convertToSumTree(node.left) + convertToSumTree(node.right);
        return node.data + backup;		
    }	

    private static void inorderTraversal(Node node) {
        if(node != null){
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
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
