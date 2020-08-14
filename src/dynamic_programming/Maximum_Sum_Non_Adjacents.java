package dynamic_programming;

/**
 * @author 47un
 *
 * Given an array of positive & negative numbers
 * Find the maximum sum of a subsequence with the constraint that no 2 numbers in the sequence should be adjacent in the array.
 * So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should return 15 (sum of 3, 5 and 7).
 * 
 * http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 * 
 * METHOD 1 : DP
 *            For each index (i) the sum is the largest among the sum of the adjacent index (i-1) and the sum of the non adjacent index (i-1) including value of this index
 *            Since we dont want the adjacent item in our sum, it boils down to picking the largest sum obtained by adding the current value to the sum till non adjacent
 *             and the sum till adjacent (without including current)
 *             
 * TIME    : O(n)
 * SPACE   : O(n)
 * 
 */

public class Maximum_Sum_Non_Adjacents {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        int[] array = {5, 5, 10, 100, 10, 5};
        int[] array1 = {3, 2, -7, -10};
        System.out.println(findMaximumSumNonAdjacents(array1));
    }

    private static int findMaximumSumNonAdjacents(int[] array) {
        int[] sum = new int[array.length];
        sum[0] = array[0];
        sum[1] = Math.max(array[0], array[1]);
        for(int i = 2; i < array.length; i++)
            sum[i] = Math.max(sum[i - 1], sum[i - 2] + array[i]);
        return sum[array.length - 1];
    }
}
