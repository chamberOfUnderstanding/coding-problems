package tree;

/**
 * @author 47un
 * 
 * Given preorder and inorder traversals of a binary tree, construct the tree
 * 
 * https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * 
 * ====================
 * METHOD 1 : Partition
 * ====================
 * Make a node out of the first item of preorder array
 * Find out where this item appears in inorder array
 * Everything to the left of this item in the inorder array will belong to the left subtree
 * Everything to the right of this item in the inorder array will belong to the right subtree
 * Prepare the subtrees and set them as their children
 * Repeat
 * Preorder index is static, all recursive calls use the same variable
 *  Or you can convert PreorderIndex to a class, create an instance out of it and use it throughout the recursion
 * Having processed all elements, return
 * Make a node out of the data at preorderIndex
 * If the start and end indices of inorder are same, then the node has no children, return the node
 * Search for this data in the inorder array
 * The left child of this node will be the subtree formed out of the nodes from start index to inorder index-1
 * The right child of this node will be the subtree formed out of the nodes from inorder index+1 to end index
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Tree_From_Preorder_And_Inorder {

    public static void main(String...strings){
        int[] preorder = {1, 2, 4, 5, 3, 6, 7, 9};
        int[] inorder  = {4, 2, 5, 1, 6, 3, 7, 9};
        
        PreorderIndex preorderIndex = new PreorderIndex();
        Node root = constructBinaryTree(0, inorder.length - 1, inorder, preorderIndex, preorder);
        traverse(root);
    }	

    public static Node constructBinaryTree(int i_start, int i_end, int[] i, PreorderIndex p_i, int[] p){
        if(i_start > i_end)
            return null;
        
        Node node = new Node(p[p_i.value]);
        
        p_i.value++;
        
        if(i_start == i_end)
            return node;
        int i_i = search(node.data, i);
        node.left = constructBinaryTree(i_start, i_i - 1, i, p_i, p);
        node.right = constructBinaryTree(i_i + 1, i_end,  i, p_i, p);
        return node;
    }

    private static int search(int data, int[] inorder){
        for(int i = 0; i < inorder.length; i++)
            if(inorder[i] == data)
                return i;
        return -1;
    }

    private static void traverse(Node node1) {
        System.out.print("Inorder : ");
        inorderTraverse(node1);
        System.out.print("\nPreorder : ");
        preorderTraverse(node1);
        System.out.print("\nPostorder : ");
        postorderTraverse(node1);
    }

    private static void preorderTraverse(Node node) {
        if(node != null) {
            System.out.print(node.data + " ");
            preorderTraverse(node.left);
            preorderTraverse(node.right);
        }
    }

    private static void postorderTraverse(Node node) {
        if(node != null) {
            postorderTraverse(node.left);
            postorderTraverse(node.right);
            System.out.print(node.data + " ");
        }
    }

    private static void inorderTraverse(Node node) {
        if(node != null) {
            inorderTraverse(node.left);
            System.out.print(node.data + " ");
            inorderTraverse(node.right);
        }
    }

   private static class PreorderIndex{
       int value;
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
