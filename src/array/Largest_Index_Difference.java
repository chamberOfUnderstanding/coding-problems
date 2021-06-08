package array;

/**
 * @author 47un
 *
 * Given an array arr[], find the maximum j - i such that arr[j] > arr[i].
 * 
 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
 *            
 * TIME    : O(n)
 * SPACE   : O(n)
 * 
 */
 
public class Largest_Index_Difference {

	public static void main(String[] args) {
		int[] array = {34, 8, 10, 3, 2, 80, 30, 33, 1};
		System.out.println(findMaximumDifference(array));		 
	}

	private static int findMaximumDifference(int[] array) {
		int length = array.length;
		
		// Prepare two arrays, left and right as follows :
                // left[i] holds the smallest value seen so far when going from 0 to i
                // right[i] holds the largest value seen so far when going from n to i
		int[] smallestValueToTheLeft = new int[length];
		int[] largestValueToTheRight = new int[length];
		smallestValueToTheLeft[0] = array[0];
		largestValueToTheRight[length - 1] = array[length - 1];
		for(int i = 1; i < length; i++)
			smallestValueToTheLeft[i] = Math.min(array[i], smallestValueToTheLeft[i - 1]);
		for(int i = length - 2; i >= 0; i--)
			largestValueToTheRight[i] = Math.max(array[i], largestValueToTheRight[i + 1]);
		
		int maximumDifference = Integer.MIN_VALUE;
		int smallest = 0;
		int largest  = 0;
		
		// Scan both arrays simulatenously
		for(int i = 0, j = 0; i < length && j < length; ) {			
			// if right[j] > left[i], it satisfies the max difference condition as j > i
			if(largestValueToTheRight[j] > smallestValueToTheLeft[i]) {
				// update maximumDifference with the largest value
				maximumDifference = Math.max(maximumDifference, j - i);
				smallest = array[i];
				largest  = array[j]; 
				// update j along
				// i is NOT updated as there is a chance of encountering a larger j which would maximize the diff further
				j++;
			}
			else {
				// update i as we require a new smaller value
				i++;
			}
		}
		System.out.println(smallest + " " + largest);
		return maximumDifference;
	}
}
