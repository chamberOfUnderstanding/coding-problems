package array.sorted;

/**
 * @author 47un
 * 
 * Given a sorted array and a value x, the floor of x is the largest element in array smaller than or equal to x.
 * 
 * http://www.geeksforgeeks.org/floor-in-a-sorted-array/
 * 
 * =========
 * METHOD 1
 * =========
 * If the key is < first item, return -1 (It has no floor)
 * If the key is > last item or = last item, return last item
 * If the key is = first item, return 0
 * Binary search the rest of the items, i.e. 1 to length - 2
 * If the array[mid] = key or array[mid] < key and array[mid + 1] > key, return mid
 * If array[mid] > key, move left  => high = mid - 1
 * If array[mid] < key, move right => low  = mid + 1
 * 
 * TIME     : O(logn)
 * SPACE    : O(1)
 *
 * 
 */

public class Sorted_Array_Floor_Search {

    public static void main(String[] args) {
        int[] array = {1, 2, 8, 10, 10, 12, 19};
        int key = 5;
        System.out.println(findFloor(array, key));
    }
    
    private static int findFloor(int[] array, int key){
        if(key < array[0])
            return -1;
        else if(key == array[0])
            return 0;
        else if(key >= array[array.length - 1])
            return array.length - 1;
        int low  = 1;
        int high = array.length - 2;
        while(low <= high){
            int mid = (low + high) >> 1;
            if(array[mid] == key ||
               array[mid] < key && array[mid + 1] > key)
                return array[mid];
            else if(array[mid] < key)
                low  = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}
