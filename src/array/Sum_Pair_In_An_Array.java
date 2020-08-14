package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 47un
 *
 * Given an array and a number x, find out a pair in the array that makes the sum x
 * 
 * =========
 * METHOD 1 : Sorting
 * ===========
 * METHOD 1 : Sort the array
 *            Scan from both ends
 *            Sum the values
 *            If the value is = target, return true
 *            If the value is < target, we need a larger value, so advance the start
 *            If the value is > target, we need a smaller value, so advance the end 
 *
 * TIME     : O(nlogn)   // Sorting costs O(nlogn) and find the pair requires another scan of O(n)
 * SPACE    : O(1)
 * 
 * METHOD 2 : Sort the array
 *            For each item, calculate the pair value and do binary search for it
 *
 * TIME     : O(nlogn)   // Better than first as sorting and finding the pair costs O(nlogn)
 * SPACE    : O(1)
 * 
 * METHOD 3 : For each item in the array
 *             Calculate the pair's value
 *             Check if it's present in the hash set, yes? found it!
 *             No? Add this item and it's pair value to a hash set
 *             
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Sum_Pair_In_An_Array {

    public static void main(String...strings){
        int[] array   = {-22, 4, 2994, 590, 33, 5, 332, 1, 85};			
        int targetSum = -21;
        findSumPairI(array, targetSum);
        findSumPairIII(array, targetSum);
    }

    private static void findSumPairI(int[] array, int targetSum) {
        int size = array.length;
        Arrays.sort(array);
        int low    = 0;
        int high   = size - 1;
        boolean found  = false;
        while(low <= high && !found)
            if(targetSum > array[low] + array[high])
                low++;
            else if(targetSum < array[low] + array[high])
                high--;
            else
                found = true;
        if(found)
            System.out.print("Pair identified at : " + low + " " + high + " with values: " + array[low] + " " + array[high]);
        else
            System.out.print("\nSuch a pair does not exist in the array!");
    }

    private static void findSumPairIII(int[] array, int targetSum) {
        Set<Integer> hashSet = new HashSet<>();
        for(int item : array)
            if(!hashSet.isEmpty() && hashSet.contains(targetSum - item)) {
                System.out.println("\n" + item + " " + (targetSum - item));
                return;
            }
            else {
                hashSet.add(item);
                hashSet.add(targetSum - item);
            }	            
        System.out.println("Pair not found!");	        
    }
}
