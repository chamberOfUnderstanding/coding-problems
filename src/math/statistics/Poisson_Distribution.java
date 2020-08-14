package math.statistics;

public class Poisson_Distribution {

	public static void main(String[] args) {
        double lambda = 2.5;
        int k = 5;
        System.out.printf("%.3f", poissonDistribution(lambda, k));
    }
	
	public static double poissonDistribution(double lambda, int k) {
		final double e = 2.71828;
		return Math.pow(lambda, k) / (Math.pow(e, lambda) * factorial(k));
	}
	
	public static int factorial(int n) {
		int result = 1;
		while(n > 1) {
			result *= n--;
		}
		return result;
	}
}
