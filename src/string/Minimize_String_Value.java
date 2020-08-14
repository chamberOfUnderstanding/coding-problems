package string;

/**
 * @author 47un
 * 
 * Given a string of lowercase alphabets and a number k
 * Find the minimum value of the string after removal of ‘k’ characters.
 * The value of a string is defined as sum of squares of count of each distinct character.
 * 
 * =========
 * METHOD 1
 * =========
 * If k > string length, then minimum value is 0 as all characters can be deleted
 * Record the frequencies of each character
 * Max heapify the frequencies
 * Do the following, k times
 *  Pick the item with largest frequency (root of the heap)
 *  Decrement it by 1
 *  Sink it
 * 
 * TIME     : O(nlogn)  // Sink operation costs logn. If k = n, this can go upto nlogn
 * SPACE    : O(n)      // Frequency array
 *
 * 
 */

public class Minimize_String_Value {

    final static int ALPHABETS = 26;
    
    public static void main(String[] args) {
        String input = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowf";
        int k = 34;
        System.out.println(findMinimumStringValue(input, k));
    }

    private static int findMinimumStringValue(String input, int k) {
        if(k > input.length())
            return 0;
        int[] frequency = new int[ALPHABETS];
        for(int i = 0; i < input.length(); i++)
            frequency[input.charAt(i) - 97]++;
        for(int i = ALPHABETS / 2; i >= 0; i--)
            sink(i, ALPHABETS - 1, frequency);
        for(int i = 0; i < k; i++) {
            frequency[0]--;
            sink(0, ALPHABETS - 1, frequency);
        }
        int minimum = 0;
        for(int i : frequency)
            minimum += (i * i);
        return minimum;
    }

    private static void sink(int parent, int size, int[] array) {
        while(2 * parent <= size) {
            int child = 2 * parent;
            if(child < size && array[child + 1] > array[child])
                child++;
            if(array[parent] >= array[child])
                return;
            swap(parent, child, array);
            parent = child;
        }
    }

    private static void swap(int i, int j, int[] array) {
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }
}
