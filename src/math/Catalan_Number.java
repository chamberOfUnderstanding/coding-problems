package math;

/**
 * @author 47un
 *
 * Find the nth Catalan Number
 * 
 * http://www.geeksforgeeks.org/program-nth-catalan-number/
 * 
 * nth Catalan Number =     (2n)!
 *                      -------------
 *                      (n + 1)! * n!
 *                      
 * 				OR 
 * 							            2n
 *       					             C
 * nth Catalan Number =         n
 *   					             	-------
 *   					              (n + 1)                    
 * 
 * Recursively, it is =>
 * 
 *   C             = Sigma 0 to n of C  * C
 *    n + 1                           i    n - i
 *    
 * 
 * ====================
 * METHOD 1 : Recursion
 * ====================
 * If n is 0 or 1, return 1 (Termination Clause)
 * Else recurse, find, add
 *             
 * TIME    : O(2^n)
 * SPACE   : O(n)
 * 
 * =============
 * METHOD 2 : DP
 * =============
 * Store and reuse the calculated values
 * 
 * TIME    : O(n^2)
 * SPACE   : O(n)
 * 
 * ===============================
 * METHOD 3 : Binomial Coefficient
 * ===============================
 * Using the below formula
 * 
 * nth Catalan Number =     (2n)!
 *                      -------------
 *                      (n + 1)! * n!
 *                      
 * Expanding the factorials will lead to simplificatin of this monster into ->
 *   n * (n - 1) * (n - 2) * .... 1
 *   ------------------------------
 *   1 * 2 * 3 * ................ r
 *        
 * Which is the same as multiplying n - i and dividing by i + 1, for i in between 0 and r
 *    
 * http://www.geeksforgeeks.org/space-and-time-efficient-binomial-coefficient/
 * 
 * TIME    : O(n)
 * SPACE   : O(1)
 * 
 * https://en.wikipedia.org/wiki/Catalan_number (Applications)
 *  -> Number of expressions containing n pairs of parenthesis which are perfectly matched
 *  -> Number of rooted binary trees with n internal nodes
 * 
 */

public class Catalan_Number {

	public static void main(String[] args) {
		int n = 15;
		System.out.println(calculateCatalanNumberRecursive(n));
		System.out.println(calculateCatalanNumberDP(n));
		System.out.println(calculateCatalanNumberBinomialCoefficient(n));
	}

	private static int calculateCatalanNumberRecursive(int n) {
		if(n == 0 || n == 1)
			return 1;
		int catalanNumber = 0;
		for(int i = 0; i < n; i++)
			catalanNumber += calculateCatalanNumberRecursive(i) * calculateCatalanNumberRecursive(n - i - 1);
		return catalanNumber;
	}

	private static int calculateCatalanNumberDP(int n) {
		int[] catalanNumber = new int[n + 1];
		catalanNumber[0] = 1;
		catalanNumber[1] = 1;
		for(int i = 2; i <= n; i++)
			for(int j = 0; j < i; j++)
				catalanNumber[i] += catalanNumber[j] * catalanNumber[i - j - 1];
		return catalanNumber[n];
	}

	private static long calculateCatalanNumberBinomialCoefficient(int n) {
		return binomialCoefficient(2 * n, n) / (n + 1);
	}
	
	private static long binomialCoefficient(int n, int r) {
		long binomialCoefficient = 1;
		if(r > n - r)
			r = n - r;
		for(int i = 0; i < r; i++) {
			binomialCoefficient *= (n - i);
			binomialCoefficient /= (1 + i);
		}
		return binomialCoefficient;
	}
}
