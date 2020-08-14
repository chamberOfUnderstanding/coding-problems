package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 47un
 *
 * https://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
 * 
 * getPeaks_I()  
 * 
 * ~ Scan everything and compare with the previous and next items. 
 * ~ O(n) time.
 * + Finds all peaks
 * 
 * getPeaks_II()
 * 
 * ~ Binary Search-ish. 
 * ~ Grab the middle element. 
 *   ~ If it's < both its neighbors, it's a peak.
 *   ~ If the left is > the middle, there's a peak in the left.
 *   ~ If the right is > the middle, there's a peak in the right.
 * + O(logn) time.
 * - Finds only one peak. 
 * 
 */

public class Peak_Finding_1D {

	public static void main(String[] args) {
		test(new int[]{5, 10, 20, 15});
		test(new int[]{100, 200, 150, 20, 230, 900, 670});
		test(new int[]{1, 2, 3, 4, 5});
		test(new int[]{1000, 800, 700, 600, 500});
		test(new int[]{54, 54, 54, 54, 54});
		test(new int[]{22});
		test(new int[]{87, 89});
		test(new int[]{97, 89});
	}


	private static void test(int[] input) {
		for(int peak : getPeaks_I(input))
			System.out.print(peak + " ");
		System.out.println();
	}

	private static List<Integer> getPeaks_I(int[] input) {
		List<Integer> peaks = new ArrayList<>();
		for(int i = 0; i < input.length; i++)
			if((i == 0 || input[i - 1] <= input[i]) && (i == (input.length - 1) || input[i] >= input[i + 1]))
				peaks.add(input[i]);
		return peaks;
	}

	private static List<Integer> getPeaks_II(int[] input) {
		List<Integer> peaks = new ArrayList<>();
		getPeaks_II(0, input.length - 1, input, peaks);
		return peaks;
	}

	private static void getPeaks_II(int low, int high, int[] input, List<Integer> peaks) {
		int mid = (low + high) / 2;
		if((mid == 0 || input[mid] >= input[mid - 1]) && 
				(mid == (input.length - 1) || input[mid] >= input[mid + 1]))
			peaks.add(input[mid]);
		else if (mid > 0 && input[mid - 1] >= input[mid])
			getPeaks_II(low, mid - 1, input, peaks);
		else getPeaks_II(mid + 1, high, input, peaks);
	}
}
