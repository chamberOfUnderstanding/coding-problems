package array;

/**
 * @author 47un
 *
 * Find the maximum sum among all strictly increasing sub arrays
 * 
 * http://www.geeksforgeeks.org/find-maximum-sum-strictly-increasing-subarray/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Keep track of the current sum and the maximum sum
 *            If the current element is < previous element, update maximum sum (if max sum > sum) and re init sum
 *            Else update sum
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Strictly_Increasing_Sequence {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 3, 3, 4, 4, -3, 4, 55, 3, -3434, -232, 10000};
        System.out.println(getMaxSum(array));
    }

    private static int getMaxSum(int[] array) {
        int sum = array[0];
        int maximumSum = Integer.MIN_VALUE;
        for(int i = 1; i < array.length; i++)
            if(array[i] > array[i - 1])
                sum += array[i];
            else {
                maximumSum = Math.max(maximumSum, sum);
                sum = array[i];
            }           
        return Math.max(maximumSum, sum);
    }
}