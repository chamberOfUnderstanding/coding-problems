package array.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 * 
 * Given an array of 0's and 1's find the size of the longest sub array with equal number of 0s and 1s.
 * 
 * http://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
 * 
 * ===================
 * METHOD 1 : HashMap
 * ===================
 * A little tweak to the input will make this problem is similar to Longest_Sub_Array_With_Zero_Sum.
 * If the array item is a 0, change it to -1. Now only a equal number of 1s and -1s can give a sum of 0.
 * This can be found out using the logic of Longest_Sub_Array_With_Zero_Sum
 * 
 * Scan the array
 *  Add the current item to sum. If the item is 0, add -1 instead
 *  If sum = 0 then a sub array with 0 sum has been seen
 *   Update longest sub array with current index + 1
 *  Else check if this sum has already been seen
 *   This happens iff the elements between the index where the sum was first seen and this index add up to a 0
 *   If yes, update the longest sub array with the current index - index where the sum was encountered for the first time
 *   This is where the map comes in handy. It stores all the sums that get generated along with the indices
 *  Else, this sum is seen for the first time, add the sum and the current index to the map
 *  
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Longest_Sub_Array_With_Equal_Zeroes_And_Ones {

    public static void main(String[] args) {
        int array[] = {0, 1, 1, 1, 1, 0, 0, 1, 1};
        System.out.println(findLongestSubArrayWithEqualZeroesAndOnes(array));
    }

    private static int findLongestSubArrayWithEqualZeroesAndOnes(int[] array) {
        int sum = 0;
        int longestSubArray = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i++) {
            sum += (array[i] == 0? -1 : 1);
            if(sum == 0)
                longestSubArray = Math.max(longestSubArray, i + 1);
            else if(map.containsKey(sum))
                longestSubArray = Math.max(longestSubArray, i - map.get(sum));
            else
                map.put(sum, i);
        }
        return longestSubArray;
    }
}
