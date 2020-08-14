package math.statistics;

/**
 *  p/q = odds
 *  q = 1-p
 *  => p = odds/(1 + odds) 
 *  
 *  b = nCx * p^x * q^(n-x)
 *  
 *  Atleast r
 *   Sum of b values for all x from r to n
 *    
 * 
 */
public class Binomial_Distribution {

	public static void main(String[] args) {
		double odds = 1.09;
		double p = odds/(1 + odds);
		double q = 1-p;
		int n = 6;
		int x = 3;
		binomialDistribution(n, x, p, q);
	}

	public static double binomialDistribution(int n, int x, double p, double q) {
		return combination(n, x) * Math.pow(p, x) * Math.pow(q, n-x);
	}

	public static double combination(int n, int r) {
		return ((double)factorial(n) / (factorial(r) * factorial(n-r)));		
	}

	public static int factorial(int n) {
		int result = 1;
		while(n > 1) {
			result *= n--;
		}
		return result;
	}
}
