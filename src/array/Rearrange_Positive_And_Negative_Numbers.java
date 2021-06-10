package array;

/**
 * @author 47un
 *
 * Given an array of positive and negative numbers.
 * Arrange them such that all negative integers appear before all the positive integers in the array.
 * Not allowed to use any additional data structure like hash table, arrays, etc.
 * The order of appearance should be maintained.
 * 
 * =========
 * METHOD 1
 * ===========
 * Attack from both ends
 * If there's a -ve on low move on
 * If there's a +ve on low, swap it with high, update high
 * ! Does'nt preserve the order
 *             
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * ======================================================
 * METHOD 2 : Variant of insertion sort (Preserves order)
 * ======================================================
 * For each item
 *   If it is positive, continue
 *   If it is negative, scan backwards till the first positive item is seen, all the while copying items to the next slot
 *   The current item goes to the slot of the first positive item
 *             
 * TIME     : O(n^2) // Worst
 * SPACE    : O(1)
 * 
 */

public class Rearrange_Positive_And_Negative_Numbers {

    public static void main(String...strings){
        int[] numbers = {40, -2, 3, 55, 22, -4, -442, -44, -4213, 59, 6};
        rearrangeII(numbers);
        printArray(numbers);
    }

    @SuppressWarnings("unused")
    private static void rearrangeI(int[] numbers){
        int low  = 0;
        int high = numbers.length - 1;
        while(low < high) {
            if(numbers[low] >= 0) {
                if(numbers[high] < 0)
                    swap(numbers, low, high);
                high--;
            }
           low++;
        }
    }

    private static void rearrangeII(int[] numbers){
        for(int i = 1; i < numbers.length; i++){			
            int key = numbers[i];
            if(key >= 0)
                continue;
            int j = i - 1;
            while(j >= 0 && numbers[j] >= 0){
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = key;
        }
    }

    private static void printArray(int[] numbers) {
        for(int number : numbers)
            System.out.print(number + " ");
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
