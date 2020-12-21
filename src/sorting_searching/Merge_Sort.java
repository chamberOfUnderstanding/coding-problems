package sorting_searching;

/**
 * @author 47un
 *
 * Average case : O(nlogn)
 * Worst case   : O(nlogn)  
 * Best case    : O(nlogn)
 * Auxiliary Space : O(n)
 * 
 * MergeSort method
 * ----------------
 * Stop recursion if high<=low (duh!)
 * Calculate mid use mid = low + (high-low)/2 or (low + high)>>1 
 * Call mergeSort for sub arrays [low,mid] and [mid+1,high]
 * Merge the sub arrays
 * 
 * Merge method
 * ------------
 * Prepare right and left arrays
 * Left array is from low to mid, so size = (mid-low)+1
 * Right array is from mid+1 to high, so size = (high-(mid+1))+1 => high-mid
 * 
 * Fill left and right arrays with values from the original array
 * For left array, the values start from low+i index of original array are copied (Coz left array starts at low)
 * For right array, values start from (mid+1)+i 								  (Coz right array starts at mid)
 * Iterate both arrays and copy the least value onto the actual array
 * Fill remaining values onto the array
 * 
 * 
 * System.arraycopy(sourceArray, sourceStartIndex, destinationArray, destinationStartIndex, numberOfElements)
 *   Copies 'numberOfElements' from 'sourceStartIndex' of 'sourceArray' to destinationArray, starting with 'destinationStartIndex'
 */

public class Merge_Sort {

	public static void main(String[] args) {
		int[] unsortedArray = {4,1,3,-34,55,-100,232323,1,3,4,-111212};
		int[] unsortedArray2 = {1,3,-34,55,-100,232323,1,3,4,-111212};
		int[] unsortedArray3 = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		mergeSort(unsortedArray,0,unsortedArray.length-1);
		mergeSort(unsortedArray2,0,unsortedArray2.length-1);
		mergeSort(unsortedArray3,0,unsortedArray3.length-1);
		for(int i : unsortedArray)
			System.out.print(i+" ");
		System.out.println();
		for(int i : unsortedArray2)
			System.out.print(i+" ");
		System.out.println();
		for(int i : unsortedArray3)
			System.out.print(i+" ");
	}

	private static void mergeSort(int[] array, int low, int high){
		if(low != high){
			int middle = (low + high)>>1;
			mergeSort(array, low, middle);
			mergeSort(array, middle+1, high);
			merge(array, low, middle, high);
		}
	}

	private static void merge(int[] array, int low, int middle, int high) {
		// Elements from low to middle need a size of middle - low + 1
		int[] leftArray = new int[middle - low + 1];
		System.arraycopy(array, low, leftArray, 0, leftArray.length);

		// Elements from middle + 1 to high need a size of high - middle - 1 + 1 => high - middle
		int[] rightArray = new int[high - middle];
		System.arraycopy(array, middle + 1, rightArray, 0, rightArray.length);

		int i = 0;
		int j = 0;
		int k = low;
		
		while(i < leftArray.length && j < rightArray.length)
			array[k++] = leftArray[i] <= rightArray[j]? leftArray[i++] : rightArray[j++];
		while(i < leftArray.length)
			array[k++] = leftArray[i++];
		while(j < rightArray.length)
			array[k++] = rightArray[j++];
	}	
		
}
