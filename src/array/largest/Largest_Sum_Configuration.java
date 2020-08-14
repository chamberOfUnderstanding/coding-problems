package array.largest;

/**
 * @author 47un
 * 
 * Given an array you have to find the max sum of i * A[i]
 * Where i is the index of the array obtained by rotating the element of the array one by one.
 * 
 * http://www.geeksforgeeks.org/maximum-sum-iarri-among-rotations-given-array/
 * 
 * =========
 * METHOD 1
 * =========
 * Scan the array and find the sum of all elements, s,  and the initial configuration sum, cf
 * During each rotation, the following happens to the cf
 *  A[i] gets the last spot, so (n - 1) * A[i] gets added to the cf
 *  Everything else moves a spot backwards, so cf loses value = sum of all moved elements = array sum - A[i] = s - A[i]
 *  Hence cf = cf + (n - 1) * A[i] - (sum - A[i])
 *  Update maximum sum if this cf has a larger value
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Largest_Sum_Configuration {

    public static void main(String[] args) {
        int[] array = {8, 3, 1, 2};
        System.out.println(findMaximumSumConfiguration(array));
    }

    private static int findMaximumSumConfiguration(int[] array) {
        int size = array.length;
        int arraySum  = 0;
        int configurationSum = 0;
        for(int i = 0; i < size; i++) {
            arraySum += array[i];
            configurationSum += i * array[i];
        }
        int maximumSum = configurationSum;
        for(int i = 0; i < size - 1; i++) {
            configurationSum = configurationSum + (size - 1) * array[i] - (arraySum - array[i]);
            maximumSum = Math.max(maximumSum, configurationSum);
        }
        return maximumSum;
    }
}