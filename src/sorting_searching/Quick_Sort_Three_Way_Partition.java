package sorting_searching;

/**
 * @author 47un
 *
 * Quick sort with a 3 way partition.
 * 
 * ===========
 * METHOD 1 
 * ===========
 * 
 * Partition the array into 3 parts -> less than pivot, equal to pivot, greater than pivot.
 * 	item < pivot? Swap item with less_than and advance both less_than and index
 *  item = pivot? Advance index
 *  item > pivot? Swap item with greater_than and advance just greater_than. Now the one at greater_than moves to index.
 *                For = comparison with this item, it needs to be considered for the next iteration, hence don't update index.
 * Sort from low to less_than - 1
 * Sort from greater_than + 1 to high
 * Everything in between can be ignored. 
 * 
 * TIME     : O(n^2)
 * SPACE    : O(1)
 *
 */

public class Quick_Sort_Three_Way_Partition {

	public static void main(String[] args) {
		int[] unsorted_array = {65, 28, 59, 33, 21, 56, 22, 95, 50, 12, 90, 53, 28, 77, 39};
		quickSortThreeWayPartition(unsorted_array);
		for(int i : unsorted_array)
			System.out.print(i + " ");
	}

	private static void quickSortThreeWayPartition(int[] array) {
		quickSortThreeWayPartition(array, 0, array.length - 1);
	}

	private static void quickSortThreeWayPartition(int[] array, int low, int high) {
		if(low < high){
			int less_than    = low;
			int greater_than = high;
			int pivot = array[low];
			int index = low;
			while(index <= greater_than){
				int comparison = compare(array[index], pivot);
				switch(comparison){
					case -1 : swap(array, less_than++, index++);  break;
					case  0 : index++; break;
					case  1 : swap(array, index, greater_than--); break;
				}
			}
			quickSortThreeWayPartition(array, low, less_than - 1);
			quickSortThreeWayPartition(array, greater_than + 1, high);
		}
	}

	private static int compare(int n, int pivot) {
		return n < pivot? -1 : n > pivot? 1 : 0;
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}