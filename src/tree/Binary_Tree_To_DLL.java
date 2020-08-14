package tree;

/**
 * @author 47un
 * 
 * Convert a binary tree to a doubly linked list
 * After conversion, the tree node's left child becomes the DLL's previous and the right child becomes the DLL's next nodes
 * 
 * http://www.geeksforgeeks.org/in-place-convert-a-given-binary-tree-to-doubly-linked-list/
 * http://www.geeksforgeeks.org/convert-a-given-binary-tree-to-doubly-linked-list-set-2/
 * http://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * Do inorder traversal
 * Keep track of first and last nodes of the list
 * While visiting a node, connect it to the end of the DLL (last.right = node and node.left = last)
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * =====================
 * METHOD 2 : Successors
 * =====================
 * If the tree is empty, quit
 * Convert the tree to a DLL
 *  For each node the inorder successor and predecessor is to be found out, so O(nlogn)
 *  Empty node? return null
 *  Left child exists for the node?
 *    Convert the left sub tree to a DLL
 *    Find the inorder predecessor of the current node
 *    Connect inorder predecessor to current node and viz.
 *  Right child exists for the node?
 *    Convert the right sub tree to a DLL
 *    Find the inorder successor of the current node
 *    Connect inorder successor to current node and viz. 
 * Now the tree is converted, but the root is still the original root of the tree and not the start of the DLL
 * The DLL starts at the left most node of the tree
 * So traverse the left side of the tree and set the root as the left most node of the tree
 *  
 * TIME     : O(nlogn)
 * SPACE    : O(n)
 * 
 */

public class Binary_Tree_To_DLL {

	static Node first = null;
	static Node last = null;

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
		Node node = convertTreeToDLLUsingSuccessorsWrapper(node1);
		while(node != null){
			System.out.print(node.data + " ");
			node = node.right;
		}
	}

	@SuppressWarnings("unused")
    private void convertTreeToDLL(Node node) {
		if(node != null){
			convertTreeToDLL(node.left);
			if(first == null)
				first = node;
			else{
				last.right = node;
				node.left = last;
			}
			last = node;
			convertTreeToDLL(node.right);
		}
	}

	private static Node convertTreeToDLLUsingSuccessorsWrapper(Node root){
		if(root == null)
			return null;
		root = convertTreeToDLLUsingSuccessors(root);
		for( ; root.left != null ; root = root.left);
		return root;
	}

	private static Node convertTreeToDLLUsingSuccessors(Node node){
		if(node == null)
			return node;
		if(node.left != null){
			Node leftSubTree = convertTreeToDLLUsingSuccessors(node.left);
			for( ; leftSubTree.right != null ; leftSubTree = leftSubTree.right);
			leftSubTree.right = node;
			node.left = leftSubTree;
		}
		if(node.right != null){
			Node rightSubTree = convertTreeToDLLUsingSuccessors(node.right);
			for( ; rightSubTree.left != null ; rightSubTree = rightSubTree.left);
			node.right = rightSubTree;
			rightSubTree.left = node;
		}
		return node;
	}
	
    private static class Node{
        int data;
        Node left;
        Node right;

        Node(Node left,int data,Node right){            
            this.left = left;
            this.data = data;
            this.right = right;         
        }
    }
}
