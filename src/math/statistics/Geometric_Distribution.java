package math.statistics;

public class Geometric_Distribution {

	public static void main(String[] args) {
        double p = (double)1/3;
        double q = 1-p;
        int n = 5;
        System.out.printf("%.3f", geometricDistribution(p, q, n));
    }

    public static double geometricDistribution(double p, double q, int n) {
        return Math.pow(q, n - 1) * p;
    }
    
}
