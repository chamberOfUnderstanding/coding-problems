/**
 * https://www.hackerrank.com/challenges/s10-basic-statistics/problem
 * 
 * Mean - Average element. Sum of all elements / number of elements
 * Median - Midpoint. Average of mid points if the data set has even elements
 * Mode - Element that repeats the most
 * 
 */

package math.statistics;

import java.util.Arrays;

public class Mean_Median_Mode {

	public static void main(String[] args) {
		int[] a = {11, 23, 5, 5, 2, 4, 4, 5, 6, 4, 4};
		int n = a.length;
		int sum = 0;
		for(int i = 0; i < a.length; i++) {
			sum += a[i]; 
		} 
		
		double mean = (double)sum/n;  

		Arrays.sort(a);
		double median = (n%2 == 0 ? 
				(a[n/2] + a[n/2 - 1])/2.0 : 
					(double) a[n/2]);
		
		int mode = a[0];    
		int previous = a[0];
		int count = 1;
		int maxCount = 1;
		for(int i = 1; i < n; i++) {
			if(a[i] == previous)
				count++;
			else{
				if(count > maxCount) {
					maxCount = count;
					mode = previous;
				}
				count = 1;
				previous = a[i];
			}
		}                    

		System.out.printf("%.1f", mean);   
		System.out.println();
		System.out.printf("%.1f", median);
		System.out.println();
		System.out.print(mode);
	}
}    
