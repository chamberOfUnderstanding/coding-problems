package tree;

/**
 * @author 47un
 * 
 * Given Inorder Traversal of a Special Binary Tree in which key of every node is greater than keys in left and right children
 * Construct the Binary Tree.
 * 
 * Input: inorder[] = {5, 10, 40, 30, 28}
 * Output: root of following tree
 * 
 *          40
 *        /   \
 *       10     30
 *      /         \
 *     5          28 
 * 
 * Input: inorder[] = {1, 5, 10, 40, 30, 15, 28, 20}
 * Output: root of following tree
 * 
 *           40
 *         /   \
 *        10     30
 *       /         \
 *      5          28
 *     /          /  \
 *    1         15    20
 *    
 * http://www.geeksforgeeks.org/construct-binary-tree-from-inorder-traversal/
 * 
 * =========
 * METHOD 1
 * =========
 * Get the index of the maximum value (within the interval)
 *  For the first call, the interval is 0 to length - 1
 * If the start of the interval is < end of the interval
 *  Make a node out the data at this index
 *  This node's :
 *    left child is the maximum of the values on the left partition of the interval (start to maxIndex - 1)
 *    right child is the maximum of values on the right partition of the interval (maxIndex + 1 to end)
 * 
 * TIME     : O(n^2) // Finding the maximum index is making things costly
 * SPACE    : O(n)
 *
 * =====================
 * METHOD 2 : Optimized
 * =====================
 * Make a node out of the current index
 * As long as the value at this index is < the next value
 *  Make a node out of the next value and add this node as its left child
 *  Advance the index and set the new node as the current node
 * If the value at this index > the next value
 *  Current node's right child is the node returned by the recursive call to the same method for the next index
 * 
 * TIME    : O(n)
 * SPACE   : O(n)
 * 
 * 
 */

public class Special_Binary_Tree_From_Inorder_Traversal {

    public static void main(String...strings){
        int[] inorderTraversal = {1, 5, 10, 40, 30, 15, 28, 20};

        Node root = buildSpecialTreeFromInorderTraversal(inorderTraversal, 0, inorderTraversal.length - 1);
        inorderTraversal(root);

        System.out.println();
        Node rootv2 = buildSpecialTreeFromInorderTraversalv2(0, inorderTraversal);
        inorderTraversal(rootv2);

    }

    private static Node buildSpecialTreeFromInorderTraversal(int[] inorderTraversal, int low, int high) {
        Node node = null;
        if(low <= high){
            int indexOfMax = getIndexOfMax(inorderTraversal, low, high);
            node = new Node(null, inorderTraversal[indexOfMax] , null);
            node.left  = buildSpecialTreeFromInorderTraversal(inorderTraversal, low, indexOfMax - 1);
            node.right = buildSpecialTreeFromInorderTraversal(inorderTraversal, indexOfMax + 1, high);			
        }
        return node;		
    }

    private static int getIndexOfMax(int[] inorderTraversal, int low, int end) {
        int maximum = low;
        for(int mid = low + 1; mid <= end; mid++)
            if(inorderTraversal[mid] > inorderTraversal[maximum])
                maximum = mid;
        return maximum;
    }

    private static Node buildSpecialTreeFromInorderTraversalv2(int index, int[] inorderTraversal) {
        if(index >= inorderTraversal.length)
            return null;
        Node node = new Node(inorderTraversal[index]);
        while(index < inorderTraversal.length - 1 && inorderTraversal[index] < inorderTraversal[index + 1]) {
            Node parent = new Node(inorderTraversal[index + 1]);
            parent.left = node;
            index++;
            node = parent;

        }
        if(index < inorderTraversal.length - 1 && inorderTraversal[index] > inorderTraversal[index + 1])
            node.right = buildSpecialTreeFromInorderTraversalv2(index + 1, inorderTraversal);
        return node;
    }

    private static void inorderTraversal(Node node){
        if(node != null){
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
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

        @Override
        public String toString() {
            return "" + this.data;
        }
    }
}