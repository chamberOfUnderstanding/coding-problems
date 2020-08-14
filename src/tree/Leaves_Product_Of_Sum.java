package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Given a Binary Tree, return following value for it.
 * 1) For every level, compute sum of all leaves if there are leaves at this level. Otherwise ignore it.
 * 2) Return product of all sums.

 * http://www.geeksforgeeks.org/find-multiplication-of-sums-of-data-of-all-leaves-at-sane-levels/
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * Level order traverse the tree
 *  If the dequeued node is a leaf, add it to the leaf sum for this level
 *  Else enqueue this node's non null children 
 *  After a level has been traversed, update the product with this level sum IFF the level sum is not 0
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Leaves_Product_Of_Sum {

    public static void main(String...strings){
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node9 = new Node(9);
        Node node8 = new Node(8, node9);
        Node node7 = new Node(7, node8);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        System.out.println(getProductOfSums(node1));
    }

    private static int getProductOfSums(Node root){
        if(root == null)
            return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int product = 1;
        while(!queue.isEmpty()){
            int nodeCount  = queue.size();
            int leafSumForThisLevel = 0;
            while(nodeCount-- > 0){
                Node node = queue.remove();
                if(isLeaf(node))
                    leafSumForThisLevel += node.data;
                else {
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }
            }
            product *= leafSumForThisLevel == 0? 1 : leafSumForThisLevel;
        }
        return product;
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
