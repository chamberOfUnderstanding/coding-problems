package tree.bst;

/**
 * @author 47un
 * 
 * Find the next higher node (inorder successor) of a given node in a BST
 * 
 * http://stackoverflow.com/questions/20990808/bst-finding-next-highest-node
 * 
 * =========
 * METHOD 1
 * =========
 * Move right
 * Keep moving left until you hit null
 * Print the node
 * 
 * TIME     : O(logn)
 * SPACE    : O(logn)
 *
 * 
 */

public class BST_Next_Higher_Node {

    @SuppressWarnings("unused")
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
        System.out.println("Next node of " + node3.data + " is " + findNextHigherNode(node3));
    }

    public static int findNextHigherNode(Node node) {
        if(node != null) {
            if(node.right != null){
                node = node.right;
                while(node.left != null)
                    node = node.left;
                return node == null? -1 : node.data;
            }
        }
        return -1;
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