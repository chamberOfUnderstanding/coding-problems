package array;

/**
 * @author 47un
 * 
 * Given an array A.
 * Equilibrium position is a position such that the sum of elements before it is equal to the sum of elements after it.
 * Find it!
 * 
 * http://www.geeksforgeeks.org/equilibrium-index-of-an-array/
 * 
 * =========================
 * METHOD 1 : Track the sums
 * ========================= 
 * Find the sum of all items in the array
 * Maintain leftSum
 * Scan the array
 *   If leftSum = sum - item, return element
 *   Update leftSum and sum
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Equilibrium_Index {

	public static void main(String[] args) {
		test(new int[] {1});
		test(new int[]{99, 1, 4, 5, 0, 99, 7, 0, 208, -99, 99});
		test(new int[]{47, 0, 0, 0, 0, 0, -4, 1, 0, 43});
		test(new int[]{4, -22123, 0, -4, 1, 0, 43});
		test(new int[]{7, 7, 7, 7, 7, 7, 7, 7, 7});
	}

	private static void test(int[] input) {
		int equilibriumIndex = equilibriumIndex_I(input);
		if(equilibriumIndex != -1)
			System.out.println("Index : " + equilibriumIndex + " Value : " + input[equilibriumIndex]);
		else
			System.out.println("There aint any");
	}

	private static int equilibriumIndex_I(int[] input) {
		int sum = addAllElements(input);
		int leftSum = 0;
		for(int i = 0; i < input.length; i++)
			if(leftSum == (sum - input[i]))
				return i;
			else {
				leftSum += input[i];
				sum -= input[i];
			}
		return -1;
	}

	private static int addAllElements(int[] array) {
		int sum = 0;
		for(int element : array)
			sum += element;
		return sum;
	}
}
