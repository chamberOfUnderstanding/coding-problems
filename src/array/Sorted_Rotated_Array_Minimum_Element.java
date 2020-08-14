package array;

/**
 * @author 47un
 * 
 * A sorted array A[ ] with distinct elements is rotated at some unknown point, find the minimum element in it.
 * 
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=819
 * 
 * METHOD 1 : Tweaked binary search
 *            Since the array is sorted and rotated, the minimum element will have a the largest element to its left
 *            Binary search while keeping low as 1 and high as size - 1
 *              low is 1 and not 0 because the comparison is with the previous element hence low - 1 for low = 0 => index out of bounds
 *            Also record the lastElement in the array (This element decides which direction to move during the search, left or right)
 *            Typical binary search process except :
 *             If the item before mid is larger than mid, return mid (the minimum element)
 *             If the mid is less than last item, move left by updating high
 *             Else move right by updating low
 *            If the item has not been found, it is at the index we ignored all this time, array[0]
 *
 * TIME     : O(logn)
 * SPACE    : O(1)
 *
 * 
 */

public class Sorted_Rotated_Array_Minimum_Element {

    public static void main (String[] args){
        int size    = 5;
        int[] array = {4, 5, 6, 8, 1};
        System.out.println(findMinimumElement(size, array));
    }
    
    private static int findMinimumElement(int size, int[] array){
        int low   = 1;
        int high  = size - 1;
        int lastElement  = array[high];
        while(low <= high){
            int mid = (low + high) >> 1;
            if(array[mid - 1] > array[mid])
                return array[mid];
            else if(array[mid] < lastElement)
                high = mid - 1;
            else
                low  = mid + 1;
        }
        return array[0];
    }

}
