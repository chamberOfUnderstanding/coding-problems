package tree;

/**
 * @author 47un
 * 
 * Find the size of the given tree (Number of nodes in it)
 *  
 * http://www.geeksforgeeks.org/write-a-c-program-to-calculate-size-of-a-tree/
 * 
 * =====================
 * METHOD 1 : Recursion
 * =====================
 * If the node is null, size is 0
 * Else this node contributes 1 to the overall size.
 * So add 1 to the sizes of the LST + RST
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Tree_Size {

    public static void main(String...strings){
        Node node9 = new Node(9);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node7 = new Node(7, node9);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        
        System.out.print("Size of the tree : "+ findSize(node1));		
    }

    private static int findSize(Node node) {
        return node == null? 0 : 1 + findSize(node.left) + findSize(node.right);
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
