package array;

import java.util.Arrays;

/**
 * @author 47un
 *
 * Giving a set of n numbers, 1 to n in which k random numbers are missing. Find them.
 *
 * https://www.geeksforgeeks.org/find-first-k-natural-numbers-missing-given-array/
 * 
 * =========== METHOD ONE ===========
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * 
 */

public class K_Missing_Numbers {

	public static void main(String[] args) {
		test(new int[]{12, 37, 9, 1, 30, 28, 29, 24, 31, 4}, 40);
	}

	private static void test(int[] input, int n) {
		findMissingNumbers_I(input, n, n - input.length);
	}

	private static void findMissingNumbers_I(int[] input, int n, int k) {
		Arrays.sort(input);
		int left = input[0];
		int right = input[0] + n;
		for(int i = 0; i < input.length && left < right; i++) {
			while(++left != input[i])
				System.out.print(left + " ");
			while(--right != input[n - k - i - 1])
				System.out.print(right + " ");
		}
	}

	private static void findMissingNumbers_II(int[] numbers, int n, int k) {
		int prev = 1;
		for(int i = 0; i < numbers.length; i++) {
			int j = prev;
			while(j != numbers[i])
				System.out.print(j++ + " ");
			prev = numbers[i] + 1;
		}
		while(prev != n + 1)
			System.out.print(prev++ + " ");
	}
}
