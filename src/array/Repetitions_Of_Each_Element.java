package array;

/**
 * @author 47un
 *
 * Given an array of size n that can hold elements from 1 to n, some of which may repeat.
 * Find the frequency of each element without using any auxiliary space
 * 
 * http://www.geeksforgeeks.org/count-frequencies-elements-array-o1-extra-space-time/
 * 
 * ========
 * METHOD 1
 * ========
 * Decrement each element by 1
 *  This is to avoid going out of bounds if the number 'n' is present (The array can hold only up to n-1)
 * Size (n) gets added to each element and it gets stored at index = element
 * e.g.
 *  Element 2 gets the size added to it becoming 2 + n
 *  The new value is stored at index 2
 *  To make sure all the 2's end up in the same index, and to avoid going out of bounds, % size is required 
 * Once the above thing is done T_T
 *  Scan the array and frequency of each item, i + 1, is array[i] / size
 *   As we added size multiple times, to get the number of times we added it (frequency), divide it by size 
 *             
 * TIME    : O(n)
 * SPACE   : O(1)
 * 
 */

public class Repetitions_Of_Each_Element {
	
	public static void main(String[] args) {
		int[] array = {2, 2, 2, 2, 2};
		findFrequency(array);
	}

	private static void findFrequency(int[] array) {
		int size = array.length;
		for(int i = 0; i < size; i++)
			array[i]--;
		for(int i = 0; i < size; i++)
			array[array[i] % size] += size;
		for(int i = 0; i < size; i++)
			System.out.println(i + 1 + " : " + array[i]/size);
	}
}
