package array;

/**
 * @author 47un
 *
 * There are 2 sorted arrays A and B of size n each.
 * Find the median of the array obtained after merging the 2 arrays in O(log(n))
 * 
 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
 * 
 * ==============================
 * METHOD 1 : Divide and conquer!
 * ==============================
 * Scan both arrays, maintain low, middle and high indices
 * Calculate median (middle element between low and high) of both arrays
 * If the medians are equal, this is the median of the merged array as well, so return it
 * Repeat till low = high for both arrays
 * Return (max of low elements + max of high elements)/2
 *             
 * TIME     : O(logn)
 * SPACE    : O(1)
 * 
 * NOTE : Condition to find the median gets reversed as the array is 0 indexed        
 *
 * ====================
 * METHOD 2 : Iteration
 * ====================
 * Scan till the least n/2 items are found
 * Add up the n/2 - 1 th and n/2 th items
 * 
 * TIME     : O(n)
 * TIME     : O(1)
 * 
 */

public class Sorted_Arrays_Median {

    public static void main(String[] args) {
        int size     = 5; 
        int[] array1 = {1, 12, 15, 26, 38};
        int[] array2 = {2, 13, 17, 30, 45};
        int median = findMedianDivideAndConquer(array1, array2, size);
        System.out.println(median);
    }
    
    private static int findMedianDivideAndConquer(int[] array1, int[] array2, int size) {
        if(array1.length != array2.length)
            return -1;                   
        int low1  = 0;
        int low2  = 0;
        int high1 = array1.length - 1;
        int high2 = array2.length - 1;
        while(high1 > low1 && high2 > low2) {
            int middle1 = low1 + (high1 - low1) >> 1;
            int middle2 = low2 + (high2 - low2) >> 1;
            int median1 = median(middle1, array1);
            int median2 = median(middle2, array2);
            if(median1 == median2)
                return median1;
            // If median1 < median2, find median of array1[middle..high] and median of array2[0...middle]
            else if(median1 < median2) {
                low1  = middle1;
                high2 = middle2; 
            }
            // If median1 > median2, find median of array1[0...middle] and median of array2[middle..high]
            else{
                high1 = middle1; 
                low2  = middle2;
            }
        }
        return (Math.max(array1[low1], array2[low2]) + Math.max(array1[high1], array2[high2])) >> 1;
    }
    
    // if middle is odd, return a[middle] else average of a[middle - 1] and a[middle]
    private static int median(int middle, int[] array) {
        return (middle % 2 != 0)? (array[middle - 1] + array[middle]) >> 1 : array[middle];
    }

    @SuppressWarnings("unused")
    private static int findMedianIterative(int[] array1, int[] array2) {
        int size = array1.length;
        int i = 0;
        int j = 0;
        int k = 0;
        int least = 0;
        int sum = 0;
        while(true){
            least = array1[i] <= array2[j]? array1[i++] : array2[j++];
            k++;
            if(k == size)
                sum += least;
            else if(k == size + 1){
                sum += least;
                break;
            }
        }
        return sum;
    }
    
}
