package array;

/**
 * @author 47un
 * 
 * Check if there is a triplet in the array that gives the required sum
 *  
 * http://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
 * 
 * =========
 * METHOD 1
 * =========
 * Sort the array
 * Fix the first element
 *  Consider the first and last elements of the array
 *  If these three form the sum, return true
 *  Else if the sum < target sum, we need a larger value, move left
 *  Else move right
 * 
 * TIME     : O(n^2)
 * SPACE    : O(1)
 *
 * 
 */

public class Triplet_Sum {

    public static void main(String[] args) {
        int[] array = {1, 4, 45, 6, 10, 8};
        int sum = 54;
        System.out.println("Is the sum present? " + isTripletSumPresent(sum, array));
    }

    private static boolean isTripletSumPresent(int targetSum, int[] array) {
        quickSort(array, 0, array.length - 1);
        for(int i = 0; i < array.length - 2; i++) {
            int low  = i + 1;
            int high = array.length - 1;
            while(low < high) {
                int sum = array[i] + array[low] + array[high];
                if(sum == targetSum)
                    return true;
                else if(sum < targetSum)
                    low++;
                else
                    high--;
            }
        }
        return false;
    }
    
    private static void quickSort(int[] array, int low, int high) {
        if(low < high) {
            int mid = partition(array, low, high);
            quickSort(array, low, mid);
            quickSort(array, mid + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[low];
        low--;
        high++;
        while(true) {
            while(array[++low] < pivot);
            while(array[--high] > pivot);
            if(low >= high)
                return high;
            swap(array, low, high);
        }
    }

    private static void swap(int[] array, int i, int j) {
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }
}
