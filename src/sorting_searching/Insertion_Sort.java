package sorting_searching;

/**
 * @author 47un
 *
 * Implement insertion sort
 * 
 * https://visualgo.net/sorting
 * 
 * =========
 * METHOD 1
 * ===========
 * V1 uses SWAPS
 * Iterate through the array (i loop)
 * Ignore the first element as it is assumed to be sorted
 * For each element, scan right to left
 * Swap if previous element is larger than current element
 * If there're no larger elements to the left of the current element, no swaps are needed (that element is already in the right spot)
 *  
 * V2 uses COPYING
 * Iterate through the array (i loop) 
 * Ignore the first element as it is assumed to be sorted
 * Save the current item (This item needs to be inserted in its proper location in the sorted left sub array)
 * For each element, scan right to left ( j loop)
 * If previous element is larger than item, copy and replace current element
 * When j loop ends, a[j] is a spot with a duplicate element, and the item that was saved previously goes here
 * 
 * INVARIANT      : a[1....i] is sorted
 * 
 * BEST TIME      : O(n)    [Already sorted / Almost sorted]
 * WORST TIME     : O(n^2) 
 * AVERAGE TIME   : O(n^2) [ Random array, reverse sorted array ]
 * 
 * SPACE     : O(1) 
 *
 * 
 */

@SuppressWarnings("unused")
public class Insertion_Sort {

	public static void main(String[] args) {
		int[] unsortedArray = {4,1,3,-34,55,-100,232323,1,3,4};
		int[] sortedArray = {-100, -34, 1, 1, 3, 3, 4, 4, 55, 232323};
		Insertion_Sort insertionSort = new Insertion_Sort();
		insertionSort.insertionSortV2(unsortedArray);
		for(int item : unsortedArray)
			System.out.print(item + " ");
	}
	
	private void insertionSortV1(int[] array){		
		for(int i = 1; i < array.length; i++){			
			for(int j = i; j > 0; j--)				
				if(array[j] < array[j - 1])
					swap(array, j, j-1);	
				else
					break;
		}
	}

	private void insertionSortV2(int[] array){		
		for(int i = 1; i < array.length; i++){
			int item = array[i];
			int j = i;
			for( ; j > 0; j--)
				if(item < array[j-1])
					array[j] = array[j-1];
				else
					break;
			array[j] = item;
		}
	}


	private static void swap(int[] a, int j, int i) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
