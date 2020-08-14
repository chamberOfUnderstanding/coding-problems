package tree;

/**
 * @author 47un
 * 
 * Print the top view of the binary tree. 
 * For example :
 * 
 *      3
 *    /   \
 *   5     2
 *  / \   / \
 * 1   4 6   7
 *  \       /
 *   9     8
 * 
 * Top View : 1 -> 5 -> 3 -> 2 -> 7
 * 
 * =====================
 * METHOD 1 : Recursion
 * =====================
 * Recursively go down the left fringe and print it
 * Print the root
 * Recursively go down the right fringe and print it
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Top_View {

    public static void main(String...strings){
        Node node6 = new Node(null, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node4 = new Node(null, 4, null);
        Node node9 = new Node(null, 9, null);
        Node node8 = new Node(null, 8, node9); 
        Node node7 = new Node(null, 7, node8);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        printTopView(node1);
    }  

    static void printTopView(Node node)
    {
        printLeftView(node.left);
        System.out.print(node.data + " ");
        printRightView(node.right);
    }

    public static void printLeftView(Node node){
        if(node != null){
            printLeftView(node.left);
            System.out.print(node.data + " ");
        }        
    }

    public static void printRightView(Node node){
        if(node != null){
            System.out.print(node.data + " ");
            printRightView(node.right);        
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
