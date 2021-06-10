package array;

/**
 * @author 47un
 *
 * An array A contains n ≥ 1 positive integers in the locations A[1], A[2], …A[n]. 
 * Find the shortest sequence of consecutive elements of A, A[i], A[i + 1], …, A[j] such that the sum of their values is ≥ M, a given positive number.
 * Prints ‘n+1’ if no such sequence exists
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan the items
 *            If the target sum is not reached, add the item to the sum and stretch the window
 *            If the target sum is reached, update minimum window size (if it's smaller)
 *            If the window size is 0 (an element that's same as the target sum is present in the array, best case!), break!
 *            Else subtract the first item in the window from the sum and shrink the window           
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Smallest_Sub_Array_With_Target_Sum {

	public static void main(String...x){
		int[] array = {1,2,14,5,6,7,8,9,13,1};
		int targetSum = 14;
		System.out.println("\nSmallest window : " + getSmallestSubArray(array, targetSum));
	}
	
	private static int getSmallestSubArray(int[] array, int targetSum) {
		// assume the biggest window possible
		int smallestWindow = array.length;
		int startIndex = 0;
		int endIndex = 0;
		int sum = array[startIndex];
		while(true){
			// if the target sum is not reached
			if(sum < targetSum){
				
				// end of the line, break
				if(endIndex == array.length-1)
					break;
				
				// advance the window
				endIndex++;
				
				// add the current item to sum
				sum += array[endIndex];
			}
			// if target sum has reached
			else{
				// update smallest window
				smallestWindow = Math.min(smallestWindow, endIndex - startIndex);
				printWindow(array, startIndex, endIndex);
				
				// an element that's same as the target sum is present in the array, best case!
				if(smallestWindow == 0)
				    break;
				
				// avoid the first item from the sum
				sum -= array[startIndex];
				
				// shrink
				startIndex++;	
			}
		}
		return smallestWindow + 1; 
	}

	private static void printWindow(int[] array, int startIndex, int endIndex){
		System.out.println();
		for(int i = startIndex; i <= endIndex; i++)
			System.out.print(array[i] + " ");
	}
}
