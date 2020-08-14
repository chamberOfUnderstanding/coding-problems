/**
 * 
 * https://www.hackerrank.com/challenges/s10-quartiles/problem
 * 
 *  
 */

package math.statistics;

import java.util.Arrays;

public class Quartiles {

	public static void main(String[] args) {
		int[] a = {6, 12, 8, 10, 20, 16};
		int[] f = {5, 4, 3, 2, 981, 5};
		int n = a.length;
		findQuartiles(n, a);
		findInterQuartileRange(a, f);
	}
	
	public static void findInterQuartileRange(int[] a, int[] f) {
		int n = Arrays.stream(f).sum();
		int[] x = new int[n];
		int k = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < f[i]; j++) {
				x[k++] = a[i];
			}
		}
		Arrays.sort(x);
		System.out.println(
				findMedian(n/2 + (n % 2 == 0 ? 0 : 1), n - 1, x) - 
				findMedian(0, n/2 - 1, x)
				);
		
	}

	public static void findQuartiles(int n, int[] a) {
		Arrays.sort(a);
		System.out.println(findMedian(0, n/2 - 1, a));
		System.out.println(findMedian(0, n - 1, a));
		System.out.println(findMedian(n/2 + (n % 2 == 0 ? 0 : 1), n - 1, a));
	}

	public static float findMedian(int s, int f, int[] a) {
		int length = f - s + 1;
		int mid = length/2 + s;
		return length % 2 == 0 ? (float)(a[mid] + a[mid - 1])/2 : (float)a[mid];				
	}
}
