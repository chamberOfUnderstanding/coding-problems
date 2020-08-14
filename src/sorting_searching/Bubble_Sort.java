package sorting_searching;

/**
 * @author 47un
 *
 * Implement Bubble sort
 * 
 * https://visualgo.net/sorting
 * 
 * =========
 * METHOD 1
 * ===========
 * Iterate the array (i loop)
 * For each element, j loop will compare it with adjacent element and swap if needed
 * After i'th iteration, i'th largest will be at the end of the array, so the rest of the iterations can skip this element, hence n-i
 * If no swaps were made, meaning the array got sorted, end the iterations 
 * size - i - 1 is the index of the last unsorted element 
 * 
 * INVARIANT : n-i ... n is always sorted
 * 
 * BEST TIME      : O(n)   [ Array is already sorted]
 * WORST TIME     : O(n^2) [ Random array, reverse sorted array ]
 * AVERAGE TIME   : O(n^2)
 * 
 * SPACE     : O(1) 
 *
 * 
 */

@SuppressWarnings("unused")
public class Bubble_Sort {
	
	public static void main(String[] args) {
		int[] unsortedArray = {4,1,3,-34,55,-100,232323,1,3,4};
		int[] sortedArray = {-100, -34, 1, 1, 3, 3, 4, 4, 55, 232323};
		Bubble_Sort bubbleSort = new Bubble_Sort();
		bubbleSort.sort(unsortedArray);
		for(int item : unsortedArray)
			System.out.print(item + " ");
	}	
	
	public void sort(int[] array){
		int size      = array.length;
		boolean swaps = true;
		for(int i = 0; i < size && swaps; i++){
			swaps = false;
			for(int j = 0; j < size-i-1; j++)
				if(array[j] > array[j+1])
					swaps = swapUsingXOR(array, j, j+1);
		}
	}

	public boolean swap(int[] array, int index1, int index2){
		int temp = array[index2];
		array[index2]=array[index1];
		array[index1]=temp;
		return true;
	}
	
	// WOAH!
	// https://www.cs.umd.edu/class/sum2003/cmsc311/Notes/BitOp/xor.html
	public boolean swapUsingXOR(int[] array, int index1, int index2) {
	    array[index1] = array[index1] ^ array[index2];
	    array[index2] = array[index1] ^ array[index2];
	    array[index1] = array[index1] ^ array[index2];
	    return true;
	}
}
