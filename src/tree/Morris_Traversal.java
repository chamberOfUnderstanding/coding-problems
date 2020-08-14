package tree;

/**
 * @author 47un
 *
 * These traversals do not employ recursion or use a stack.
 * But they do modify the tree for a while and then revert the modifications at the end
 * 
 * PROBABLY THE TRICKIEST ONE IN THIS WORKSPACE X_X T_T
 * 
 * http://www.geeksforgeeks.org/morris-traversal-for-preorder/
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
 * 
 * ============================
 * METHOD 1 : Inorder Traversal
 * ============================
 * Empty tree? return
 * This node has no left child, then scan this node and move right 
 * (Makes sense coz left is scanned before root and right, in inorder and there's no left here, so move along)
 * If the node has a left child, buckle up... T_T
 * Get the inorder predecessor of the current node
 * To do this, get the left child
 * Move right until the node has no right child or the node's right child is current
 * The node's right child becomes current iff this node was scanned once and it got connected to current (coz it's the predecessor of current)
 * If the right child is null, connect it to the current node
 * These connections will be later broken
 * Now move left
 * If right child isn't null, disconnect the connection that was made
 * Print the current node and move right
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * ============================
 * METHOD 1 : Preorder Traversal
 * ============================
 * No left child? print the data and move right
 * Yes to the left child!
 * Get the left child
 * Keep moving right until you get the predecessor (one that hasnt been connected to its successor yet)               
 * If the predecessor has a connection, break it and move right of the current node
 * Print the data and connect predecessor to current and advance left
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Morris_Traversal {

    public static void main(String...strings){
        Node node9 = new Node(9);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node7 = new Node(7, node9);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.println("\nInorder traversal");
        inorderTraversal(node1);
        System.out.println("\nPreorder traversal");
        preorderTraversal(node1);
    }

    private static void inorderTraversal(Node root){		
        if(root == null)
            return;
        Node current = root;
        while(current != null){
            if(current.left == null){
                System.out.print(current.data + " ");
                current = current.right;	
            }
            else{
                Node inorderPredecessor = current.left;
                while(inorderPredecessor.right != null && inorderPredecessor.right != current)
                    inorderPredecessor = inorderPredecessor.right;
                if(inorderPredecessor.right == null){
                    inorderPredecessor.right = current;
                    current = current.left;					
                }
                else{
                    inorderPredecessor.right = null;
                    System.out.print(current.data + " ");
                    current = current.right;
                }
            }
        }
    }

    private static void preorderTraversal(Node root){
        if(root == null)
            return;
        Node current = root;
        while(current != null){
            if(current.left == null){
                System.out.print(current.data + " ");
                current = current.right;
            }
            else{
                Node inorderPredecessor = current.left;
                while(inorderPredecessor.right != null && inorderPredecessor.right != current)
                    inorderPredecessor = inorderPredecessor.right;
                if(inorderPredecessor.right == current){
                    inorderPredecessor.right = null;
                    current = current.right;
                }
                else{
                    System.out.print(current.data + " ");
                    inorderPredecessor.right = current;
                    current = current.left;
                }					
            }
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
