package tree.special;

/**
 * @author 47un
 * 
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 *
 * AVL tree is a self-balancing Binary Search Tree (BST) where the difference between heights of left and right subtrees cannot be more than one for all nodes.
 * 
 * But WHY?
 * 
 * Most of the BST operations (e.g., search, max, min, insert, delete.. etc) take O(h) time where h is the height of the BST.
 * The cost of these operations may become O(n) for a skewed Binary tree.
 * If we make sure that height of the tree remains O(logn) after every insertion and deletion, then we can guarantee an upper bound of O(Logn) for all these operations.
 * The height of an AVL tree is always O(logn) where n is the number of nodes in the tree
 * 
 * To make sure that the given tree remains AVL after every insertion, we must augment the standard BST insert operation to perform some re-balancing.
 * Following are two basic operations that can be performed to re-balance a BST without violating the BST property (keys(left) < key(root) < keys(right)). 
 * 	1) Left Rotation
 * 	2) Right Rotation
 * T1, T2 and T3 are subtrees of the tree rooted with y (on left side) or x (on right side)           
 *                 y                               x
 *                / \     Right Rotation          /  \
 *               x   T3   – – – – – – – >        T1   y 
 *              / \       < - - - - - - -            / \
 *             T1  z      Left Rotation             z  T3
 *             
 * Keys in both of the above trees follow the following order
 *   key(T1) < key(x) < key(z) < key(y) < key(T3)
 * Notice how T1 and T3 remain untouched.
 *  They start off as left child of x and right child of y, respectively.
 *  After the rotation, they are still the left child of x and right child of y
 * So BST property is not violated anywhere.
 * 
 * 
 * Insert
 * If the node is null, prepare a node with this data and return it
 * If the data to be inserted is < current node, move left (BST property)
 *  This node's left child becomes the root of LST, after the insertion of the new node
 * Else if the data to be inserted is > current node, move right (BST property)
 *  This node's right child becomes the root of RST, after the insertion of the new node
 * If this node's data = data to be inserted, return the current node
 *  Insertion must not be performed as a BST does not allow duplicate keys
 * Now check if the tree is balanced, if not do some rotations to make sure it is balanced
 * Calculate the height of the current node
 *  height = 1 + Maximum among LST and RST height
 * Calculate the balance factor of the current node 
 *  balance factor = LST height - RST height
 *  
 * Rotate method
 * 	If the tree is left heavy then LST height > RST height and balance factor > 1, then the weight needs to be shifted to the RIGHT!, so right rotation is needed
 * 		If the new data is on the left of this node, do a right rotation (LL case)
 * 		Else if the new data is on the right of this node, do a left rotation, update the left child, and then do a right rotation (LR case) 
 *  If the tree is right heavy then RST height > LST height and balance factor < -1, then the weight needs to be shifted to the LEFT!, so left rotation is needed
 *      If the new data is on the right of this node, do a left rotation (RR case)
 * 		Else if the new data is on the left of this node, do a right rotation, update the right child, and then do a left rotation (RL case)
 * 
 * Right rotation
 * 
 *	  x               y 
 *	 /                 \
 *	y       =>          x 
 *	 \                 / 
 *	  z               z
 *
 * No need to worry about x's left
 * Do the above, update the heights of x and y, return the new root of the sub tree, x
 * 
 * Left rotation
 * 
 * 	 y                x
 *  	  \              /  
 *	   x      =>    y             
 *	  /              \ 
 *	 z                z
 *  
 * No need to worry about y's right	
 * Do the above, update the heights of x and y, return the new root of the sub tree, y 
 * 
 * TIME    : O(logn)
 * SPACE   : O(1)
 * 
 * 
 * The AVL tree and other self balancing search trees like Red Black are useful to get all basic operations done in O(Log n) time. 
 * The AVL trees are more balanced compared to Red Black Trees, but they may cause more rotations during insertion and deletion.
 * So if your application involves many frequent insertions and deletions, then Red Black trees should be preferred.
 * If the insertions and deletions are less frequent and search is more frequent operation, then AVL tree should be preferred over Red Black Tree.
 * 
 * 
 * Delete
 * If node is null, return null
 * If data is < node.data, this node's left = root of LST after deleting the data from it
 * If data is > node.data, this node's right = root of RST after deleting the data from it
 * If data = node.data
 * 	Check if this is a leaf node, if yes, delete it (set it as null)
 *  If this node has a right child only, set this node's value as the right child's value and delete this node's right
 *  If this node has a left child only, set this node's value as the left child's value and delete this node's left
 *  If this node has both children, then 
 *   Fetch the inorder successor of this node (Move right and move left until you hit a null)
 *   Set this node's data as the inorder successor's data
 *   This node's right is the root of the RST after deleting the inorder successor (!!!!!!!!!!!!!!!!!)
 *    Call delete method again for the inorder successor's data and for the RST
 * After the above shenanigans, if the node is null, return null
 * Find the height and balance factor of the tree
 * Rotate the tree and return the root
 * 
 * TIME    : O(logn)
 * SPACE   : O(1)
 * 
 * 
 */

public class AVL_Tree {

	public static void main(String[] args) {
		Node root = null;
		root = insert(5, root);
		root = insert(66, root);
		root = insert(78, root);
		root = insert(79, root);
		root = insert(80, root);
		root = insert(81, root);
		preorderTraverse(root);
		System.out.println();
		root = delete(78, root);
		root = delete(66, root);
		root = delete(79, root);
		preorderTraverse(root);
		System.out.println();
	}

	private static void preorderTraverse(Node node) {
		if(node != null) {
			System.out.print(node.data + " ");
			preorderTraverse(node.left);
			preorderTraverse(node.right);
		}
	}

	private static Node insert(int data, Node node) {
		if(node == null)
			return new Node(data);
		if(data < node.data)
			node.left  = insert(data, node.left);
		else if(data > node.data)
			node.right = insert(data, node.right);
		else
			return node;
		updateHeight(node);
		int balanceFactor = calculateBalanceFactor(node);
		return rotate(node, data, balanceFactor > 1, balanceFactor < -1);
	}

	private static Node delete(int data, Node node) {
		if(node == null)
			return node;
		else if(data < node.data)
			node.left  = delete(data, node.left);
		else if(data > node.data)
			node.right = delete(data, node.right);
		else {
			if(isLeaf(node))
				node = null;
			else if(hasRightChildOnly(node)) {
				node.data = node.right.data;
				node.right = null;
			}
			else if(hasLeftChildOnly(node)) {
				node.data = node.left.data;
				node.left = null;
			}
			else {
				Node inorderSuccessor = findInorderSuccessor(node);
				node.data = inorderSuccessor.data;
				node.right = delete(inorderSuccessor.data, node.right);
			}
		}
		if(node == null)
			return null;
		updateHeight(node);
		int balanceFactor = calculateBalanceFactor(node);
		return rotate(node, data, balanceFactor > 1, balanceFactor < -1);
	}

	private static Node rotate(Node node, int data, boolean leftHeavy, boolean rightHeavy) {
		if(leftHeavy)
			if(data < node.left.data)
				return rotateRight(node);
			else {
				node.left = rotateLeft(node);
				return rotateRight(node);
			}
		else if(rightHeavy)
			if(data > node.right.data)
				return rotateLeft(node);
			else {
				node.right = rotateRight(node);
				return rotateLeft(node);
			}
		return node;
	}

	private static Node rotateRight(Node x) {
		Node y = x.left;
		Node z = y.right;
		y.right = x;
		x.left  = z;
		updateHeight(y);
		updateHeight(x);		
		return y;
	}

	private static Node rotateLeft(Node y) {
		Node x = y.right;
		Node z = x.left;
		x.left  = y;
		y.right = z;
		updateHeight(y);
		updateHeight(x);		
		return x;
	}

	private static void updateHeight(Node node) {
		node.height = 1 + Math.max(fetchHeight(node.left), fetchHeight(node.right));	
	}

	private static int fetchHeight(Node node) {
		return node == null? 0 : node.height;
	}

	private static int calculateBalanceFactor(Node node) {
		return node == null? 0 : fetchHeight(node.left) - fetchHeight(node.right);
	}

	private static Node findInorderSuccessor(Node node) {
		Node inorderSuccessor = node.right;
		while(inorderSuccessor.left != null)
			inorderSuccessor = inorderSuccessor.left;			
		return inorderSuccessor;
	}

	private static boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}

	private static boolean hasLeftChildOnly(Node node) {
		return node.left != null && node.right == null;
	}

	private static boolean hasRightChildOnly(Node node) {
		return node.left == null && node.right != null;
	}

	private static class Node{
		int data;
		Node left;
		Node right;
		int height;

		Node(int data){
			this.data = data;
			this.height = 1;
		}
	}
}
