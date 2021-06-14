package tree;

/**
 * @author 47un
 * 
 * Get the root to leaf path with the target sum
 * 
 * http://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If the node is null, return false (Termination clause)
 * If this node is a leaf node & if the sum + node's data = target sum, return true
 * Else look for the updated sum in the LST or the RST
 *           
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */
public class Root_To_Leaf_Path_Target_Sum {
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
        int pathSum = 28;
        System.out.println(""
                + "Path sum of " 
                + pathSum 
                + " is present? "
                + (isPathSumPresent(node1, 0, pathSum)? "Yes" : "No"));
    }
    private static boolean isPathSumPresent(Node node, int sum, int targetSum) {
        if(node == null)
            return false;
        // include this node's data in the sum
        int updatedSum = sum + node.data;
        // if it's a leaf and it matches the target, bingo
        if(isLeaf(node) && updatedSum == targetSum)
            return true;
        // else look for it in the LST and RST WITH the updated Sum
        return isPathSumPresent(node.left, updatedSum, targetSum) || 
                isPathSumPresent(node.right, updatedSum, targetSum);
    }
    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
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