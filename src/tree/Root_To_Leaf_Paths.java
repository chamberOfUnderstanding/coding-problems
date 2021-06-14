package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 47un
 * 
 * Print all root to leaf paths of a given binary tree
 * 
 * http://www.geeksforgeeks.org/given-a-binary-tree-print-out-all-of-its-root-to-leaf-paths-one-per-line/
 * http://stackoverflow.com/questions/14936861/print-all-root-to-leaf-paths-in-a-binary-tree 
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * Empty node? return
 * Add this data to the path
 * If this is a leaf node, add it to the list of paths
 * If this is a non leaf node, recurse to find the paths in LST and RST
 * Removes the node last added during a this recursion to assist in backtracking
 *           
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * ==============
 * METHOD 2 : DFS
 * ==============
 * Stack the root
 * Iterate as long as the stack is not empty
 *  Get the stack top and mark it as visited
 *  If this node has an unvisited left child, push it to stack
 *  Else if this node has an unvisited right child, push it to stack
 *  Else if this node is a leaf, print the stack and pop the leaf
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Root_To_Leaf_Paths {

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

		System.out.println("Root to leaf paths are : ");
		printRootToLeafPathsWrapper(node1);
		System.out.println("\nRoot to leaf paths using DFS are : ");
		findRootToLeafPathsDFS(node1);
	}

	private static void printRootToLeafPathsWrapper(Node root) {
		List<List<Integer>> allPaths = new ArrayList<>();
		findRootToLeafPaths(root, new ArrayList<>(), allPaths);
		for(List<Integer> path : allPaths){
			for(Integer node : path)
				System.out.print(node + " ");
			System.out.println();
		}
	}

	private static void findRootToLeafPaths(Node node, List<Integer> path, List<List<Integer>> allPaths) {
		if(node == null)
			return;
		path.add(node.data);
		if(isLeaf(node))
			allPaths.add(new ArrayList<>(path));
		else{
			findRootToLeafPaths(node.left, path, allPaths);
			findRootToLeafPaths(node.right, path, allPaths);
		}
//		remove the node in the end to allow backtracking
//		   1
//		 2   3 
//		4 5
//		Here first path is 1, 2, 4
//		Once found, 4 has to be removed => 1, 2
//		1, 2 is what is required for calculating the path to 5
		path.remove(path.size() - 1);
	}

	private static void findRootToLeafPathsDFS(Node root) {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		boolean[] visited = new boolean[100];
		while(!stack.isEmpty()) {
			Node node = stack.peek();
			visited[node.data] = true;
			if(node.left != null && !visited[node.left.data])
				stack.push(node.left);
			else if(node.right != null && !visited[node.right.data])
				stack.push(node.right);
			else {
				if(isLeaf(stack.peek())) {
					for(int i = 0; i < stack.size(); i++)
						System.out.print(stack.get(i).data + " ");
					System.out.println();
				}
				stack.pop();
			}
		}		
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