package array;

/**
 * @author 47un
 *
 * Quick Selection algorithm to find the k th smallest element.
 * k = N/2 gives median.
 * 
 * ===========
 * METHOD 1 
 * ===========
 * Variant of quick sort.
 * Instead of quick sorting both sides of the partition element, move to the side that can hold k.
 * After partitioning once, compare k to the length of the left partition.
 *  k = left partition length ? return the partition element
 *  k < left partition length ? partition the left partition (low to middle - 1)
 *  k > left partition length ? partition the right partition (middle + 1 to high). For this, k needs to be updated to k -= length 
 * 
 * To make things easy, k = 1 to array length is printed here.
 * 
 * TIME     : O(n) // Quick Sorts the half that matters.
 * SPACE    : O(1)
 *
 */

public class Quick_Select {

	public static void main(String[] args) {
		int[] array = {65, 48, 59, 21, 76, 32, 95, 80, 12, 100, 113, 127, 139, 76};
		for(int k = 1; k < array.length; k++)
			System.out.println(k + " -> " + quickSelect(array, 0, array.length - 1, k));
	}

	private static int quickSelect(int[] array, int low, int high, int k){
		if(low == high)
			return array[low];
		int middle = partition_Lomuto(array, low, high);
		int length = middle - low + 1;
		return (k == length)? 
				array[middle] : (k < length)?
						quickSelect(array, low, middle - 1, k) : quickSelect(array, middle + 1, high, k - length);
	}

	private static int partition_Lomuto(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1;
		for(int j = low; j < high; j++)
			if(array[j] <= pivot)
				swap(array, ++i, j);
		swap(array, i + 1, high);
		return i + 1;
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// This is for another day, a day that never comes.. **cries in a corner**
	@SuppressWarnings("unused")
	private static int partition_Hoare(int[] array, int low, int high) {		
		int pivot = array[low];
		int leftIndex = low - 1;
		int rightIndex = high + 1;
		while(true){
			while(array[++leftIndex] < pivot);
			while(array[--rightIndex] > pivot);
			if(leftIndex >= rightIndex)
				return rightIndex;
			swap(array, leftIndex, rightIndex);
		}
	}
}

