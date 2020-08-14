package sorting_searching;

/**
 * @author 47un
 *
 * 
 * 
 * https://visualgo.net/sorting
 * 
 * =========
 * METHOD 1
 * ===========
 * Sort method
 * -----------
 * Each parent node at position i in a heap has children at positions 2*i and 2*i+1,
 * only if these positions do not exceed the length of the array.
 * Since the sink() algorithm swaps parents with their children, only length/2 iterations are needed. 
 * ie. Element at n/2 will be the parent to the last elements of the array
 * Moreover, the heap is constructed from bottom to top. Therefore, the iteration starts at n/2 going up, i.e. towards position 0.
 * 
 * So, starting from n/2 sink or heapify all elements till 0 
 * As long as the array size is >=0, swap the first and last elements, reduce the overall size by 1 and sink the first element
 *
 * Sink method
 * -----------
 * Since we are sinking element at i, it's children can be at 2*i and 2*i+1, hence we repeat this as long as 2*i<=n 
 * Find out if any of the children is larger than the parent -
 *  Here we are comparing the left and right child, we pick the child with the larger value
 *  Then compare the chosen child with the papa and swap if needed
 * If none are larger than parent, break (coz he's in the right spot)
 * If either is larger, swap it with the parent and sink a level down (i=j) to verify the grandchildren 
 * 
 *
 * BEST TIME      : O(nlogn)
 * WORST TIME     : O(nlogn) 
 * AVERAGE TIME   : O(nlogn)
 * 
 * SPACE          : O(1) 
 *
 * 
 */

public class Heap_Sort {

	public static void main(String...strings){
		int[] unsortedArray = {-88,2423,1,0,-4,333,-55,67434};
		Heap_Sort heapSort = new Heap_Sort();
		heapSort.heapSort(unsortedArray);
		for(int item : unsortedArray)
			System.out.print(item + " ");
	}

	private void heapSort(int[] array){
		int size = array.length - 1;		
		for(int i = size/2; i >= 0; i--)
			sink(i, size, array);
		while(size >= 0){
			swap(0, size, array);
			sink(0, --size, array);
		}
	}

	private void sink(int parent, int heapSize, int[] array) {
		while(2 * parent <= heapSize){
			int child = 2 * parent;
			if(child < heapSize && array[child] < array[child+1])
				child++;
			if(array[parent] >= array[child])
				break;
			swap(parent, child, array);
			parent = child;
		}
	}

	private void swap(int index1, int index2, int[] array) {
		int temp = array[index2];
		array[index2] = array[index1];
		array[index1] = temp;
	}
	
    // WOAH!
    // https://www.cs.umd.edu/class/sum2003/cmsc311/Notes/BitOp/xor.html
	
    public boolean swapUsingXOR(int[] array, int index1, int index2) {
        array[index1] = array[index1] ^ array[index2];
        array[index2] = array[index1] ^ array[index2];
        array[index1] = array[index1] ^ array[index2];
        return true;
    }
}
