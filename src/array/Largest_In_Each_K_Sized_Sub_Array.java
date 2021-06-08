package array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 47un
 *
 * Find max in each k sized window of an array 
 * e.g : -85 189 2 3 47 and window size : 3 => 189 189 47
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Initialize max to start of the window
 *            Scan the window and update max if any greater values are found
 *            Slide the window
 *
 * TIME     : O(nk)
 * SPACE    : O(1)
 * 
 * METHOD 2 : Use a Dequeue to store the array indices
 *            For first k items,             
 *             Dequeue all elements from the rear (if any), that are < current element (the ones that cant be max)
 *             Enqueue current element at the rear
 *            Scan remaining elements
 *             Print the front of the queue (max of that window)
 *             Remove all items from front that do not belong to the new window
 *              If k is 3, first window is 0,1,2 whose max has been calculated in the previous steps. The next window STARTS at 1 (1,2,3).
 *              So all elements in the deque that are at indices < 1 should be removed from the front. Hence for index i, the window starts at i - k (This is also the FIRST window that the index
 *              will be a part of.
 *             Dequeue all elements from the rear, that are < current element
 *             Enqueue current element at the rear
 *            Print the front of the queue
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 */

public class Largest_In_Each_K_Sized_Sub_Array {
  
    public static void main(String...strings){
        int size = 8;
        int[] array = {-85, 189, 2, 3, 47, 993, -22, 532};			
        int windowSize = 3;
        getMaxInEachWindowDequeue(array, size, windowSize);
    }

    @SuppressWarnings("unused")
    private void getMaxInEachWindow(int[] array, int size, int windowSize) {
        if(windowSize > size){
            System.out.println("Window is larger than array!");
            return;
        }
        int windowStart = 0;
        int windowEnd = windowSize;
        int max = array[windowStart];
        while(windowEnd <= size){
            for(int i = windowStart + 1; i < windowEnd; i++)
                max = Math.max(array[i], max);
            System.out.print(": "+max+" ");
            windowStart++;
            windowEnd++;
            if(windowStart != size)
                max = array[windowStart];
        }
    }
    
    private static void getMaxInEachWindowDequeue(int[] array, int size, int windowSize) {
        Deque<Integer> dequeue = new LinkedList<>();
        int i = 0;
        for( ; i < windowSize; i++) {
            while(!dequeue.isEmpty() && array[i] >= array[dequeue.peekLast()])
                dequeue.removeLast();
            dequeue.offerLast(i);
        }            
        for( ; i < size; i++) {
          
            System.out.println(array[dequeue.peekFirst()]);
            while(!dequeue.isEmpty() && dequeue.peekFirst() <= i - windowSize)
                dequeue.removeFirst();
          
            while(!dequeue.isEmpty() && array[i] >= array[dequeue.peekLast()])
                dequeue.removeLast();
            dequeue.offerLast(i);
        }
        System.out.println(array[dequeue.peekFirst()]);
    }
}
