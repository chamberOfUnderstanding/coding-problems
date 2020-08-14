package tree;

/**
 * @author 47un
 * 
 * Given a binary tree (not a binary search tree) and two values say n1 and n2. Find their least common ancestor.
 * 
 * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
 * 
 * ================
 * METHOD 1 : Paths
 * ================
 * Traverse the tree and record the paths to node1 and node2 in two lists.
 * Scan the lists until the first mismatch in paths occur
 * - Traverses the tree twice
 * - Scans the lists
 * - Extra space for the lists
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * ====================
 * METHOD 2 : Recursion
 * ====================
 * If node is null, return null (Best case)
 * If this node has either values, then this node has to be the LCA, (one is the ancestor of the other)
 * Find the LCA of LST & RST
 * If both subtrees returned non null LCAs then both data are present in different subtrees, who's parent
 *  is this node. So this node is the LCA
 * If one subtree's LCA is not null and another is null. Then the not null LCA is the LCA. This is because the nodes are
 *  present in the subtrees rooted at that node
 * - This method assumes that the nodes are present in the binary tree
 *
 * TIME     : O(n)
 * SPACE    : O(h)
 * 
 * Tree in the input
 *            1
 *         /     \
 *        2       3
 *      /   \    /  \
 *     4     5  6    7
 *                    \
 *                     8
 *                      \
 *                       9
 * 
 */

public class LCA_Binary_Tree {

    public static void main(String[] args) {
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node9 = new Node(9);
        Node node8 = new Node(null, 8, node9); 
        Node node7 = new Node(null, 7, node8);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        
        System.out.println("LCA is : " + lca(node1, 9, 4).data);
    }
    
    public static Node lca(Node node, int data1, int data2){
        if(node == null || node.data == data1 || node.data == data2)
            return node;
        Node lcaLST = lca(node.left, data1, data2);
        Node lcaRST = lca(node.right, data1, data2);
        if(lcaLST != null && lcaRST != null)
            return node;
        return lcaLST == null? lcaRST : lcaLST;
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
