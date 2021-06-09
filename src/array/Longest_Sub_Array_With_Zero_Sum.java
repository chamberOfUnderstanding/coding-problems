package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 * 
 * Given an array of integers, find length of the largest sub array with sum = 0.
 * 
 * http://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
 * 
 * ===================
 * METHOD 1 : HashMap
 * ===================
 * Scan the array
 *  Add the current item to sum
 *  If sum = 0 then a sub array with 0 sum has been seen
 *   Update longest sub array with current index + 1
 *  Else check if this sum has already been seen
 *   This happens iff the elements between the index where the sum was first seen and this index, add up to a 0
 *   If yes, update the longest sub array with the current index - index where the sum was encountered for the first time
 *   This is where the map comes in handy. It stores all the sums that get generated along with the indices
 *  Else, this sum is seen for the first time, add the sum and the current index to the map
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Longest_Sub_Array_With_Zero_Sum {

    public static void main(String arg[]) {
//      int[] array = {15, -2, 2, -8, 1, 7, 10, 23};
        int[] array = {5, -5, 2, -1, -1};
//		int[] array = {2, 8, -3, -5, 2, -4, 6, 1, 2, 1, -3, 4};        
        System.out.println(findLongestSubArrayWithZeroSum(array));
    }

    private static int findLongestSubArrayWithZeroSum(int array[]){
        int sum = 0;
        int longestSubArray = -1;
        // map stores the sums encountered along with the indices
        Map<Integer, Integer> map = new HashMap<>();
        // initialize it with 0 sum at index -1
        // if another 0 is encountered, this -1 will help in getting the full length of all items to that index
        // e.g. 0 is encountered at index 10, therefore all items till index 10 add up to 0
        // mapping for 0 is -1, so length is 10 - -1 => 11
        map.put(0, -1);
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
            // If a sum repeats, that means elements between the first encounter and the current encounter have a sum of 0
            // update longest sub array
            if(map.containsKey(sum))
                longestSubArray = Math.max(longestSubArray, i - map.get(sum));
            else
                map.put(sum, i);
        }
        return longestSubArray;
    }
}
