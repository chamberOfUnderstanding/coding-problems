/**
 * @author Solaire
 * 
 * =========
 * METHOD 1 
 * =========
 * 
 * 
 * TIME     : O()
 * SPACE    : O()
 *
 */
package tree;

import java.util.LinkedList;
import java.util.Queue;

public class TODO_Burn_Binary_Tree {

	public static void main(String... x) {
		Node root = getTree();
		LinkedList<Node> path = new LinkedList<Node>();
		Queue<Node> bs = new LinkedList<Node>();
		getPath(root, 9, path);
		int i = 0;
		bs.add(path.get(0));
		while(!bs.isEmpty()) {
			Node cn = bs.poll();
			System.out.println(cn.data);
			
		}
		
	}

	private static boolean getPath(Node n, int target, LinkedList<Node> path) {
		if(n.left == null && n.right == null) {
			return false;
		}
		if (n.data == target) {
			path.add(n);
			return true;
		} else {
			if (getPath(n.left, target, path)) {
				path.add(n);				
				return true;
			}
			if (getPath(n.right, target, path)) {
				path.add(n);
				return true;
			}
			return false;
		}
	}

	private static Node getTree() {
		Node node11 = new Node(null, 11, null);
		Node node10 = new Node(null, 10, null);
		Node node7 = new Node(null, 7, null);
		Node node5 = new Node(null, 5, null);
		Node node4 = new Node(null, 4, null);
		Node node9 = new Node(node10, 9, node11);
		Node node8 = new Node(null, 8, node9);
		Node node6 = new Node(node8, 6, null);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node4, 2, node5);
		Node node1 = new Node(node2, 1, node3);
		return node1;
	}

	private static class Node {
		int data;
		Node left;
		Node right;

		public Node(Node left, int data, Node right) {
			this.left = left;
			this.data = data;
			this.right = right;
		}
	}
}
