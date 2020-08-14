package array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author 47un
 *
 * Find the k th largest element in an array
 * 
 * http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
 * 
 * METHOD 1 : Use a min heap of size k
 *            Add k items to the min heap (from index k-1 down to 0)
 *            Sink them as well
 *            Now scan the items in the array from index k to n and for each item,
 *             If the item is > root of min heap (which is the kth max)
 *              Update the root with this item and sink the root
 *            min heap[0] gives the k th largest            
 *
 * TIME     : O(nlogk) // Worst case when the k th max is the last item,
 *                        So you have to scan the entire array and sink each item
 * SPACE    : O(k)     // Min heap of size k
 *
 * METHOD 2 : Variant of bubble sort that runs for the first k items in the array
 * 
 * TIME     : O(nk)
 * SPACE    : O(1)
 * 
 * METHOD 3 : Use a priority queue (Which itself is a min heap)
 *            In a priority queue, items are dequeued in a sorted way
 *            So enqueue k items onto the priority queue
 *            Scan from k to n
 *             If an item is greater than the smallest item on the priority queue (priorityqueue.peek())
 *              Poll this item and add the new item
 *            Output priority queue . poll()
 *            
 * TIME     : O(nlogk)
 * SPACE    : O(k)
 * 
 * METHOD 4 : Print the k largest items in the array
 *            Same as finding the kth largest using a minHeap
 *            After find it, the minHeap needs to be sorted (costing another klogk)
 *             
 * TIME     : O(k +(n-k)logk + klogk)
 * SPACE    : O(k)            
 * 
 */

@SuppressWarnings("unused")
public class Kth_Largest_In_An_Array {

    public static void main(String...strings){		
        int[] array = {-44, 2, 50, 4, 0, 1223, -4232, 869, 99};
        int k = 3;
        findKthLargestUsingMinHeap(array, k);
        printKLargestElements(array, k);
    }

    private static void findKthLargestUsingMinHeap(int[] array, int k) {
        if(k < 0 || k > array.length - 1) {
            System.out.println("Invalid value for k");
            System.exit(0);
        }
        int[] minHeap = new int[k];
        for(int i = 0; i < k; i++)
            minHeap[i] = array[i];
        for(int i = k / 2; i >= 0; i--)
            sink(minHeap, i, k - 1);
        int i = k;
        while(i < array.length) {
            if(array[i] > minHeap[0]) {
                minHeap[0] = array[i];
                sink(minHeap, 0, k - 1);
            }
            i++;
        }
        System.out.println(minHeap[0]);
    }

    private static void sink(int[] minHeap, int parent, int heapSize) {
        while(2 * parent <= heapSize) {
            int child = 2 * parent;
            if(child < heapSize && minHeap[child + 1] < minHeap[child])
                child++;
            if(minHeap[parent] <= minHeap[child])
                return;
            swap(minHeap, parent, child);
            parent = child;
        }        
    }

    private static void printKLargestElements(int[] array, int k) {
        int[] minHeap = new int[k];
        for(int i = 0; i < k; i++)
            minHeap[i] = array[i];
        for(int i = k / 2; i >= 0; i--)
            sink(minHeap, i, k - 1);
        for(int i = k; i < array.length; i++){
            if(array[i] > minHeap[0]){
                minHeap[0] = array[i];
                sink(minHeap, 0, k - 1);
            }
        }
        Arrays.sort(minHeap);
        for(int i = k - 1; i >= 0; i--)
            System.out.print(minHeap[i] + " ");
        System.out.println();
    }

    private static void findKthLargestUsingPriorityQueue(int[] array, int k) {
        if(k < 0 || k > array.length - 1) {
            System.out.println("Invalid value for k");
            System.exit(0);
        }        
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();        
        for(int i = 0; i < k ; i++)
            priorityQueue.add(array[i]);
        int i = k;
        while(i < array.length) {
            if(array[i] > priorityQueue.peek()) {
                priorityQueue.poll();                
                priorityQueue.add(array[i]);
            }
            i++;
        }
        System.out.println(priorityQueue.poll());
    }

    private int findKthLargestUsingSort(int[] array, int k) {       
        boolean swaps = true;
        for(int i = 0; i < k && swaps; i++){
            swaps = false;
            for(int j = 0; j < array.length - i - 1; j++)
                if(array[j] > array[j+1])
                    swaps = swap(array, j, j+1);
        }
        return array[array.length - k];
    }

    private static boolean swap(int[] array, int index1, int index2) {
        int temp = array[index2];
        array[index2] = array[index1];
        array[index1] = temp;
        return true;
    }
}
