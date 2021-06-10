package array;

/**
 * @author 47un
 * 
 * Given an array A of N integers,find any 3 elements in it such that A[i] < A[j] < A[k] and i < j < k.
 * OR 
 * Find a sorted sub sequence in the array
 * 
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=700357
 * http://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
 * 
 * METHOD 1 : If the array has fewer elements, quit
 *            Maintain low, medium and high, initialize them to the first 3 values of the array
 *            Scan the rest of the array
 *              If current item is < low, update low
 *              Else if the current < medium, then low and current now form a sorted sequence of 2,
 *               So update medium as low and high as current
 *              Else (current is > medium)
 *               medium, high and current now form the result
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Sorted_Subsequence {

    public static void main(String[] args) {
        int[] case1 = {12, 11, 10, 5, 6, 2, 30};
        int[] case2 = {1, 2, 3, 4};
        int[] case3 = {4, 3, 1, 2};
        int[] case4 = {12, 11, 10, 1, 2, 3, 9, 8, 7, 6, 5};
        printArray(findSortedSubsequence(case1));
        printArray(findSortedSubsequence(case2));
        printArray(findSortedSubsequence(case3));
        printArray(findSortedSubsequence(case4));
    }

    private static int[] findSortedSubsequence(int[] array) {
        // applies to arrays of length >= 3
        if(array.length >= 3) {
            
            // initialize the first three elements
            int low     = array[0];
            int medium  = array[1];
            int high    = array[2];
            
            // check if they satisfy the condition
            if(low < medium && medium < high)
                return new int[]{low, medium, high};
            
            // scan the rest if not
            for(int i = 3; i < array.length; i++) {
                
                // get the current element
                int current = array[i];
                
                // if it's less than low, it's the new low
                if(current <= low)
                    low = current;
                // if it's less than medium, then it's > low (As it fails the first condition) 
                // then low and current form a sorted sequence
                else if(current <= medium) {
                    medium = low;
                    high   = current;
                }
                // current > medium
                else
                    return new int[] {medium, high, current};
            }
        }
        return null;
    }
    
    private static void printArray(int[] array) {
        if(array == null)
            System.out.print("Nope nope nope..");
        else
            for(int element : array)
                System.out.print(element + " ");
        System.out.println();
    }
}
