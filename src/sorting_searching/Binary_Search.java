package sorting_searching;

import java.util.Arrays;

/**
 * @author 47un
 *
 * Best case : O(logn)
 * 
 * Sort the array, get the mid
 * If the mid value is greater than item, move left
 * If the mid value is less than the item, move right
 * It they are equal, return mid or boolean
 * 
 * >>> and >> are right shift operators
 * >> is arithmetic hence the sign of the number is preserved
 * >>> is logical (unsigned), so the sign is ignored
 * 
 * 
 */

public class Binary_Search {
	
	public static void main(String...strings){
		    int[] array = {4,1,3,-34,55,1,3,4};
		    int size = array.length;
		    int item = -34;
			//Array needs to be sorted for this to work
			Arrays.sort(array);
			int result = binarySearchIterative(array,size,item);
			System.out.println(binarySearchRecursive(array, 0, size-1, item));
			System.out.print(result ==- 1?"\nItem not found!":"\nItem found");
	}

    private static boolean binarySearchRecursive(int[] array, int low, int high, int item){
		if(low <= high)	{
			int mid = (low + high)>>1;
			if(array[mid] < item)
				return false || binarySearchRecursive(array, mid+1, high, item);
			else if(array[mid] > item)
				return false || binarySearchRecursive(array, low, mid-1, item);
			return true;			
		}
		return false;
	}

	private static int binarySearchIterative(int[] array,int size,int item){
		int low  = 0;
		int high = size-1;
		int mid;
		while(low <= high){
			mid = (low + high)>>>1;
			if(array[mid] == item)
				return mid;
			else if(array[mid] < item)
				low = mid+1;
			else
				high = mid-1;
		}
		return -1;
	}
}
