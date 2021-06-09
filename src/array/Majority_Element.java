package array;

/**
 * @author 47un
 * 
 * A majority element in an array A[] of size n is an element that appears more than n/2 times.
 * There is at most one such element.
 * For a given array, find the majority element
 * 
 * http://www.geeksforgeeks.org/majority-element/
 * 
 * ===================================
 * METHOD 1 : Moore's Voting Algorithm
 * ===================================
 * Find out a candidate using Moore's voting algorithm
 *   This algorithm is akin to canceling off equal stuff
 *   Set the candidate index as 0 and count as 1   
 *   Scan the array
 *    If the index i holds a value same as that of the candidate index, increase count
 *    If not decrease count
 *    At any point if the count falls to 0, then consider the current index, i as the new candidate and reset the count
 * At the end you are left with a possible majority element
 * Scan the array again and check if this element occurs more than n/2 times
 *  The second pass is required coz : 
 *   For input {1,1,1,3,3,3} there is no majority element, but this logic would report 1.
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * ==================
 * METHOD 2 : Sorting
 * ==================
 * Sort the array
 * For each item at index i,
 *  Check if i + n/2 + 1 is within bounds,
 *   If yes, check if array[i] = array[i + n/2 + 1], then return array[i]
 *   
 * TIME     : O(nlogn)
 * SPACE    : O(1)
 * 
 * 
 */

public class Majority_Element {

    public static void main(String[] args) {
        int[] array = {7, 4, 2, 7, 3, 7, 7, 7, 7, 7};
        System.out.println(findMajorityElement(array));
    }

    // Moore's Voting Algorithm
    private static int findMajorityElement(int[] array) {
        // count is 1 and candidateIndex is 0 (first possible candidate)
        int count = 1;
        int candidateIndex = 0;
        for(int i = 1; i < array.length; i++) {
            // if the current element is the same as candidate, increment the count, else decrement
            count += (array[i] == array[candidateIndex])? 1 : -1;
            // if the count is 0, then we need a new candidate
            // reset the candidateIndex and the count
            if(count == 0) {
                candidateIndex = i;
                count = 1;
            }
        }
        // second pass is required to ensure that the element occurs more than n/2 times
        // this second pass is required coz - For input {1,1,1,3,3,3} there is no majority element, but this logic would report 1.
        count = 0;
        for(int i : array.length) {
            if (array[i] == array[candidateIndex])
                count++;
            if (count >= array.length / 2)
                return array[candidateIndex];
        }
        return -1;
    }
}
