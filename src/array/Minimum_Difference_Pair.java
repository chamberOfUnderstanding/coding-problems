package array;

import java.util.Arrays;

/**
 * @author 47un
 * 
 * Given an unsorted array, find the minimum difference between any pair in given array.
 * 
 * http://www.geeksforgeeks.org/find-minimum-difference-pair/
 * 
 * =========
 * METHOD 1
 * =========
 * Sort the array
 * Scan the array
 *  Track the difference between adjacent elements
 *  Record the least difference
 * 
 * TIME     : O(nlogn)
 * SPACE    : O(1)
 *
 * 
 */

public class Minimum_Difference_Pair {

    public static void main(String[] args) {
        int[] array = {1, 19, -4, 31, 38, 25, 100};
        findMinimumDifference(array);
    }

    private static void findMinimumDifference(int[] array){
        Arrays.sort(array);
        int minimumDifference = Integer.MAX_VALUE;
        for(int i = 0; i < array.length - 1; i++)
            minimumDifference = Math.min(minimumDifference, array[i + 1] - array[i]);
        System.out.println(minimumDifference);
    }
}
