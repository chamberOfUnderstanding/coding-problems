package tree;

/**
 * @author 47un
 * 
 * Print all nodes in a binary tree that do not have a sibling
 * 
 * http://www.geeksforgeeks.org/print-nodes-dont-sibling-binary-tree/
 * 
 * =========
 * METHOD 1
 * =========
 * Preorder traverse the tree
 * While visiting a node, check if the node has only one child, print it
 * Resume the traversal
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Nodes_Without_Siblings {

    public static void main(String[] args) {
        Node node5 = new Node(-99);
        Node node7 = new Node(node5, 250, null);
        Node node4 = new Node(-8);
        Node node3 = new Node(null, 3, node7);
        Node node2 = new Node(node4, 1023, null);
        Node node1 = new Node(node2, 325, node3);
        printNodesWithoutSiblings(node1);
    }

    private static void printNodesWithoutSiblings(Node node) {
        if(node != null){
            if(node.left != null && node.right == null)
                System.out.print(node.left.data + " ");
            else if(node.left == null && node.right != null)
                System.out.print(node.right.data + " ");
            printNodesWithoutSiblings(node.left);
            printNodesWithoutSiblings(node.right);
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
