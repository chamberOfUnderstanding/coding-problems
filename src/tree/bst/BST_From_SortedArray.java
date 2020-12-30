package tree.bst;

/**
 * @author 47un
 * 
 * Convert a sorted array into a balanced BST
 * 
 * http://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
 * 
 * =========
 * METHOD 1
 * =========
 * Get the middle element of the array
 * Turn it into a node
 * Now this node's left is the middle of the left sub array
 * and this node's right is the middle of the right sub array
 * e.g. 1 2 3 4 5 => middle = 3
 *          3
 *      1 2  4 5
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class BST_From_SortedArray {

    public static void main(String[] args) {
        int size = 10;
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 99};
        Node root = prepareBST(0, size, array);
        inorderTraverse(root);
    }

    private static Node prepareBST(int low, int high, int[] array) {
    	// Null if limits cross (leaf nodes)
        if(low > high)
            return null;
        
        // Make a node out of the middle element
        int middle = (low + high) >> 1;
        Node node  = new Node(array[middle]);
        
        // Middle of the left sub tree will be the left child
        node.left  = prepareBST(low, middle - 1, array);
        
        // Middle of the right sub tree will be the right child
        node.right = prepareBST(middle + 1, high, array);
        
        // Return the new node
        return node;
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

        public Node(int data) {
            this.data  = data;
        }
    }
}
