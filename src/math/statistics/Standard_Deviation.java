/**
 * @author Solaire
 * 
 * https://www.hackerrank.com/challenges/s10-standard-deviation/problem
 * 
 * =========
 * METHOD 1 
 * =========
 * 
 * 
 * TIME     : O()
 * SPACE    : O()
 *
 */

package math.statistics;

public class Standard_Deviation {

	public static void main(String[] args) {
		int[] a = {3, 7, 8, 5, 12, 14, 21, 13, 18};
		int n = a.length;
		System.out.println(findStandardDeviation(n, a));
	}

	public static double findStandardDeviation(int n, int[] a) {
		int mean = 0;
		for(int i : a)
			mean += i;
		double standardDeviation = 0;
		for(int i : a)
			standardDeviation += Math.pow((double)(i - (double) mean / n), 2);
		return Math.sqrt((double) standardDeviation / n);			
	}   

}
