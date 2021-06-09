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

	private static void findMissingNaturalNumbers(int[] numbers, int n, int k) {
		// sort the data
		Arrays.sort(numbers);
		
		ArrayList<Long> missingNumbers = new ArrayList<>();
		int current = 0;
		
		// skip negative numbers as the question is to find missing natural numbers
		while(current < n && numbers[current] < 0)
			current++;
		
		// initialize missing as 1
		long missingNumber = 1;
		int previous = Integer.MIN_VALUE;
		
		// repeat until all k missing numbers are found
		while(current < n && missingNumbers.size() < k) {
			
			// skip duplicates by comparing it with previous
			while(current < n - 1 && numbers[current] == previous)
				current++;
			
			// record any missing numbers
			if (missingNumber != numbers[current]) {
				missingNumbers.add(missingNumber);
			} 
			
            // else record the current value as previous
			else {
				previous = numbers[current];
				current++;
			}
			
			// advance to the next missing number
			missingNumber++;
		}
		
		// record any missing numbers beyond array's last item and within k
		while(missingNumbers.size() < k)
			missingNumbers.add(missingNumber++);

		for(long x : missingNumbers) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
}
