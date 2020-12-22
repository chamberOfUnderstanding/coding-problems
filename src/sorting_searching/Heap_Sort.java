package sorting_searching;

/**
 * @author 47un
 * 
 * Max heap - Parent value >= child values
 * Min heap - Parent value <= child values
 * 
 * Heaps are complete binary trees
 * 
 * Array is the most space efficient way to represent a heap
 * 
 * Element at position i of a heap has children at positions 2*i + 1 and 2*i + 2
 * 
 * Heapify will make sure the parent - child - child sub tree follows the heap property
 *
 * Sort
 * -----
 * Starting from n/2 sink or heapify all elements till 0 
 * As long as the array size is >=0 
 *  - After the initial heapification, the smallest/largest element will be at the top of the heap. 
 *    This element is considered as "sorted". Replace it with the last element.  
 *  - Since 1 element has been sorted, reduce the overall size by 1
 *  - Heapify the first position and repeat
 *
 * Heapify
 * -------
 * During sink/heapify, child elements need to be within bounds (heapSize). 
   Hence the steps are repeated as long as 2*parent + 1 <= heapSize.
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
		int[] unsortedArray2 = {-88,2423,1,0,-4,333,-55};
		Heap_Sort heapSort = new Heap_Sort();
		heapSort.heapSort(unsortedArray);
		heapSort.heapSort(unsortedArray2);
		for(int item : unsortedArray)
			System.out.print(item + " ");
		System.out.println();
		for(int item : unsortedArray2)
			System.out.print(item + " ");
	}

	private void heapSort(int[] array){
		int size = array.length - 1;		
		for(int i = size/2; i >= 0; i--)
//			heapify(i, size, array);
			heapify_Recursive(i, size, array);
		// --size makes sure the sorted elements are not considered
		while(size >= 0){
			swap(0, size, array);
//			heapify(0, --size, array);
			heapify_Recursive(0, --size, array);
		}
	}

	// aka sink
	private void heapify_Iterative(int parent, int heapSize, int[] array) {
		while(2 * parent + 1 < heapSize){
			int child = 2 * parent + 1;
			// prefer the largest child
			if(child < heapSize && array[child] < array[child + 1])
				child++;
			if(array[parent] >= array[child])
				break;
			swap(parent, child, array);
			parent = child;
		}
	}
	
	private void heapify_Recursive(int parent, int heapSize, int[] array) {
		// assume parent is the largest
		int largest = parent;
		int leftChild = 2 * parent + 1;
		int rightChild = 2 * parent + 2;
		// update largest with the index of the largest child
		if (leftChild < heapSize && array[leftChild] > array[largest])
			largest = leftChild;
		if (rightChild < heapSize && array[rightChild] > array[largest])
			largest = rightChild;
		// if any child is found to be larger that the parent, swap parent and that child and recurse down the heap
		if (largest != parent) {
			swap(parent, largest, array);
			heapify_Recursive(largest, heapSize, array);
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
