package sorting_searching;

/**
 * @author 47un
 * 
 * Best case    : O(n2)
 * Worst case   : O(n2)
 * Average case : O(n2)
 * 
 * INVARIANT - a[min] is the least element among a[i...n]
 * INVARIANT - a[1...i] is sorted
 * 
 * Iterate through the array (i loop)
 * Assume i-th element is the least (min)
 * Iterate through the rest of the array (j loop and it starts at i)
 * If any of the remaining values is less that the assumed least value, update min
 * After iterating through the rest of the array (j loop ends),swap the value at min with the value at i
 *  
 */

public class Selection_Sort {

	public static void main(String[] args) {
		int[] unsortedArray = {4,1,3,-34,55,1,3,4};
		selectionSort(unsortedArray);
		for(int i : unsortedArray)
			System.out.print(i + " ");
	}

	public static void selectionSort(int[] array){
		for(int i = 0; i < array.length; i++){
			int min = i;
			for(int j = i; j < array.length; j++)
				min = array[j] < array[min]? j : min;
			swap(array, i, min);
		}
	}

	private static void swap(int[] a, int i, int min) {
		int temp = a[i];
		a[i] = a[min];
		a[min] = temp;
	}
}
