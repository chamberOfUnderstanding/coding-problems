package dynamic_programming;

import java.util.Arrays;

/**
 * @author 47un
 * 
 * Given an array of n positive integers, a subsequence of is called Bitonic if it is first increasing, then decreasing.
 * Find the length of the longest bitonic subsequence.
 * A sequence, sorted in increasing order is considered Bitonic with the decreasing part as empty.
 * Similarly, decreasing order sequence is considered Bitonic with the increasing part as empty.
 * 
 * Examples:
 * Input arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
 * Output: 6 (A Longest Bitonic Subsequence of length 6 is 1, 2, 10, 4, 2, 1)
 * 
 * Input arr[] = {12, 11, 40, 5, 3, 1}
 * Output: 5 (A Longest Bitonic Subsequence of length 5 is 12, 11, 5, 3, 1)
 *
 * Input arr[] = {80, 60, 30, 40, 20, 10}
 * Output: 5 (A Longest Bitonic Subsequence of length 5 is 80, 60, 30, 20, 10)
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
 * 
 * METHOD 1 : Dynamic Programming
 *
 * TIME     : O()
 * SPACE    : O()
 *
 * 
 */

public class Longest_Bitonic_Subsequence {

    public static void main(String[] args) {
        int[] array = {1, 11, 2, 10, 4, 5, 2, 1};
        System.out.println(longestBitonicSubsequence(array));
    }

    private static int longestBitonicSubsequence(int[] array) {
        int[] parentLIS = new int[array.length];
        Arrays.fill(parentLIS, -1);
        int[] lis = findSequence(array, parentLIS, true);
        int[] parentLDS = new int[array.length];
        Arrays.fill(parentLDS, -1);
        int[] lds = findSequence(array, parentLDS, false);        
        return longest(array, lis, parentLIS, lds, parentLDS);
    }

    private static int[] findSequence(int[] array, int[] parent, boolean increasing) {
        int[] sequence = new int[array.length];
        Arrays.fill(sequence, 1);
        for(int i = 0; i < array.length; i++)
            for(int j = i + 1; j < array.length; j++)
                if(increasing && array[j] > array[i] || !increasing && array[j] < array[i]) {
                    sequence[j] = Math.max(sequence[j], sequence[i] + 1);
                    parent[j] = i;
                }
        return sequence;
    }

    private static int longest(int[] array, int[] lis, int[] lds, int[] parentLIS, int[] parentLDS) {
        int longestSequence = lis[0] + lds[0] - 1;
        int maxParentLIS = 0;
        int maxParentLDS = 0;
        for(int i = 1; i < lis.length; i++)
            if(lis[i] + lds[i] - 1 > longestSequence) {
                longestSequence = lis[i] + lds[i] - 1; 
                maxParentLIS = maxParentLDS = i;
            }
//        printSequence(maxParentLIS, parentLIS, array);
//        printSequence(maxParentLDS, parentLDS, array);
        return longestSequence;
    }

    private static void printSequence(int maxParent, int[] parent, int[] array) {
        while(maxParent != -1) {
            System.out.print(array[maxParent]);
            maxParent = parent[maxParent];
        }
    }
}
