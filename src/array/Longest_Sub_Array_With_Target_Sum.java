package array;

/**
 * @author 47un
 *
 * Given an unsorted array of positive numbers and a target sum.
 * Find the sub array of maximum length that has the sum of its elements = target sum. *  
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Maintain a window
 *            Scan the array
 *              If upon adding current element, the sum is less than target, update sum and advance
 *              If upon adding current element, the sum is equal to target, update the sum and the window and advance
 *              If the sum is greater than target, reset sum to 0, advance window start and result window end to window start
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Longest_Sub_Array_With_Target_Sum {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int targetSum = 15;
        System.out.println(findLongestWindowWithTargetSum(array, targetSum));
    }

    private static int findLongestWindowWithTargetSum(int[] array, int targetSum) {
        // sliding window
        int sum = 0;
        int windowStart   = 0;
        int windowEnd     = 0;
        int maxWindowSize = Integer.MIN_VALUE;
        while(windowEnd < array.length) {
            // if the target sum is not achieved, update the sum and grow the window
            if(sum + array[windowEnd] < targetSum) {
                sum += array[windowEnd];
                windowEnd++;
            }
            // if the target sum is achieved, update the sum, calculate window size and grow the window
            else if(sum + array[windowEnd] == targetSum) {
                sum += array[windowEnd];
                maxWindowSize = Math.max(maxWindowSize, windowEnd - windowStart + 1);
                windowEnd++;
            }
            // if it goes beyond the target, reset sum to 0, shrink the window
            else {
                sum = 0;
                windowStart++;
                windowEnd = windowStart;
            }
        }
        return maxWindowSize == Integer.MIN_VALUE? -1 : maxWindowSize;
    }
}
