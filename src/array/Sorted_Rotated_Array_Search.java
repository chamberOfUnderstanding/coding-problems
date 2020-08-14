package array;

/**
 * @author 47un
 * 
 * Given a sorted and rotated array (rotated at some point), and given an element K, find the index of the given element K in the array.
 * The array has no duplicate elements. If the element does not exist in the array, print -1.
 * 
 * http://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
 * 
 * METHOD 1 : Find the pivot point (the point where the rotation happened)
 *              After the pivot the array starts to ascend
 *            If the item is < first item then binary search from first to pivot
 *            If the item is < last item then binary search from pivot + 1 to last
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Sorted_Rotated_Array_Search {

    public static void main(String[] args) {
        int[] array = {4, 5, 6, 7, 1, 2, 3};
        int item = 7;
        System.out.println(findItem(item, array));
        System.out.println(binarySearchII(array, 0, array.length - 1, item));
    }

    private static int findItem(int item, int[] array) {
        if(array[0] == item)
            return 0;
        if(array[array.length - 1] == item)
            return array.length - 1;
        int low   = 0;
        int high  = 0;
        int pivot = -1;
        for(int i = 1; i < array.length - 1; i++)
            if(array[i+1] < array[i]) {
                pivot = i;
                break;
            }
        if(pivot == -1) {
            low = 0;
            high = array.length - 1;
        }
        else if(array[0] < item) {
            low = 0;
            high = pivot;
        }
        else if(item < array[array.length - 1]) {
            low = pivot + 1;
            high = array.length - 1;
        }
        return binarySearch(low, high, item, array);
    }

    private static int binarySearch(int low, int high, int item, int[] array) {
        while(low <= high) {
            int mid = (low + high) >> 1;
        if(array[mid] == item)
            return mid;
        else if(array[mid] < item)
            low = mid + 1;
        else
            high = mid - 1;
        }
        return -1;
    }

    public static int binarySearchII(int array[], int low, int high, int key) {
        if (low > high)
            return -1;
        int mid = (low + high) >> 1;
        if (array[mid] == key) 
            return mid;
        if (array[low] <= array[mid]) {
            if (key >= array[low] && key <= array[mid])
                return binarySearchII(array, low, mid - 1, key);
            return binarySearchII(array, mid + 1, high, key);
        }
        if (key >= array[mid] && key <= array[high])
            return binarySearchII(array, mid + 1, high, key);
        return binarySearchII(array, low, mid - 1, key);
    }
}
