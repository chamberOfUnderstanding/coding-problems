package array;

/**
 * @author 47un
 *
 * Given a sorted array and a number. Find the number of occurrences of this number in the array
 * 
 * http://www.geeksforgeeks.org/count-number-of-occurrences-in-a-sorted-array/
 * 
 * ==========================
 * METHOD 1 : Binary search
 * ==========================
 * Binary search for the first occurrence  -> O(logn)
 * The typical binary search returns true if the item is found, in this case we need to strech as we need the first occurrence.
 * For that if the item has been found, check if the index is 0 (first item) or the item before this one is less than this one (property of the first occurrence)
 *   If yes return this index, as this is the first occurrence of the item
 *   If no, we have to move left i.e. update high to mid - 1
 *   If the item is greater than mid item, move right (update low)
 *   If the item is less than mid item, move left (update high)
 * 
 * If the search for the first occurrence was a failure, quit!
 *  
 * Binary search for the last occurrence   -> O(logn)
 * Similarly in this case, if the item has been found, make the following checks:
 *   If this is the last item or the item next to this is greater than this one (property of the last occurrence of an item), return this index
 *   Else we have to move right (update low)
 *   If the item is greater than mid item, move right (update low)
 *   If the item is less than mid item, move left (update high) (  
 * Do lastOccurence - firstOccurence + 1
 *
 * TIME     :  O(logn) 
 * SPACE    :  O(1)
 *
 * =========================
 * METHOD 2 : Linear search
 * =========================
 * Linear search for first occurrence       -> O(n)
 * Keep counting till something new is seen -> O(n)
 *            
 * TIME     :  O(n) 
 * SPACE    :  O(1)            
 * 
 * 
 */

public class Sorted_Array_Number_Of_Occurences {

    public static void main(String...x){
        int[] array = {-5, -5, -5, 1, 2, 3, 4, 4, 7, 7, 7, 7, 7, 7, 8, 54, 99, 99, 99, 99, 99};
        int item = 7;
        System.out.println(findNumberOfOccurences(array, item));
    }

    private static int findNumberOfOccurences(int[] array, int item) {
        int firstIndex = getFirstIndex(0, array.length - 1, item, array);
        if(firstIndex == -1){
            System.out.println("Item not found!");
            System.exit(0);
        }
        return getLastIndex(firstIndex, array.length - 1, item, array) - firstIndex + 1;
    }

    private static int getFirstIndex(int low, int high, int item, int[] array) {
        while(low <= high) {
            int mid = (low + high) >> 1;
            if(array[mid] == item){
                if(mid == 0 || array[mid - 1] < item)
                    return mid;
                else
                    high = mid - 1;
            }
            else if(array[mid] > item)
                high = mid - 1;
            else
                low  = mid + 1;
        }
        return -1;
    }

    private static int getLastIndex(int low, int high, int item, int[] array) {
        while(low <= high){
            int mid = (low + high) >> 1;
            if(array[mid] == item){  
                if(mid == array.length - 1 || array[mid + 1] > item)
                    return mid;
                else
                    low = mid + 1;	
            }
            else if(array[mid] > item)
                high = mid - 1;
            else
                low  = mid + 1;				
        }
        return -1;
    }
}
