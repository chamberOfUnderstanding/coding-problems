package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * In an array, find all element pairs which have the given absolute difference. 
 */


public class Absolute_Difference {

	public static void main(String[] args) {
		int[] array = {115, 95, 2, 55, 92, 12, 12, 32, 105,33, 22, 39, 43, 6};
		int difference = 10;
		System.out.println("Method 1");
		methodOne(array, difference);
		System.out.println("\nMethod 2");
		methodTwo(array, difference);
	}

	/**
	 * T O(n)
	 * S O(n)
	 */
	public static void methodOne(int[] array, int difference) {
		Set<Integer> set = new HashSet<>();
		for(int item : array) {
			// Check if the item +- difference is already in the set
			if(set.contains(item + difference) || set.contains(item - difference))
				System.out.println(item + " " + (item + difference));
			set.add(item);
		}
	}

	/**
	 * T O(nlogn) + O(n) => O(nlogn)
	 * S O(1)
	 */
	public static void methodTwo(int[] array, int difference) {
		// Sort the array
		Arrays.sort(array);
		// Maintain two indices
		for(int left = 0, right = left + 1; left < array.length && right < array.length; ) {
			int currentDiff = array[right] - array[left];
			// Increment both if the difference matches
			if(currentDiff == difference) {
				System.out.println(array[left] + " " + array[right]);
				left++;
				right++;
			} 
			// Increment left if the difference is greater
			else if(currentDiff > difference)
				left++;
			// Else increment right
			else
				right++;
		}
	}
}
