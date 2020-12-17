package array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 47un
 *
 * Giving a set of n numbers, 1 to n in which k random numbers are missing. Find them.
 *
 * https://www.geeksforgeeks.org/find-first-k-natural-numbers-missing-given-array/
 * 
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * 
 */

public class Missing_Natural_Numbers {

	public static void main(String[] args) {
		test(new int[]{-5, 10, -3}, 10);
		test(new int[]{4, -2, -3}, 2);
		test(new int[]{9, 1, 2, 7, 9, 5, 6, 2, 4, 4, 5, 5, 10,10, 1}, 2);
		test(new int[]{9, 9, 8, 2, 8, 8, 3, 9, 6, 9, 1, 8, 6, 4, 3, 4, 9, 4, 4, 6}, 5);
	}

	private static void test(int[] input, int k) {
		findMissingNaturalNumbers(input, input.length, k);
	}


	private static void findMissingNaturalNumbers(int[] input, int n, int k) {
		Arrays.sort(input);
		ArrayList<Long> missingNumbers = new ArrayList<>();
		int current = 0;
		// Skip positive numbers
		for(; current < n && input[current] < 0; current++);
		long missingNumber = 1, previous = Integer.MIN_VALUE;
		while(current < n && missingNumbers.size() < k) {
			// Skip duplicate numbers
			for (; input[current] == previous && current < n - 1; current++);
			// Record any missing numbers else record the current index as previous
			if (missingNumber != input[current]) {
				missingNumbers.add(missingNumber);
			} else {
				previous = input[current];
				current++;
			}
			missingNumber++;
		}
		for (; missingNumbers.size() < k; missingNumbers.add(missingNumber++));

		for(long x : missingNumbers) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
}
