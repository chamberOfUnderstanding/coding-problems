package array;

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
 * Lets take example {1, 2, 3}. Current value is 1*0 + 2*1 + 3*2 = 8. Shifting it by one will make it {2, 3, 1} and next value
 * will be 8 - (6 - 1) + 1*2 = 5 which is same as 2*0 + 3*1 + 1*2
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
        
        // find array sum and configuration sum
        int maximumSum = configurationSum;
        int arraySum  = 0;
        int configurationSum = 0;
        for(int i = 0; i < size; i++) {
            arraySum += array[i];
            configurationSum += i * array[i];
        }
       
        // calculate the next configuration sum from the current one
        // for index i,
        //  the configuration sum increases by (n - 1) * a[i]  (as a[i] becomes the last item)
        //  the configuration sum decreases in value by the sum of all moved elements, which is array sum - a[i]
        // update maximum accordingly
        for(int i = 0; i < size - 1; i++) {
            configurationSum = configurationSum + (size - 1) * array[i] - (arraySum - array[i]);
            maximumSum = Math.max(maximumSum, configurationSum);
        }
        return maximumSum;
    }
}
