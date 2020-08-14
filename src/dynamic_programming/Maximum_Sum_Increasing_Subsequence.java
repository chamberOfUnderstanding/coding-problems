package dynamic_programming;

import java.util.Arrays;

/**
 * @author 47un
 * 
 * Given an array of n positive integers.
 * Find the sum of maximum sum subsequence of the given array such that the integers in the subsequence are sorted in increasing order.
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
 * 
 * =========
 * METHOD 1
 * =========
 * Variant of LIS
 * In LIS the solution array is initialized as 1 for every index (As every item in the array is itself a LIS of size 1)
 * Here every item in the array is a maximum sum LIS with a value = the item's value 
 * Do the normal LIS
 *  If item j is > item i
 *   Find the largest among the max value seen till j and max value seen at i + j's value   
 * Find the largest value in the solution array
 *  
 * TIME     : O(n^2)
 * SPACE    : O(n)
 *
 * 
 */

public class Maximum_Sum_Increasing_Subsequence {
    
    public static void main(String[] args) {
        int[] array = {1, 101, 2, 3, 100, 4, 5};
        System.out.println(findMaximumSumLIS(array));
    }

    private static int findMaximumSumLIS(int[] array) {
        int[] maxSumLIS = Arrays.copyOf(array, array.length);
        for(int i = 0; i < array.length; i++)
            for(int j = i + 1; j < array.length; j++)
                if(array[j] > array[i])
                    maxSumLIS[j] = Math.max(maxSumLIS[j], maxSumLIS[i] + array[j]);
        int maximum = maxSumLIS[0];
        for(int i = 1; i < array.length; i++)
            maximum = Math.max(maximum, maxSumLIS[i]);
        return maximum;
    }
}
