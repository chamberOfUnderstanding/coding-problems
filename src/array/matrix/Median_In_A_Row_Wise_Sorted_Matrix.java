package array.matrix;

/**
 * @author arun_anandakrishnan
 *
 * Given a row wise sorted matrix of size r*c, we need to find the median of the matrix. 
 * It is assumed that r*c is always odd.
 *      
 *      1 3 5
 *      2 6 9  =>> 5
 *      3 6 9
 *      
 * ========================
 * METHOD 1 : Use an array
 * ========================
 * Store the contents of the matrix on an array.
 * Find the median of the array. (Quick Select)
 * 
 * TIME     : O(r * c)
 * SPACE    : O(r * c)
 * 
 *
 */

public class Median_In_A_Row_Wise_Sorted_Matrix {

	public static void main(String... args) {
		int rows    = 3;
		int columns = 3;
		int[][] matrix = 
				   {{1, 3, 5},
					{2, 6, 9},
					{3, 6, 9}};
		System.out.println("Method I  : " + medianUsingArray(matrix, rows, columns));
		System.out.println("Method II : " + medianUsingBinarySearch(matrix, rows, columns));
	}

	private static int medianUsingArray(int[][] matrix, int rows, int columns) {
		int[] array = new int[rows * columns];
		int index = 0;
		for(int row = 0; row < rows; row++)
			for(int column = 0; column < columns; column++)
				array[index++] = matrix[row][column];
		return quickSelect(array, 0, array.length - 1, (array.length + 1)/2);
	}

	private static int quickSelect(int[] array, int low, int high, int k) {
		if(low == high)
			return array[low];
		int middle = partition(array, low, high);
		int length = middle - low + 1;
		return (k == length)? 
				array[middle] : (k < length)? 
						quickSelect(array, low, middle - 1, k) : quickSelect(array, middle + 1, high, k - length);
	}

	private static int partition(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1;
		for(int j = low; j < high; j++)
			if(array[j] <= pivot)
				swap(array, ++i, j);
		swap(array, i + 1, high);
		return i + 1;
	}

	private static String medianUsingBinarySearch(int[][] matrix, int rows, int columns) {
		return null;
	}

	private static void swap(int[] a, int i, int j) {
		int x = a[j];
		a[j]  = a[i];
		a[i]  = x;
	}  
}
