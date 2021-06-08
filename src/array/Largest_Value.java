package array;

/**
 * @author 47un
 * 
 * In a given array A[] find the maximum value of (A[i] - i) - (A[j] - j) where i is not equal to j.
 * i and j vary from 0 to n-1 and N is size of input array A[].  Value of N is always greater than 1.
 * 
 * http://www.geeksforgeeks.org/maximize-value-of-arri-i-arrj-j-in-an-array/
 * 
 * =========
 * METHOD 1
 * =========
 * Scan the array
 *  If array[i] - i is < smallest, update smallest
 *  If array[i] - i is > largest, update largest
 * Return largest - smallest
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Largest_Value {

    public static void main(String[] args) {
        int[] array = {9, 15, 4, 12, 13};
        System.out.println(findMaximumValue(array));
    }

    private static int findMaximumValue(int[] array) {
        int smallest = Integer.MAX_VALUE;
        int largest  = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            smallest = Math.min(smallest, array[i] - i);
            largest  = Math.max(largest,  array[i] - i);
        }
        return largest - smallest;
    }
}
