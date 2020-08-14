package tree;

/**
 * @author 47un
 * 
 * Given the preorder traversal of a binary tree and an array that says what type of a node each of them are (internal or leaf).
 * Construct the binary tree.
 *  
 * http://www.geeksforgeeks.org/construct-a-special-tree-from-given-preorder-traversal/
 * 
 * =====================
 * METHOD 1 : Recursion
 * ====================
 * Check if the preorder array has been fully scanned, if yes, exit
 * Create a new node for the current data
 * Advance the index
 *  There's just one index value maintained for all the recursive method calls. Hence the index variable is static.
 *  Or you can Wrapper it and pass it via method argument
 * If this node is an internal node, build the lst and rst and assign them to left and right respectively
 * Return the node
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Special_Binary_Tree_From_Preorder_Traversal {

    public static void main(String...strings){
        int[] preorderTraversal = {10, 30, 20, 5, 15};
        char[] nodeType = {'N','N','L','L','L'};
        Node root = buildSpecialTreeFromPreorderTraversal(preorderTraversal, nodeType);
        preorderTraversal(root);
    }

    static int index = 0;

    private static Node buildSpecialTreeFromPreorderTraversal(int[] preorderTraversal, char[] nodeType) {
        if(index == preorderTraversal.length)
            return null;
        Node node = new Node(preorderTraversal[index]);
        if(nodeType[index++] == 'N'){			
            node.left = buildSpecialTreeFromPreorderTraversal(preorderTraversal, nodeType);
            node.right = buildSpecialTreeFromPreorderTraversal(preorderTraversal, nodeType);
        }
        return node;
    }

    private static void preorderTraversal(Node node) {
        if(node != null){
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
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