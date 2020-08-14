package tree.bst;

import java.util.Stack;

/**
 * @author 47un
 *
 * Check if the given array forms a valid BST preorder traversal
 * e.g. 16 22 24 : Valid
 *      22 24 16 : Invalid
 * 
 * http://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
 * 
 * =================
 * METHOD 1 : Stack
 * =================
 * Track the minimum element using a variable
 * Scan the given preorder traversal
 *  If the current item is less than the minimum, this traversal is invalid
 *  If the current item is greater than the stack top, pop the stack and make that item the minimum
 *   This happens when the NGE of stack top is seen.
 *   Then the stack top becomes minimum & we gotta make sure we dont see anything smaller than that afterwards, which is the first step
 *  All items get pushed
 * 
 * This approach is based on the following logic :
 *     5
 *    / \
 *   3   9
 *  / \   \
 * 1   4   15
 * 
 * 5 3 1 4 9 15 is a valid Preorder traversal
 * The NGE of 5 is 9, and after 9 no element has a value < 5
 * The NGE of 3 is 4, and after 4 no element has a value < 3
 * The NGE of 1 is 4, and after 4 no element has a value < 1
 * To summarize, the given array is a valid BST preorder traversal if :
 *  No element to the right of an element, say x, has a value > the NGE(x) 
 *      OR
 *  All elements to the right of an element, say x, have a value < NGE(x)    
 *  Elements to the right of x can have smaller values (Like 5 3 1 4) in this example, only until the NGE is seen (i.e. 9 the NGE(5) in this example)
 *   
 * TIME    : O(n)
 * SPACE   : O(height)
 * 
 */

public class Is_BST_Preorder_Traversal {

	public static void main(String[] strings) {
		int[] preorderTraversal = {5, 3, 1, 4, 9, 15};

		System.out.print("This is ");
		System.out.print(isBSTPreorderTraversal(preorderTraversal)? "a valid " : "an invalid ");
		System.out.println("BST preorder traversal");
	}

	public static boolean isBSTPreorderTraversal(int[] preorderTraversal){
		Stack<Integer> stack = new Stack<Integer>();
		int minimum = Integer.MIN_VALUE;
		for (int i = 0; i < preorderTraversal.length; i++){
			if (preorderTraversal[i] < minimum)
				return false;
			while(!stack.isEmpty() && preorderTraversal[i] >= stack.peek())
				minimum = stack.pop();
			stack.push(preorderTraversal[i]);
		}
		return true;
	}
}