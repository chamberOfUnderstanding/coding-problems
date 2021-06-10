package array;

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
 *
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
        // key < first item, return -1 (It has no floor)
        if(key < array[0])
            return -1;
        // key is = first item, return 0
        else if(key == array[0])
            return 0;
        // key is > last item or = last item, return last item
        else if(key >= array[array.length - 1])
            return array.length - 1;
        //  Binary search the rest of the items, i.e. 1 to length - 2
        int low  = 1;
        int high = array.length - 2;
        while(low <= high){
            int mid = (low + high) >> 1;
             if(array[mid] == key ||
               array[mid] < key && array[mid + 1] > key)
                return array[mid];
            // move right
            else if(array[mid] < key)
                low  = mid + 1;
            // move left
            else
                high = mid - 1;
        }
        return -1;
    }
}
