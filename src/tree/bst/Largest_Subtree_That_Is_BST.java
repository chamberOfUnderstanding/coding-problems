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
 * For each node we need to keep track of the following :
 *	Whether the sub tree rooted at this node is a bst (isBST)
 *	The minimum value on the LST (min)
 *	The maximum value on the RST (max)
 *	The size of the BST (size)
 * This information is shared between the nodes (from leaves to root).
 * Default data would be (if the node is null) :
 *	true for isBST 
 *	  : As a leaf node is a BST
 *	min = MAX_VALUE 
 *	  : As this node is expected to have a larger value to everything on the left child of this node's parent (including the parent)
 *	    Giving it the highest value will ensure it's always greater for a leaf
 *	max = MIN_VALUE
 *	  : Same as above, but this time this value needs to be smaller than everything to the right of the parent (including parent)
 *	size = 0
 *	  : It's a null node, I mean there's no tree!
 * Now if the node is null, return the default data
 * Else find the largest BST in both lst and rst
 *  If both lst and rst are BST and this node's value lies between lst max and rst min, then this node is also included in the bst
 *	New size would be sum of lst and rst sizes + 1 (this node)
 *	Minimum of this node would be this node's data (if it has no left) else lst's min
 *	Maximum of this node would be this node's data (if it has no right) else rst's max
 *  If this node does not form a bst with lst and rst, then size = max of lst and rst sizes
 *
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
        Data data = new Data();
        if(node != null){
            Data lst = largestBST(node.left);
            Data rst = largestBST(node.right);
            data.isBST = lst.isBST &&
                         rst.isBST &&
                         lst.max < node.data &&
                         node.data < rst.min;
            if(!data.isBST)
                data.size = Math.max(lst.size, rst.size);
            else{
                data.size = lst.size + 1 + rst.size;
                data.min = node.left == null? node.data : lst.min;
                data.max = node.right == null? node.data : rst.max;
            }
        }
        return data;
    }

    public static class Data{
        boolean isBST;
        int min;
        int max;
        int size;

        Data(){
            this.isBST = true;
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
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
