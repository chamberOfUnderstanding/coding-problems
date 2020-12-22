package sorting_searching;

/**
 * @author 47un
 *
 * Best case    :  O(nlogn)     [ Randomized array ]
 * Worst case   :  O(n2)        [ Lopsided partitioning / All elements are the same / Array is already sorted (ascending or descending) ]
 * Average case :  O(nlogn)
 * 
 * INVARIANT : pivot is in its correct spot and all to its left are < pivot and all to its right are >
 * 
 * Take an item as pivot. 
 * After one "partitioning", the pivot will be in the right spot, so all items to its left are < and right are > it.
 * Hoare partition deals with this logic. 
 * 	First item of the partition is chosen as pivot.
 * 	Scan right until a value > pivot is seen.
 * 	Scan left until a value < pivot is seen.
 * 	If the indices cross, return right index.
 * 	Else swap the items at these indices
 * 
 * Best case happens when the partitioning is even, i.e. pivot falls close to the median
 *
 */

@SuppressWarnings("unused")
public class Quick_Sort {

    public static void main(String[] args) {
        int[] unsortedArray = {65, 48, 59, 21, -111212, 0, 76, 32, 95, 80, 12, 100, 113, 127, 139};
        quickSort(unsortedArray, 0, unsortedArray.length-1);
        for(int i : unsortedArray)
            System.out.print(i+" ");
    }

    private static void quickSort(int[] array, int low, int high){
        if(low < high){
            int middle = partition_Hoare(array, low, high);
            quickSort(array, low, middle);
            quickSort(array, middle+1, high);
        }
    }

    private static int partition_Hoare(int[] array, int low, int high) {		
        int pivot = array[low];
        int leftIndex = low - 1;
        int rightIndex = high + 1;
        while(true){
            while(array[++leftIndex] < pivot);
            while(array[--rightIndex] > pivot);
            if(leftIndex >= rightIndex)
                return rightIndex;
            swap(array, leftIndex, rightIndex);
        }
    }

    private static int partition_Lomuto(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for(int j = low; j < high; j++)
            if(array[j] <=pivot)
                swap(array, ++i, j);
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }  
}