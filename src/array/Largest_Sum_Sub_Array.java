package array;

/**
 * @author 47un
 *
 * Find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum. 
 * e.g. {-2, -3, 4, -1, -2, 1, 5, -3}
 *      Max contiguous subarray sum = 4 + -1 + -2 + 1 + 5 => 7
 *      
 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Kadane's algorithm
 *              Maintain sum and max variables
 *              Add first item to the sum
 *                  If the sum is < 0, set the sum to 0    // We are more concerned about the +ve nos.
 *                  Else if the sum is > max, update max to sum
 *              ! This method returns 0 if all the items in the array are -ve
 *                To fix this, keep track of the largest -ve value and maintain a flag that's to be hoisted
 *                if a positive value is seen.
 *                After scanning the array, if the flag is not hoisted, return the largest -ve value
 *                
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 * METHOD 2 : Maintain sum and max variables, init them to the first item's value
 *            Scan the array
 *            Set sum as the largest of array item and array item + sum // If the item is > sum, prefer the item
 *            Set max as the largest of max and sum
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Largest_Sum_Sub_Array {

    public static void main(String...strings){
        int[] array = {-1,-2,24,-44,90,65,22,-144,147};
        //		int[] array = {-122231,-213,-4444,-144};
        System.out.println("Method 1 : " + algorithmKadane(array) +
                "\nMethod 2 : " + methodII(array));
    }

    private static int algorithmKadane(int[] array) {
        int sum = 0;
        int max = 0;
        int maxNegative = Integer.MIN_VALUE;
        int start = 0;
        int end   = 0;
        boolean anyPositives = false;
        for(int i = 0; i < array.length; i++){
            if(!anyPositives && array[i] < 0)
                maxNegative = Math.max(maxNegative, array[i]);
            else
                anyPositives = true;
            sum += array[i];
            if(sum < 0) {
                sum = 0;
                start = i + 1;
            }
            else if(sum > max) {			    
                max = sum;
                end = i;
            }
        }			
        System.out.println(start + " " + end);
        return anyPositives? max : maxNegative;
    }

    private static int methodII(int[] array) {
        int sum = array[0];
        int max = array[0];
        int startingIndex = 0;
        int endingIndex = 0; 
        for(int i = 1; i < array.length; i++){	
            sum = Math.max(sum + array[i], array[i]);
            max = Math.max(max, sum);
            if(sum == array[i])
                startingIndex = i;
            endingIndex = i;
        }
        System.out.println("Starts at "+ startingIndex + "\nEnds at " + endingIndex);
        return max;
    }
}
