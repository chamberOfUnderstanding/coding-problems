package math;

/**
 * @author 47un
 *
 * Given an integer x, find square root of it. If x is not a perfect square, then return floor(sqrt(x)).
 * 
 * http://www.geeksforgeeks.org/square-root-of-an-integer/
 * 
 * ========================
 * METHOD 1 : Binary Search
 * ======================== 
 * The square root of any number, x, is always < x/2
 * Take low = 1 and high = x/2
 * As long as low <= high
 * Find mid
 *  If mid * mid == x, happens for perfect squares, return mid
 *  Else if mid * mid < x, we need a higher mid value, so move right by setting low as mid + 1, save this mid value in a variable, result
 *  Else if mid * mid > x, we need a lower mid value, so move left by setting high as mid - 1
 * Return result
 *             
 * TIME    : O(logn)
 * SPACE   : O(1)
 * 
 * ==================================
 * METHOD 2 : Binary Search (Floats)
 * ================================== 
 * For floats/doubles,
 *  Start with low at 1 and high = number/n , where n is the nth root you wish to find
 *    precision determines how many accurate decimal places should be present in the result 
 *  Find mid as average of low and high (Bit wise operators DO NOT work for float, so you cant >> to divide by 2)
 *  Calculate error for this mid value
 *   Error is how far away the mid value is from the actual result
 *   To find this, calculate mid^n and compare it with the number. Their difference is the error 
 *  If the error is <= precision, then return mid
 *  Else if mid^n < number, we need to go lower, so high = mid
 *  Else if mid^n > number, we need to go higher, so low = mid
 * 
 * TIME   : O(logn)
 * SPACE  : O(1)
 * 
 */

public class Roots {

    public static void main(String[] args) {
        int number = 145;
        System.out.println("Square root of " + number  + " is " + findSquareRoot(number));
        System.out.println("Cube root of " + number  + " is " + findCubeRoot(number));
    }

    private static double findSquareRoot(int number) {
        double low  = 1;
        double high = number/2;
        double precision = 0.00000001;
        while(true) {
            double mid = (low + high)/2;
            double error = calculateErrorInSquareRoot(number, mid);
            if(error <= precision)
                return mid;
            else if(mid * mid < number)
                low = mid;
            else
                high = mid;
        }
    }

    private static double findCubeRoot(int number) {
        double low  = 1;
        double high = number/3;
        double precision = 0.00000001;
        while(true) {
            double mid = (low + high)/2;
            double error = calculateErrorInCubeRoot(number, mid);
            if(error <= precision)
                return mid;
            else if(mid * mid * mid < number)
                low = mid;
            else
                high = mid;
        }
    }

    private static double calculateErrorInSquareRoot(int number, double mid) {
        return Math.abs(number - mid * mid);
    }
    
    private static double calculateErrorInCubeRoot(int number, double mid) {
        return Math.abs(number - mid * mid * mid);
    }

}
