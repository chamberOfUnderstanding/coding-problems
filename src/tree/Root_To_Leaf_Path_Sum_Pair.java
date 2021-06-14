package tree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 47un
 * 
 * Given a binary tree, find if there is a pair in root to a leaf path such that sum of values in pair is equal to root’s data
 * 
 * http://www.geeksforgeeks.org/find-pair-root-leaf-path-sum-equals-roots-data/
 * 
 * ==============================
 * METHOD 1 : Recursion + HashSet
 * ==============================
 * Prepare a hash set and look for the pair in the sub trees
 *  If the task is to just look for a pair, finding it in either LST or RST is enough (printPairs = false)
 *  Else if the all valid pairs need to be printed, search for them in BOST LST & RST (printPairs = true)
 * To look inside a sub tree
 *  If the node is null, false! duh!
 *  If the hash set contains this node's data (Because some one else in this root to leaf path was looking for it), true!
 *  Add root - node's data to the hash set
 *  Give the updated hash set to LST and RST and look for a match over there
 *  If either of them gives a true, return true
 *  Else remove the last entered item from the hash set
 *   Since the task is to find a pair in the ROOT TO LEAF PATH, that data is no longer valid
 *   Had it been find a pair in the entire TREE, that data would've been useful
 * 
 * TIME     : O(n)
 * SPACE    : O(height) // Stack space will grow up to tree height + Hash set grow up to a size = height 
 *        
 *        17
 *       2   3
 *    15  5 6  7
 *              8
 *               9
 * 
 */

public class Root_To_Leaf_Path_Sum_Pair {

	public static void main(String...strings){
		Node node6 = new Node(null, 6, null);
		Node node5 = new Node(null, 5, null);
		Node node15 = new Node(null, 15, null);
		Node node9 = new Node(null, 9, null);
		Node node8 = new Node(null, 8, node9); 
		Node node7 = new Node(null, 7, node8);
		Node node3 = new Node(node6, 3, node7);
		Node node2 = new Node(node15, 2, node5);
		Node node1 = new Node(node2, 17, node3);

		int sum = node1.data;
		isPairPresent(node1, sum);
	}

	private static void isPairPresent(Node root, int target) {
		Set<Integer> hashSet = new HashSet<>();
		System.out.println(
				"Is pair present " + 
						(
								isPairPresent(root.left, target, hashSet) || 
								isPairPresent(root.right, target, hashSet)
								)
				);
	}

	private static boolean isPairPresent(Node node, int target, Set<Integer> hashSet) {
		if(node != null) {
			// if the data is in the set, return true/print it
			if(hashSet.contains(node.data)) {
				printPair(node.data, target - node.data);
				return true;
			}
			// else add target - node.
			// if some one has this value, then we have a pair
			hashSet.add(target - node.data);
			// check the LST and RST for pairs
			if(isPairPresent(node.left, target, hashSet) 
					|| isPairPresent(node.right, target, hashSet))
				return true;
			// if neither has it, remove the data from the set
			else
				hashSet.remove(target - node.data);
		}
		return false;
	}

	private static void printPair(int data1, int data2) {
		System.out.println("{" + data1 + " , " + data2 + "}");
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