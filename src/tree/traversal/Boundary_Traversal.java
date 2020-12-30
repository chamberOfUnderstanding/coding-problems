package tree.traversal;

/**
 * @author 47un
 * 
 * Given a binary tree, print boundary nodes in anti-clockwise starting from the root. 
 * 
 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 * http://articles.leetcode.com/print-edge-nodes-boundary-of-binary/
 * 
 * ===================
 * METHOD 1 : Recursion
 * ====================
 * Print root
 * Print left edge
 *  Keep a flag that tells whether the node is to be printed or not
 *  For the left edge, all left nodes and leaves need to be printed
 *   So, if the node is a leaf or if print is true, print the node
 *  Recurse for the left child with print set as true
 *  Recurse for the right child with print set as false (we don't want right nodes to be printed, unless they are leaves)
 * Print right edge
 *  Similar to left edge
 *  Since anti clockwise printing is required, the bottom most leaf of the RST needs to be printed first
 *  So move left, don't print, move right, print
 *  if print or if leaf node, print the node
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Boundary_Traversal {

    public static void main(String...strings){
        Node node41 = new Node(null, 41, null);
        Node node28 = new Node(null, 28, null);
        Node node15 = new Node(null, 15, null);
        Node node5  = new Node(null, 5, null); 
        Node node50 = new Node(node41, 50, null);
        Node node35 = new Node(null, 35, null);
        Node node25 = new Node(null, 25, node28);
        Node node10 = new Node(node5, 10, node15);
        Node node40 = new Node(node35, 40, node50);
        Node node20 = new Node(node10, 20, node25);		
        Node node30 = new Node(node20, 30, node40);
        System.out.println("Boundary Traversal : ");
        boundaryTraversal(node30);
    }

    // Anti clockwise printing, therefore visit LST first
    private static void boundaryTraversal(Node root) {
        System.out.print(root.data + " ");
        leftSubTree(root.left, true);
        rightSubTree(root.right, true);
    }

    // Prints all left child nodes in the boundary along with any left/right child leaf nodes
    private static void leftSubTree(Node node, boolean print) {
        if(node != null){
            if(print || isLeaf(node))
                System.out.print(node.data + " ");
            leftSubTree(node.left, true);
            // Print the right child ONLY IF it is a leaf
            leftSubTree(node.right, false);
        }
    }

    // Prints all right child nodes in the boundary along with any left/right child leaf nodes
    // Since anti clockwise pattern is required, visit left then right and then print
    private static void rightSubTree(Node node, boolean print) {
        if(node != null){
        	rightSubTree(node.left, false);
        	rightSubTree(node.right, true);
            if(print || isLeaf(node))
                System.out.print(node.data + " ");
        }
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
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
