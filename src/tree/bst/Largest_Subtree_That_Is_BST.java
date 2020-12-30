package tree.bst;

/**
 * @author 47un
 * 
 * Find the largest subtree that is a BST
 * 
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LargestBSTInBinaryTree.java
 * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
 * 
 * ===================================
 * METHOD 1 : Recursion + Data sharing
 * ===================================
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Largest_Subtree_That_Is_BST {

    public static void main(String[] args) {
        Node node2 = new Node(2);
        Node node4 = new Node(2);
        Node node3_1 = new Node(null, 3, node4);
        Node node3 = new Node(node2, 3, node3_1);
        System.out.println(largestBST(node3).size);
    }

    public static Data largestBST(Node node){
    	
    	// Default data is returned if node is null
        Data data = new Data();
        
        if(node != null){
        	// Prepare the data for LST and RST
            Data lst = largestBST(node.left);
            Data rst = largestBST(node.right);
            
            // Current node is a BST if both LST and RST are BST AND current node's data is between LST max and RST min
            data.isBST = lst.isBST &&
                         rst.isBST &&
                         lst.max < node.data &&
                         node.data < rst.min;
            
            // If current node is not a part of a BST, then size is the largest among the subtrees
            if(!data.isBST)
                data.size = Math.max(lst.size, rst.size);
            
            // Else add the current node to the size
            // Update the current node's min and max
            else{
                data.size = lst.size + 1 + rst.size;
                data.min = node.left == null? node.data : lst.min;
                data.max = node.right == null? node.data : rst.max;
            }
        }
        return data;
    }

    /**
     * This information is maintained for each node.
     */
    public static class Data{
    	
    	// Whether the sub tree rooted at this node is a BST (isBST)
        boolean isBST;

        // The minimum value on the LST (min)
        int min;
        
        // The maximum value on the RST (max)
        int max;
        
        // The size of the BST
        int size;

        Data(){

        	// true for a no tree case
            this.isBST = true;
            
            // Default is MAX_VALUE as everything is smaller than this 
            this.min = Integer.MAX_VALUE;
            
            // Default is MIN_Value as everything is larger than this
            this.max = Integer.MIN_VALUE;
            
            // 0 for no tree
            this.size = 0;
        }

        Data(boolean isBST, int min, int max, int size){
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.size = size;
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
