package stack;

/***
 * You have three stacks of cylinders where each cylinder has the same diameter, but they may vary in height. 
 * You can change the height of a stack by removing and discarding its topmost cylinder any number of times.
 * Find the maximum possible height of the stacks such that all of the stacks are exactly the same height. 
 * This means you must remove zero or more cylinders from the top of zero or more of the three stacks until they're all the same height, then print the height. 
 * The removals must be performed in such a way as to maximize the height.
 * 
 * Sample Input
 * 5 3 4
 * 3 2 1 1 1
 * 4 3 2
 * 1 1 4 1
 *
 * Sample Output
 * 5
 * 
 * =====================
 * METHOD 1 : Iteration
 * =====================
 * Find the height of each stack and store it in an array
 * Iterate while the heights are unequal
 * Find the max height
 * Scan the three stacks to see which has the max height
 *  For all the stacks that have the max height, reduce the height by the value of the topmost element and go down the stack
 * Return height
 * 
 * TIME  : O(n)
 * SPACE : O(1)
 * 
 */

public class Equal_Stacks {

	public static void main(String...strings){
		int[] stack1  = {3, 2, 1, 1, 1};
		int[] stack2  = {4, 3, 2};
		int[] stack3  = {1, 1, 4, 1};
		int[] heights = {findHeight(stack1), findHeight(stack2), findHeight(stack3)};
		int i = 0;
		int j = 0;
		int k = 0;
		while(!heightsAreEqual(heights)){			
			int max = maxHeight(heights);
			if(heights[0] == max)
				heights[0] -= stack1[i++];
			if(heights[1] == max)
				heights[1] -= stack2[j++];
			if(heights[2] == max)
				heights[2] -= stack3[k++];
			if(i == stack1.length || j == stack2.length || k == stack3.length)
			    break;
		}
		System.out.println(heights[0]);
	}

	private static boolean heightsAreEqual(int[] heights) {
		return heights[0] == heights[1] && heights[1] == heights[2];
	}

	private static int maxHeight(int[] heights) {
		return Math.max(heights[0], Math.max(heights[1], heights[2]));
	}

	private static int findHeight(int[] stack) {
		int height = 0;
		for(int cylinder : stack)
			height += cylinder;
		return height;
	}
}
