package tree.bst;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Given a BST and a number.
 * Check if any two nodes of the BST can give a sum of equal to this number
 * e.g. 
 *         1       
 *       /    \ 
 *      2      3    
 *     / \    / \ 
 *    4   5  6   7
 *    
 * If the input number is 13, the result should be true coz 6+7 = 13
 * If the input number is 19, the result should be false 
 * 
 * =========
 * METHOD 1
 * =========
 * Find inorder traversal (Sorted list) and detect sum pair in this list
 * Check 'Sum_Pair_In_Sorted_Array.java'
 * 
 * =========
 * METHOD 2
 * =========
 * Fetch first node of inorder traversal,
 * Fetch first node of reverse inorder traversal,
 *  Sum them up and see if we have the pair
 *     yes? print it!
 *     no? fetch the next nodes of both traversals and repeat
 *     
 * Maintain flags to fetch exactly one node of both inorder and reverse inorder traversals
 * Maintain two stacks to store nodes of both traversals
 * While the fetch inorder flag is on, fetch the next node of inorder traversal
 *  Add the inorderNode to stack and move left
 *  If inorderNode is null, then pop the stack, retrieve the data and move right
 *  Since we have the data of the inorderNode, turn the flag off for now
 * Similarly fetch the next reverseInorderNode 
 *  First move right till the leaf is hit
 *  Then pop the stack, record the data and turn the flag off
 * If the data from inorderNode is greater than or equal to reverseInorderNode, that means
 *   we have considered all pairs and we have reached the middle of both traversals, so the sum pair doesnt exist
 * NOTE : in both inorder and reverse inorder traversal, the middle elements will be the same (the root) 
 * If the detectedSum is equal to target sum, bingo! return!
 * If it's less then we have to increase this sum, i.e. fetch next node of inorder traversal, so turn on that flag
 * If it's greater then we have to decrease this sum, i.e. fetch next node of reverse inorder traversal, so turn on that flag
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 */

public class BST_Sum_Pair{
    
	public static void main(String...strings){
			Node node7 = new Node(250);
			Node node6 = new Node(16);
			Node node5 = new Node(12);
			Node node4 = new Node(8);
			Node node3 = new Node(node6, 20, node7);
			Node node2 = new Node(node4, 10, node5);
			Node node1 = new Node(node2, 15, node3);
			int target = 266;
			System.out.println(detectSumPair(node1, target));
	}

	private static boolean detectSumPair(Node root, int targetSum) {
		boolean fetchInorderNode = true;
		boolean fetchReverseInorderNode = true;
		Node inorderNode = root;
		Node reverseInorderNode = root;
		Stack<Node> inorderStack = new Stack<>();
		Stack<Node> reverseInorderStack = new Stack<>();
		int detectedSum = 0;
		int inorderData = 0;
		int reverseInorderData = 0;
		while(true){
			while(fetchInorderNode){
				if(null != inorderNode){					
					inorderStack.push(inorderNode);
					inorderNode = inorderNode.left;
				}
				else{
					inorderNode = inorderStack.pop();
					inorderData = inorderNode.data;
					inorderNode = inorderNode.right;
					fetchInorderNode = false;
				}		
			}
			while(fetchReverseInorderNode){
				if(null != reverseInorderNode){
					reverseInorderStack.push(reverseInorderNode);
					reverseInorderNode = reverseInorderNode.right;
				}
				else{
					reverseInorderNode = reverseInorderStack.pop();
					reverseInorderData = reverseInorderNode.data;
					reverseInorderNode = reverseInorderNode.left;
					fetchReverseInorderNode = false;
				}			
			}
			if(inorderData >= reverseInorderData)
				return false;
			detectedSum = inorderData + reverseInorderData;
			if(detectedSum == targetSum)
				return true;
			else if(detectedSum < targetSum)
				fetchInorderNode = true;
			else
				fetchReverseInorderNode = true;		
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
