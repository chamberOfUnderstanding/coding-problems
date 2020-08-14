package tree;

/**
 * @author 47un
 *
 * Find the diameter of a binary tree.
 * Diameter of a Tree is defined as the number of nodes on the longest path between two leaves in the tree.
 * 
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * https://www.youtube.com/watch?v=i9nVJDr4HmA
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * Empty node ? return 0
 * Get the heights of LST and RST
 * Get their diameters
 * Sum the heights (gives a possible diameter value) and compare it with LST Diameter and RST Diameter, return the largest
 * 
 * TIME     : O(n^2)
 * SPACE    : O(n)
 * 
 * ================================
 * METHOD 1 : Recursion (Optimized)
 * ================================
 * Same as above, with the difference that height is calculated with the recursion
 * Height is turned into an object and gets instantiated at a point. 
 * As the same instance is shared with the lower layers of the recursive call, the updated values are received by the higher layers.
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 * Diameter need not always pass through the root
 * 
 */

public class Tree_Diameter {

    public static void main(String...strings){
        Node node9 = new Node(9);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node7 = new Node(7, node9);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.println("Diameter of the tree : ");
        System.out.println("Method 1 : " + diameter(node1));		
        System.out.println("Method 2 : " + diameterOptimized(node1, new Height()));		
    }

    private static int diameter(Node node){
        if(node == null)
            return 0;
        int height = height(node.left) + 1 + height(node.right);
        return maximum(height, diameter(node.left), diameter(node.right));
    }


    private static int height(Node node) {
        return node == null? 0 : 1 + Math.max(height(node.left), height(node.right));
    }

    private static int diameterOptimized(Node node, Height current){
        if(node == null){
            current.height = 0;
            return 0;
        }
        Height lst = new Height();
        Height rst = new Height();		
        int lstDiameter = diameterOptimized(node.left, lst);
        int rstDiameter = diameterOptimized(node.right, rst);
        current.height  = Math.max(rst.height, lst.height) + 1;
        return maximum(lst.height + rst.height + 1, lstDiameter, rstDiameter);
    }

    private static class Height{
        int height;

        Height(){
            this.height = 1;
        }
    }

    private static int maximum(int data1, int data2, int data3) {
        return Math.max(data1, Math.max(data2, data3));
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
