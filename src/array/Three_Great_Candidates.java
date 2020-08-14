package array;

/**
 * @author 47un
 * 
 * Find the triplet with the largest product in the array
 * 
 * http://www.practice.geeksforgeeks.org/editorial.php?pid=765
 * 
 * =========
 * METHOD 1
 * =========
 * Sort the array
 * Get the product of the first two values and the last value
 *  First two values because, if the input is negative, the two least negatives will give a large positive value when multiplied
 * Get the product of the last three values
 * Find the maximum among the above two
 * 
 * TIME     : O(nlogn)
 * SPACE    : O(1)
 *
 * =========
 * METHOD 2
 * =========
 * Scan the array, track the least two values and the largest 3 values using variables
 * Compare the products and find the largest among them
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Three_Great_Candidates {

    public static void main(String[] args) {
        int[] candidates = {0, -1, 3, 100, 70, 50};
        findThreeGreatCandidates(candidates);
    }

    private static void findThreeGreatCandidates(int[] candidates) {
        int n = candidates.length - 1;
        quickSort(candidates, 0 , n);
        System.out.println(
                Math.max(candidates[0] * candidates[1] * candidates[n],
                         candidates[n - 2] * candidates[n - 1] * candidates[n]));
    }

    private static void quickSort(int[] candidates, int low, int high) {
        if(low < high) {
            int mid = partition(candidates, low, high);
            quickSort(candidates, low, mid);
            quickSort(candidates, mid + 1, high);
        }
    }

    private static int partition(int[] candidates, int low, int high) {
        int pivot = candidates[low];
        low--;
        high++;
        while(true) {
            while(candidates[++low] < pivot);
            while(candidates[--high] > pivot);
            if(low >= high)
                return high;
            swap(candidates, low, high);
        }
    }

    private static void swap(int[] array, int i, int j) {
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }
}
