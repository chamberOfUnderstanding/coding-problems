package array;

/**
 * @author 47un
 *
 * Given an array arr[], find the maximum j - i such that arr[j] > arr[i].
 * 
 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
 * 
 * =========
 * METHOD 1 
 * ========= 
 * Prepare two arrays, left and right as follows :
 *  For each index i of the array,
 *   left[i] holds the smallest value seen so far (0...i)
 *   right[i] holds the largest value seen so far (i...n)
 * Scan both arrays
 *  If left[i] is < right[j] then consider the difference j - i if it is the maximum difference
 *   Update j, as i is still the smallest element, we may see a larger to the right that can give a better max difference
 *  Else if left[i] > right[j], then look for a new smaller value by updating i
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
		for(int i = 0, j = 0; i < length && j < length; ) {
			if(largestValueToTheRight[j] > smallestValueToTheLeft[i]) {
				maximumDifference = Math.max(maximumDifference, j - i);
				smallest = array[i];
				largest  = array[j]; 
				j++;
			}
			else
				i++;
		}
		System.out.println(smallest + " " + largest);
		return maximumDifference;
	}
}
