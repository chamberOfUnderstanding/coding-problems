package array;

/**
 * @author 47un
 * 
 * Given three integer arrays and a â€œsumâ€�, the task is to check if there are three elements a, b, c such that a + b + c = sum and a, b and c belong to three different arrays.
 * 
 * http://www.geeksforgeeks.org/find-three-element-from-different-three-arrays-such-that-that-a-b-c-k/
 * 
 * 
 * METHOD 1 : 
 *             
 * TIME    : O()
 * SPACE   : O()
 * 
 */

public class Triplet_Sum_From_Three_Arrays {

	public static void main(String[] args) {
		int[] a = { 1 , 2 , 3 , 4 , 5 };
		int[] b = { 2 , 3 , 6 , 1 , 2 };
		int[] c = { 3 , 2 , 4 , 5 , 6 };
		int sum = 9;
		System.out.println(isTripletSumPresent(a, b, c, sum));
	}

	private static boolean isTripletSumPresent(int[] a, int[] b, int[] c, int sum) {
		quicksort(a);
		quicksort(b);
		quicksort(c);
		for(int i = 0, j = b.length - 1; i < a.length && j >= 0; ) {
			int partialSum = a[i] + b[j];
			int searchStatus = binarySearch(c, sum - partialSum);
			if(searchStatus == 0)
				return true;
			else if(searchStatus == -1)
				i++;
			else
				j++;
		}
		return false;
	}

	private static int binarySearch(int[] c, int x) {
		int l = 0;
		int h = c.length;
		int m = 0;
		while(l < h) {
			m = (l + h) >> 1;
			if(c[m] == x)
				return 0;
			else if(c[m] < x)
				l = m + 1;
			else
				h = m - 1;
		}
		return c[m] < x ? -1 : 1;
	}

	private static void quicksort(int[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static void quicksort(int[] a, int low, int high) {
		if(low <= high) {
			int mid = partition(a, low, high);
			quicksort(a, low, mid - 1);
			quicksort(a, mid + 1, high);
		}
	}

	private static int partition(int[] a, int l, int h) {
		int pivot = a[l];
		l--;
		h++;
		while(true) {
			while(a[++l] < pivot);
			while(pivot  < a[--h]);
			if(l >= h)
				return h;
			swap(a, l, h);
		}
	}

	private static void swap(int[] a, int i, int j) {
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
	}
}
