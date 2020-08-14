package array;

/**
 * @author 47un
 * 
 * Replace every element with the largest element on its right
 * 
 * http://www.geeksforgeeks.org/replace-every-element-with-the-greatest-on-right-side/
 * 
 * ============================
 * METHOD 1 : Reverse scanning
 * ============================
 * Set the largest as the last item on the array
 * Scan the array in reverse from second last item to first item (0 index)
 *  If the current item, array[i], is larger than largest, swap array[i] and largest
 *  Else set array[i] with the value of largest
 * Set the last item of the array as -1 
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Largest_Element_On_The_Right {

	public static void main(String[] args) {
		test(new int[] {16, 17, 4, 3, 5, 2});
		test(new int[] {2, 4, 66, 67, 334, 2155});
		test(new int[] {2155, 43, 33, 7, 4});
		test(new int[] {7, 7, 7, 7, 7});
	}

	private static void test(int[] input) {
		replaceWithLargestOnTheRight(input);
		printArray(input);
	}

	private static void replaceWithLargestOnTheRight(int[] array) {
		int largestElement = array[array.length - 1];
		for(int i = array.length - 2; i >= 0; i--)
			if(array[i] > largestElement) {
				array[i] ^= largestElement;        
				largestElement ^= array[i];        
				array[i] ^= largestElement; 
			}
			else
				array[i] = largestElement;
		array[array.length - 1] = -1;
	}

	private static void printArray(int[] array) {
		for(int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
