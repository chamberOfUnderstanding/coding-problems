package array;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Find the k-th largest element in an incoming integer stream
 * 
 * OR
 * 
 * There is file with million numbers, find out the kth largest element.
 * 
 * http://www.geeksforgeeks.org/kth-largest-element-in-a-stream/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Use a min heap
 *            Maintain a min heap of size k
 *            Keep adding items to your heap and follow it with a heapify
 *            After k items have been added to heap, for every new number, compare this number with the minHeap's root
 *              If the number is < minHeap's root, ignore it
 *              If it is greater, set it as the root and heapify (sink it)
 * TIME     : O(logk) for each sink operation. So if the number is greater than root, O(logk) else O(1)
 * SPACE    : O(k) as there will be atmost k items on the min heap
 *
 * 
 */

public class Kth_Largest_In_An_Integer_Stream {

    public static void main(String[] args) {
        int k = 3;
        int[] minHeap = new int[k];
        int count = k - 1;        
        System.out.println("Enter numbers"
                + "\nEnter quit to exit");
        try(Scanner scanner = new Scanner(System.in)){
            while(true) {
                String data = scanner.next();
                if(data.equalsIgnoreCase("quit")) {
                    System.out.println("Quitting...");
                    System.exit(0);
                }
                int number = Integer.parseInt(data);
                if(count >= 0) {
                    minHeap[count] = number;
                    sink(minHeap, count, k - 1);
                    count--;
                }
                else if(number > minHeap[0]){
                    minHeap[0] = number;
                    sink(minHeap, 0, k - 1);
                }
                System.out.println(count >= 0? "-" : minHeap[0]);
            }
        }
    }

    private static void sink(int[] minHeap, int parent, int size) {
        while(2 * parent <= size) {
            int child = 2 * parent;
            if(child < size && minHeap[child + 1] < minHeap[child])
                child++;
            if(minHeap[parent] <= minHeap[child])
                break;
            swap(minHeap, parent, child);
            parent = child;
        }        
    }

    private static void swap(int[] minHeap, int index1, int index2) {
        int temp = minHeap[index1];
        minHeap[index1] = minHeap[index2];
        minHeap[index2] = temp;
    }
}
