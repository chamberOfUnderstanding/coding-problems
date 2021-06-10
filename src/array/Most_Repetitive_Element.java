package array;

/**
 * @author 47un
 * 
 * Given an array of size n, the array contains numbers in range from 0 to k-1 where k is a positive integer and k <= n.
 * Find the most repetitive number in this array.
 * For example, let k be 10 the given array be arr[] = {1, 2, 2, 2, 0, 2, 0, 2, 3, 8, 0, 9, 2, 3}, the maximum repeating number would be 2 if there are two or more maximum repeating numbers print the element having least value.
 * Expected time complexity is O(n) and extra space allowed is O(1).
 * 
 * http://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/
 * 
 * =========
 * METHOD 1
 * =========
 * Array is of size 'size' and the range of elements that can be seen in the array is from 0 to 'range' - 1
 * In the below description, k is the range
 * 
 * Similar to Repetitions_Of_Each_Element
 * Scan the array and update the element at index array[i] % k by adding k to it
 *  Since we use array[i] % k as index and add value k at the index array[i]%k, the index which is equal to maximum repeating element will have the maximum value in the end.
 *  Note that k is added maximum number of times at the index equal to maximum repeating element and all array elements are smaller than k.
 * To get the frequency/repetitions of each element, divide the items by k
 * Track the most repetitive element
 * Scan the array
 *  If the frequency of this element is > frequency of the most repetitive element,
 *   Update the most repetitive element
 * Scan the array again. This time check if there are any other elements that repeat the same number of times as the most repetitive element.
 *  If yes update the most repetitive element with the least of the values 
 * Scan the array and revert it to the original state by %size each element
 * 
 * !! NOTE !!
 * After the step where the array is transformed (array[array[i] % range] += range step),
 *  Each index, i, of the array within the range represents the value and array[i] / range gives the frequency
 *  So mostRepetitive = 5 means : the value 5 repeats the most and NOT : THE VALUE AT INDEX 5 REPEATS THE MOST !! 
 *  This is why Math.min(mostRepetitive, i) is done AND NOT Math.min(array[mostRepetitive], array[i]) !!!
 *  
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Most_Repetitive_Element {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        int[] array = {2, 5, 2, 5, 5, 5, 2, 2};
        int size    = 8;
        int range   = 6;
        findTheElementThatOccursTheMost(array, range);
    }

    private static void findTheElementThatOccursTheMost(int[] array, int k) {
        
        // the array elements will always be contained within the range of 0 to k - 1
        // since k < n, each index of the array can be used to calculate the repetition of the element at that index
        // array[i] % k will range from 0 to k - 1, same as the array indices
        // array = {1, 1, 1, 2}
        // k = 2
        // i = 0, array[0] % k => 1 % 2 => 1
        // i = 1, array[1] % k => 1 % 2 => 1
        // i = 2, array[2] % k => 1 % 2 => 1
        // i = 3, array[3] % k => 2 % 2 => 0
        // k will be added to index 1 3 times (giving 7)
        // k will be added to index 0 1 time (giving 2)
        // largest of the above gives the most repetitive element
        for(int i = 0; i < array.length; i++)
            array[array[i] % k] += k;
               
        // after the above operation, elements from index 0 to k - 1 will have been altered to contain the "frequencies" of those numbers
        // dividing the numbers by k gives the frequency
        // if it's greater than the most repetitive one, mark it as the most repetitive one
        int mostRepetitive = 0;
        for(int i = 1; i < k; i++)
            if(array[i] / k > array[mostRepetitive] / k)
                mostRepetitive = i;
        
        // second scan to check if there are any other elements with the same frequency as we require the least of the elements in this case
        for(int i = 0; i < k; i++)
            if(i != mostRepetitive && array[i] / k == array[mostRepetitive] / k)
                mostRepetitive = Math.min(mostRepetitive, i);
        
        // revert the changes made to the array by % with k
        // 7 % 2 => 1
        // 2 % 2 => 0
        for(int i = 0; i < array.length; i++)
            array[i] %= k;
        
        System.out.println(mostRepetitive);
    }
}
