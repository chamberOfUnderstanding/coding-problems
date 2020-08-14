package tree;

/**
 * @author 47un
 *
 * Check if a given tree is a sub tree of another tree 
 * 
 * http://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/
 *
 * ==========================================
 * METHOD 1 : Preorder and Inorder Traversals
 * ==========================================
 * Get the preorder traversals of both trees 
 * If the second tree's preorder traversals is not contained within the first then it is NOT a sub tree
 * If it is contained, do one more verification using inorder traversals.
 * If that is also true, then the second is a sub tree of first
 * 		
 * This uses a custom inorder traversal, where the result is returned as a string builder
 * The traversed nodes are appended to the string builder (Separated by ,)
 * For null nodes, $ is appended. 
 * This is because this logic for finding sub trees might break when the tree is a not so obvious subtree. e.g.
 *       x                 x 
 *     /   \             /   \
 *    a     b           a      b
 *   /                 /        \        
 *  c                 c          d
 * The preorder - inorder logic applied for the above trees will say that they first is a sub tree of second, which it isnt.
 * This is because of the null child of b
 * 
 * Preorders are : xacb and xacbd
 * Inorders are  : caxb and caxbd
 * 
 * If a special character, say $ denotes the null values, then
 * Preorders are : xac$b$ and xac$bd$
 * Inorders are  : $caxb$ and $caxbd$
 * Now they are not subtrees!
 * 
 * NOTE : In this example, the ',' that separate the nodes in the traversals have not been shown for simplicity
 *             
 * TIME    : O(n)
 * SPACE   : O(n)	// Well, stack space for the traversals, then the 4 StringBuilders that store the traversals
 * 
 */

public class Is_Sub_Tree {

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
		
		System.out.println("\nIs it a subtree? " + (isSubTree(node1, node3)? "Yes" : "No"));
	}

	private static boolean isSubTree(Node root1, Node root2) {
		StringBuilder preorderTraversalOfTree1 = getPreorderTraversal(root1, new StringBuilder(""));
		StringBuilder preorderTraversalOfTree2 = getPreorderTraversal(root2, new StringBuilder(""));
		if(!preorderTraversalOfTree1.toString().contains(preorderTraversalOfTree2))
			return false;
		StringBuilder inorderTraversalOfTree1  = getInorderTraversal(root1, new StringBuilder(""));
		StringBuilder inorderTraversalOfTree2  = getInorderTraversal(root2, new StringBuilder(""));
		return inorderTraversalOfTree1.toString().contains(inorderTraversalOfTree2);
	}
	
	private static StringBuilder getInorderTraversal(Node node, StringBuilder inorderTraversal) {
		if(node != null){
			getInorderTraversal(node.left, inorderTraversal);
			inorderTraversal.append(node.data + ",");
			getInorderTraversal(node.right, inorderTraversal);
		}
		else
			inorderTraversal.append("$,");
		return inorderTraversal;
	}

	private static StringBuilder getPreorderTraversal(Node node, StringBuilder preorderTraversal) {
		if(node != null){
			preorderTraversal.append(node.data + ",");
			getPreorderTraversal(node.left, preorderTraversal);
			getPreorderTraversal(node.right, preorderTraversal);
		}
		else
			preorderTraversal.append("$,");
		return preorderTraversal;
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
