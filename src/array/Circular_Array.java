package array;

/**
 * @author 47un
 *
 *  http://www.geeksforgeeks.org/circular-array/
 * 
 *  Start from given spot and go till start + items.
 *  Mod the counter by starting index and output it
 * 
 *  O(items), O(1)
 * 
 */

public class Circular_Array {

	public static void main(String[] args) {
		char[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
		int start = 4;
		int items = 8;
		circular_Print(array, start, items);
	}

	private static void circular_Print(char[] array, int start, int items) {
		for(int i = start; i < items + start; i++)
			System.out.print(array[i % array.length] + " ");
	}
}
