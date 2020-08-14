package array;

import java.util.Arrays;

/**
 * @author 47un
 *
 * Given an array of n positive integers with repeating elements.
 * Find maximum difference between the frequency of any two different elements, such that the element with greater frequency is also greater in value than the second integer.
 *
 * http://www.geeksforgeeks.org/maximum-difference-between-frequency-of-two-elements-such-that-element-having-greater-frequency-is-also-greater/
 * 
 * ==================
 * METHOD 1 : Sorting
 * ==================
 * Sort the array
 * Scan from both ends, l and r
 *  Scan until a new item is seen on the left
 *   Update frequency of left item accordingly
 *  Do the same for right item
 *  Update the difference  
 *             
 * TIME    : O(nlogn)
 * SPACE   : O(1)
 * 
 */

public class Largest_Frequency_Difference {

	public static void main(String[] args) {
		test(new int[]{ 3, 1, 3, 2, 3, 2 });
	}

	private static void test(int[] input) {
		System.out.println(findLargestFrequencyDifference(input));
	}

	private static int findLargestFrequencyDifference(int[] array) {
		Arrays.sort(array);
		int freqDiff = Integer.MIN_VALUE;
		int l = 0;
		int r = array.length - 1;
		int leftFreq  = 0;
		int rightFreq = 0;
		while(true) {
			while(l != array.length - 1 && array[l] == array[l + 1]) {
				leftFreq++;
				l++;
			}
			while(r != 0 && array[r] == array[r - 1]) {
				rightFreq++;
				r--;
			}
			freqDiff = Math.max(freqDiff, rightFreq - leftFreq); 
			if(l == array.length - 1 || r == 0 || array[l] != array[r])
				break;
		}
		return freqDiff;
	}
}
