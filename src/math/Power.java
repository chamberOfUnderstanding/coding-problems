package math;

/**
 * @author 47un
 *
 * Implement the power operation using only addition
 * 
 * http://www.geeksforgeeks.org/write-you-own-power-without-using-multiplication-and-division/
 * http://www.geeksforgeeks.org/write-a-c-program-to-calculate-powxn/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : 5^4 = 5 * 5^3  // Recursion to calculate power
 *            5*3 = 5 + 5*2  // Recursion to calculate product
 *
 * TIME     : O(n)
 * SPACE    : O(n)  // Tail recursion applies only to the multiply method
 *
 * METHOD 2 : Use divide and conquer
 *            Calculate x^(n/2)
 *            If n is even, do x^(n/2) * x^(n/2)
 *            If n is odd, do x * x^(n/2) * x^(n/2)
 *            
 * TIME     : O(logn)
 * SPACE    : O(1)
 * 
 * 
 */

public class Power {
    
    public static void main(String[] args) {
        int base = 9;
        int exponent = 3;
        System.out.println(power(base, exponent));
        System.out.println(powerOptimized(base, exponent));
    }

    private static int power(int base, int exponent) {
        return exponent == 0 ? 1 : multiply(base, power(base, exponent - 1));
    }

    private static int multiply(int number, int multiplier) {        
        return multiplier == 0 ? 0 : number + multiply(number, multiplier - 1);
    }
    
    private static int powerOptimized(int base, int exponent) {
       if(exponent == 0)
           return 1;
       int halfExponent = powerOptimized(base, exponent/2);
       return halfExponent * halfExponent * (exponent%2 == 0? 1 : base);
    }
}
