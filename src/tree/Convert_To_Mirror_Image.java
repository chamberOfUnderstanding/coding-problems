package tree;

/**
 * @author 47un
 * 
 * Convert a binary tree into its mirror image
 * e.g. :
 * 
 *          1                     1  
 *       /    \                 /    \
 *      2      3       =>      3      2  
 *     / \    / \             / \    / \
 *    4   5  6   7           7   6  5   4
 *   /                                   \
 *  8                                     8
 *   \                                   /
 *    9                                 9
 *    
 * http://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If the node is null, the subtree/tree is empty, return (Termination clause)
 * Get the mirror image of the left subtree and right subtree
 * Set current node's left sub tree as mirror image of the right subtree
 * Set current node's right sub tree as mirror image of the left subtree
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Convert_To_Mirror_Image {

    public static void main(String...strings){
        Node node9 = new Node(null, 9, null);
        Node node8 = new Node(node9, 8, null);
        Node node7 = new Node(null, 7, node8);
        Node node6 = new Node(null, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node4 = new Node(null, 4, null);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        System.out.print("\nInorder traversal of original tree : ");
        inorderTraversal(node1);
        node1 = convertToMirrorImage(node1);
        System.out.print("\nInorder traversal of mirrored tree : ");
        inorderTraversal(node1);
    }

    private static Node convertToMirrorImage(Node node) {
        if(node == null)
            return null;
        Node mirroredLST = convertToMirrorImage(node.left);
        Node mirroredRST = convertToMirrorImage(node.right);
        node.left  = mirroredRST;
        node.right = mirroredLST;
        return node;
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

        Node(Node left, int data, Node right){			
            this.left = left;
            this.data = data;
            this.right = right;			
        }
    }
}

