package tree;

/**
 * @author 47un
 * 
 * Find the nodes with maximum and minimum value in a binary tree
 * 
 * http://quiz.geeksforgeeks.org/find-maximum-or-minimum-in-binary-tree/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * Traverse the tree
 * For each node
 *  return the largest value among: node's data, LST's maximum value, RST's maximum value
 * Similarly find minimum value
 * 
 * TIME     : O(n) // Traverses the tree, twice
 * SPACE    : O(n)
 * 
 * Or they can be clubbed together into an array and returned, where data[0] can be minimum value and data[1] can be maximum value
 * This method will do it in one traversal of the tree.
 * 
 */

public class Maximum_Minimum_Nodes {

    public static void main(String...strings){
        Node node6 = new Node(-6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node9 = new Node(4123);
        Node node8 = new Node(8, node9);
        Node node7 = new Node(7, node8);
        Node node3 = new Node(node6, -32948, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.println("Maximum : " + findMaximumNode(node1));
        System.out.println("Minimum : " + findMinimumNode(node1));
        int[] result = findMaximumAndMinimumNodes(node1);
        System.out.println(result[0] + " " + result[1]);
    }

    private static int findMaximumNode(Node node) {
        return node == null? 
                Integer.MIN_VALUE : 
                    maximum(node.data, findMaximumNode(node.left), findMaximumNode(node.right));
    }

    private static int findMinimumNode(Node node) {
        return node == null?
                Integer.MAX_VALUE:
                    minimum(node.data, findMinimumNode(node.left), findMinimumNode(node.right));
    }

    private static int[] findMaximumAndMinimumNodes(Node node) {
        if(node == null) 
            return new int[] { Integer.MIN_VALUE,
                               Integer.MAX_VALUE };
        int[] lstData = findMaximumAndMinimumNodes(node.left);
        int[] rstData = findMaximumAndMinimumNodes(node.right);
        return new int[] { maximum(node.data, lstData[0], rstData[0]), 
                           minimum(node.data, lstData[1], rstData[1]) };
    }

    private static int maximum(int number1, int number2, int number3) {
        return Math.max(number1, Math.max(number2, number3));
    }

    private static int minimum(int number1, int number2, int number3) {
        return Math.min(number1, Math.min(number2, number3));
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
