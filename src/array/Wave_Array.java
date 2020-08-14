package array;

import java.util.Arrays;

/**
 * @author 47un
 * 
 * Given an unsorted array of integers, sort the array into a wave like array.
 * An array ‘arr[0..n-1]’ is sorted in wave form if arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] >= …..
 * 
 * Examples:
 * 
 * Input:  arr[] = {10, 5, 6, 3, 2, 20, 100, 80}
 * Output: arr[] = {10, 5, 6, 2, 20, 3, 100, 80} OR
 *                 {20, 5, 10, 2, 80, 6, 100, 3} OR
 *                 any other array that is in wave form
 * 
 * http://www.geeksforgeeks.org/sort-array-wave-form-2/
 * 
 * ==================
 * METHOD 1 : Sorting
 * ==================
 * Sort the array and swap even positioned items with its adjacent item 
 * + Result is the lexicographically smallest wave form
 *            
 * TIME     : O(nlogn)
 * SPACE    : O(1)
 * 
 * ==========================
 * METHOD 2 : Without Sorting
 * ==========================
 * Scan even indices of the array (0,2,4..)
 * If the item is > the previous one, swap it
 * If the item is > the next one, swap it
 * - Result is NOT the lexicographically smallest
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Wave_Array {

    public static void main(String[] args) {
        int[] array = {10, 90, 49, 2, 1, 5, 23};
        waveifyI(array);
        waveifyII(array);
        printArray(array);
    }
    
    public static void waveifyI(int[] array){
        Arrays.sort(array);
        for(int i = 0; i < array.length - 1; i += 2)
            swap(array, i, i + 1);
     }
     
     public static void waveifyII(int[] array){
         for(int i = 1; i < array.length - 1; i += 2){
            if(array[i] > array[i - 1])
                swap(array, i, i - 1);
            if(array[i] > array[i + 1])
                swap(array, i, i + 1);
         }
     }
     
     public static void printArray(int[] array) {
         for(int i : array)
             System.out.print(i + " ");
     }
     
     public static void swap(int[] array, int i, int j){
         array[i] ^= array[j];
         array[j] ^= array[i];
         array[i] ^= array[j];
     }
}
