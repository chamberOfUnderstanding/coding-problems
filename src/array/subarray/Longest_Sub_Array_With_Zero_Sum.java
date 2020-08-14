package array.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 * 
 * Given an array of integers, find length of the largest sub array with sum = 0.
 * 
 * http://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
 * https://discuss.leetcode.com/topic/25465/longest-continous-zero-sum-subarray/5
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
        int[] array = {15, -2, 2, -8, 1, 7, 10, 23};
        System.out.println(findLongestSubArrayWithZeroSum(array));
    }

    private static int findLongestSubArrayWithZeroSum(int array[]){
        int sum = 0;
        int longestSubArray = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
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