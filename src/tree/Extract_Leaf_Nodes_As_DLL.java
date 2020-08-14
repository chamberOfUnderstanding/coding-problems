package tree;

/**
 * @author 47un
 * 
 * Extract the leaf nodes of a tree into a Doubly Linked List
 * 
 * http://www.geeksforgeeks.org/connect-leaves-doubly-linked-list/
 * 
 * =========================================
 * METHOD 1 : Extension of Inorder traversal
 * =========================================
 * Go Left
 * Scan the node. If it's a leaf, append it to the DLL.
 *  Sever the bond of this node with its parent
 *  To do this, check if the parent's left is this node, if so, break the bond
 *  Else if this node's the parent's right child, break that bond.
 *  Either way, he's going home childless
 * Move right
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Extract_Leaf_Nodes_As_DLL {

    public static void main(String...strings){
        Node node10 = new Node(null, 10, null);
        Node node9 = new Node(null, 9, null);								
        Node node8 = new Node(null, 8, null);
        Node node7 = new Node(null, 7, null);
        Node node5 = new Node(null, 5, null);
        Node node6 = new Node(node9, 6, node10);
        Node node4 = new Node(node7, 4, node8);
        Node node3 = new Node(null, 3, node6);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        
        extractLeafNodesAsDLL(node1, null);		
        inorderTraversal(node1);
        printList(first);
    }

    private static Node first = null;
    private static Node last  = null;	

    private static void extractLeafNodesAsDLL(Node node, Node parent){
        if(node == null)
            return;
        extractLeafNodesAsDLL(node.left, node);
        if(isLeaf(node)){
            if(first == null)
                first = node;
            else{
                last.right = node;
                node.left = last;
            }
            last = node;
            if(parent.left == node)
                parent.left = null;
            else
                parent.right = null;				
        }
        extractLeafNodesAsDLL(node.right, node);
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private static void inorderTraversal(Node node1) {
        if(node1 != null){
            inorderTraversal(node1.left);
            System.out.print(node1.data + " ");
            inorderTraversal(node1.right);
        }       
    }

    private static void printList(Node first) {
        System.out.println();
        Node node = first;
        while(node != null){
            System.out.print(node.data + " ");
            node = node.right;
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
