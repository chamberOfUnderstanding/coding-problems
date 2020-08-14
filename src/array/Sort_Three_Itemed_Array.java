package array;

/**
 * @author 47un
 *
 * Dutch National Flag
 * 
 * Given an array consisting 0s, 1s and 2s or 3 colors r,w,b
 * Sort the array by scanning the elements EXACTLY ONCE! Strictly O(n)
 * 
 * http://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
 * 
 * =========
 * METHOD 1
 * ===========
 * Use low, mid, high for partitioning
 * Repeat till mid reaches the end (high)
 * If current character is 0 and the one at low is'nt 0, swap it with low
 *   Advance low and mid (coz item[low] is now sorted)
 * If current character is 2 and the one at high is'nt 2, swap it with high
 *   Advance high alone, as the one that just got swapped from high to current, can be a 1.
 *  In that case more 0s may appear after this 1, which need to be sorted to low. So advancing low or mid here breaks the algo
 * If the current character is a 1, advance mid            
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Sort_Three_Itemed_Array {

    public static void main(String...strings){
        int[] items = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        sortThreeItemedArray(items);
        printArray(items);
    }

    private static void sortThreeItemedArray(int[] array){
        int low  = 0;
        int mid  = 0;
        int high = array.length - 1;
        while(mid <= high){
            if(array[mid] == 0){
                if(array[low] != 0)
                    swap(array, mid, low);
                mid++;
                low++;
            }
            else if(array[mid] == 2){
                if(array[high] != 2)
                    swap(array, mid, high);
                high--;
            }
            else
                mid++;
        }
    }

    private static void swap(int[] array, int i, int j){
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }

    private static void printArray(int[] items) {
        for(int item : items)
            System.out.print(item + " ");
    }
}
