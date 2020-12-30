package tree.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 47un
 * 
 * Convert the given binary tree to a BST. Structure of the tree must be retained.
 * 
 * Input:
 *        10
 *       /  \
 *      2    7
 *     / \
 *    8   4
 *    
 * Output:
 *        8
 *       /  \
 *      4    10
 *     / \
 *    2   7
 * 
 * 
 * http://www.geeksforgeeks.org/binary-tree-to-binary-search-tree-conversion/
 * 
 * =========
 * METHOD 1
 * =========
 * Inorder traverse the binary tree, record the data in a list
 * Sort the list (As the inorder traversal of a BST is sorted)
 * Inorder traverse the tree again and set the visited nodes with the value in the front of the list
 * 
 * Here the flag determines whether the method extracts the data from the node onto the list or does the opposite
 * 
 * TIME     : O(nlogn)  // Sorting is there!
 * SPACE    : O(n)
 *
 * 
 */

public class Convert_Binary_Tree_To_BST {

    public static void main(String[] args) {
        Node node7 = new Node(250);
        Node node6 = new Node(16);
        Node node5 = new Node(-99);
        Node node4 = new Node(-8);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 1023, node5);
        Node node1 = new Node(node2, 325, node3);
        System.out.println("Inorder traversal before conversion : ");
        inorderTraverse(node1);
        node1 = convertBinaryTreeToBST(node1);
        System.out.println("\n\nInorder traversal after conversion : ");
        inorderTraverse(node1);
    }

    private static Node convertBinaryTreeToBST(Node root)
    {
        List<Integer> data = new ArrayList<>();
        convertBinaryTreeToBST(root, data, true);
        Collections.sort(data);
        convertBinaryTreeToBST(root, data, false);
        return root;
    }

    private static void convertBinaryTreeToBST(Node node, List<Integer> data, boolean extract){
    	// Basic inorder traversal
        if(node != null){
            convertBinaryTreeToBST(node.left, data, extract);
            
            // If data is to be extracted, record it in the list
            if(extract)
                data.add(node.data);
            
            // Else update the node's data with that on the list top
            else
                node.data = data.remove(0);
            convertBinaryTreeToBST(node.right, data, extract);
        }
    }

    private static void inorderTraverse(Node node) {
        if(node != null) {
            inorderTraverse(node.left);
            System.out.print(node.data + " ");
            inorderTraverse(node.right);
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
    }
}
