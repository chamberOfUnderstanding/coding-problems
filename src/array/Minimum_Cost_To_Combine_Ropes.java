package array;

/**
 * @author 47un
 * 
 * There are n ropes of different lengths, the task is to connect these ropes into one long rope.
 * The cost to connect two ropes is equal to sum of their lengths. Connect the ropes with minimum cost.
 * 
 * http://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
 * 
 * ===================================
 * METHOD 1 : Minheap (Priority Queue)
 * ===================================
 * Sink half the elements. Least one floats up.
 * min1 = Get the least item from the heap (0 index)
 * Set it as the last item
 * Sink the 0th
 * Reduce the heap size by 1
 * min2 = Get another item from the heap
 * Update the cost as min1 + min2
 * Set the first item as sum of the two minimums (min1 + min2, AND NOT COST)
 * Sink the first item
 * Since this operation counts as insertion, no need to reduce the size by 1
 * 
 * For example, if we are given 4 ropes of lengths 4, 3, 2, and 6. We can connect the ropes in the following ways. 
 * 1) First, connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6, and 5. 
 * 2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9. 
 * 3) Finally connect the two ropes and all ropes have connected.
 * Total cost for connecting all ropes is 5 + 9 + 15 = 29. 
 *
 * TIME     : O(nlogn)
 * SPACE    : O(n)
 *
 * 
 */

public class Minimum_Cost_To_Combine_Ropes {

    public static void main(String[] args) {
        int[] rope = {4, 3, 2, 6};
        combineRopes(rope);
    }

    private static void combineRopes(int[] ropes) {
        int ropesLeft = ropes.length - 1;
        int cost  = 0;
        
        // sink/heapify half the ropes
        for(int i = ropesLeft/2; i >= 0; i--)
            sink(i, ropes, ropesLeft);
        
        // as long as there are ropes left
        while(ropesLeft > 0) {
        	
        	// first minimum will be at the top of the minheap
            int firstMinimum = ropes[0];
            // replace top with the last item
            ropes[0] = ropes[ropesLeft];
            // reduce rope count and sink
            ropesLeft--;
            sink(0, ropes, ropesLeft);
            // second minimum will have floated to the top by now
            int secondMinimum = ropes[0];
            // add combined rope value to the start of minheap
            ropes[0] = firstMinimum + secondMinimum;
            // calculate cost
            cost += ropes[0];
            // sink
            sink(0, ropes, ropesLeft);
        }
        System.out.println(cost);
    }

    private static void sink(int parent, int[] ropes, int ropesLeft) {
        while(2 * parent <= ropesLeft) {
            int child = 2 * parent;
            if(child < ropesLeft && ropes[child + 1] < ropes[child])
                child++;
            if(ropes[parent] <= ropes[child])
                return;
            swap(ropes, parent, child);
            parent = child;
        }
    }

    private static void swap(int[] array, int i, int j) {
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }
}
