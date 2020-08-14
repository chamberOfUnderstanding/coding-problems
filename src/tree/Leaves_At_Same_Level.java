package tree;

/**
 * @author 47un
 * 
 * Check if all the leaves of a binary tree are at the same level
 * 
 * http://www.geeksforgeeks.org/check-leaves-level/
 * 
 * ====================================
 * METHOD 1 : Leftmost leaf + Traversal
 * ====================================
 * Find the height of the left most leaf and record it
 *  To do this, start with the root and scan until a leaf node is detected
 *    If the node has a left, move left and increase the height
 *    If the node has a right, move right and increase the height
 * Traverse the tree while carrying two extra parameters, heightOfTheLeftmostLeaf and current node's height
 *  If the node is null, return true
 *  If the node is a leaf, compare the heights and return the result
 *  Else traverse the LST and RST after increasing height by 1 
 * 
 * TIME     : O(n) // May be a bit more than the first method due to the initial leftmost leaf computation
 * SPACE    : O(n)
 * 
 * Input tree
 *         1
 *       /   \
 *      2     3
 *    /  \   /  \
 *   4    5 6    7 
 * 
 */

public class Leaves_At_Same_Level {

    public static void main(String...strings){
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4); 
        Node node7 = new Node(7);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.println(areLeavesAtSameLevel(node1));
    }

    private static boolean areLeavesAtSameLevel(Node root) {
        Node node = root;
        int heightOfLeftmostLeaf = 0;
        while(!isLeaf(node)) {
            while(node.left != null) {
                node = node.left;
                heightOfLeftmostLeaf++;
            }
            while(node.right != null) {
                node = node.right;
                heightOfLeftmostLeaf++;
            }
        }
        return traverse(root, heightOfLeftmostLeaf, 0);
    }

    private static boolean traverse(Node node, int heightOfLeftmostLeaf, int height) {
        if(node == null)
            return true;
        if(isLeaf(node))
            return heightOfLeftmostLeaf == height;
        return traverse(node.left,  heightOfLeftmostLeaf, height + 1) &&
               traverse(node.right, heightOfLeftmostLeaf, height + 1);            
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
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
        
        Node(int data){
            this.data = data;
        }
    }
}
