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
		
		boolean i_flag = true;
		Node i_node = root;
		Stack<Node> i_stack = new Stack<>();
		int i_data = 0;

		boolean ri_flag = true;
		Node ri_node = root;
		Stack<Node> ri_stack = new Stack<>();
		int ri_data = 0;
		
		int sum = 0;
		
		while(true){
			
			// Fetch next inorder node
			while(i_flag){
				
				// Valid node
				// Push it onto stack and move left
				// This repeats until LST is stacked
				if(null != i_node){					
					i_stack.push(i_node);
					i_node = i_node.left;
				}
				
				// Invalid node
				// Pop stack to get the largest among the LST (great parent or whatever, the top most)
				// Record the data and move right to find the successor
				// Turn off the flag for the sum check
				else{
					i_node = i_stack.pop();
					i_data = i_node.data;
					i_node = i_node.right;
					i_flag = false;
				}
			}
			
			// Fetch next reverse inorder node
			while(ri_flag){
				if(null != ri_node){
					ri_stack.push(ri_node);
					ri_node = ri_node.right;
				}
				else{
					ri_node = ri_stack.pop();
					ri_data = ri_node.data;
					ri_node = ri_node.left;
					ri_flag = false;
				}			
			}
			if(i_data >= ri_data)
				return false;
			sum = i_data + ri_data;
			if(sum == targetSum)
				return true;
			
			// Need a higher value, so next inorder node
			else if(sum < targetSum)
				i_flag = true;
			
			// Need a smaller value, so next reverse inorder node
			else
				ri_flag = true;		
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
