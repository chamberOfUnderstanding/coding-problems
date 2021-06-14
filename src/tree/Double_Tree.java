package tree;

/**
 * @author 47un
 * 
 * Convert a tree to a double tree.
 * A double tree can be formed by creating a duplicate of each node and adding it as the left child of the original node. 
 * 
 * http://www.geeksforgeeks.org/double-tree/
 * 
 * Hmmmmmmm...
 * 
 *     2
 *    / \
 *   1   3
 *   
 *  becomes 
 *  
 *        2
 *       / \
 *      2   3
 *     /   /
 *    1   3
 *   /
 *  1
 * 
 * =====================
 * METHOD 1 : No Backups
 * =====================
 * If the node is null, return
 * Else this node's left gets a new node which :
 *  Has the same data as this node
 *  Has it's left child as this node's left child
 *  Has no right child
 * Now recursively do this for the left and right sub trees
 * For left subtree, skip one left child as it's same as this node
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 * =================
 * METHOD 2 : Backup
 * =================
 * Return if node is empty
 * Double tree the left and right children
 * Backup the old left child
 * Make a copy of the parent
 * Parent's left child is a copy of itself
 * Copy's left is the backed up left child of the original parent
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Double_Tree {

    public static void main(String...strings){
        Node node7 = new Node(null, 7, null);
        Node node6 = new Node(null, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node4 = new Node(null, 4, null);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        
        System.out.print("\nOriginal Tree's inorder traversal : ");
        inorderTraversal(node1);
        doubleTreev1(node1);
        System.out.print("\nDouble Tree's inorder traversal : ");
        inorderTraversal(node1);
    }

    private static void doubleTreev1(Node node) {
        if(node == null)
            return;
        // prepare a node with this node's data 
        // the new node has this node's left as it's left
        node.left = new Node(node.left, node.data, null);
        // doubtTree-ify the old left child
        doubleTreev2(node.left.left);
        doubleTreev2(node.right);
    }

    private static void doubleTreev2(Node node) {
        if(node == null)
            return;
        doubleTreev2(node.left);
        doubleTreev2(node.right);
        Node oldLeft = node.left;
        Node copy = new Node(null, node.data, null);
        node.left = copy;
        copy.left = oldLeft;
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
