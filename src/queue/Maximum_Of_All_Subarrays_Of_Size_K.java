package queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 47un
 * 
 * Given an array and an integer k, find the maximum for each and every contiguous sub array of size k.
 * 
 * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
 * 
 * INPUT : 12 1 78 90 57 89 56 , k = 3
 * OUPUT : 78 90 90 90 89
 * 
 * =========
 * METHOD 1
 * =========
 * Prepare a deque (pronounced as "deck")
 * Process the first window and fill the deque
 *  As long as the last item in the deque is <= current one remove items from the end
 *   Here all items less than the current one are removed from the queue
 *  Add the current item to the end
 * Scan the rest of the items
 *  This point is reached every time a window is fully scanned, so print the first item on the deque
 *  Now remove the elements that do not belong in this window
 *   i.e If the index of the item on the front of the deque is <= i - k, then remove the item in front
 *   e.g For k = 3, the windows are [0,1,2] [1,2,3] [2,3,4] [3,4,5] etc.
 *       If the i is now, say 7, then 0,1,2,3,4 are not needed in the deque as they are all <= 4 (Which is 7 - 3)
 *       5, 6 are not removed as 7 is a part of the window that starts with 5 and 6
 *  Process the next window
 * Print the last window's maximum value
 * 
 * TIME     : O(n)
 * SPACE    : O(k)
 *
 * 
 */

public class Maximum_Of_All_Subarrays_Of_Size_K {

    public static void main(String...x){
        int[] array = {12, 1, 78, 90, 57, 89, 56};
        int k = 3;			
        findMaximumOfAllSubArraysOfSizeK(array, k);
    }

    private static void findMaximumOfAllSubArraysOfSizeK(int[] array, int k) {
        int size = array.length;
        Deque<Integer> deque = new LinkedList<>();
        int i = 0;
        while(i < k)
            processWindow(array, deque, i++);
        while(i < size){
            System.out.print(array[deque.peekFirst()] + " ");
            while(!deque.isEmpty() && deque.peekFirst() <= i - k)
                deque.removeFirst();
            processWindow(array, deque, i++);
        }
        System.out.print(array[deque.peekFirst()] + " ");
    }

    private static void processWindow(int[] array, Deque<Integer> deque, int index) {
        while(!deque.isEmpty() && array[deque.peekLast()] <= array[index])
            deque.removeLast();
        deque.addLast(index);
    }
}
