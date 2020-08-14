package math;

/**
 * @author 47un
 * 
 * Given a number d, representing the number of digits of a number.
 * Find the total count of positive integers which have at-least one zero in them and consist d or less digits
 * 
 * http://www.geeksforgeeks.org/count-positive-integers-0-digit/
 * http://www.geeksforgeeks.org/count-d-digit-positive-integers-with-0-as-a-digit/
 * 
 * =========
 * METHOD 1
 * =========
 * Following are some observations:
 * 
 *      MSP             LSP
 *       ^               ^
 *       |               |
 *       
 *     | D1 | D2 | D3 | D4 |
 *     
 * There are exactly d digits.
 * The number at most significant place can’t be a zero (D1 cant be 0 as no leading zeroes allowed).
 * All the other places except the most significant one can contain zeroes.
 * 
 * So considering the above points, let’s find the total count of numbers having d digits:
 * 
 * We can place any of {1, 2, ... 9} in D1. Hence D1 can be filled in 9 ways.
 * 
 * Apart from D1 all the other places can be  10 ways as we can place 0 as well.
 * Hence the total numbers having d digits can be given as: 
 * Total =  9 * (10 ^ (d - 1))
 * 
 * Now, let's find the numbers having d digits, that don't contain zero at any place. 
 * In this case, all the places can be filled in 9 ways.
 * Hence count of such numbers is given by:
 * Non_Zero = 9 ^ d = 9 * (9 ^ (d - 1))
 * 
 * Now the count of numbers having at least one zero can be obtained by subtracting Non_Zero from Total.
 * Hence Answer would be given by:
 * 
 * Result = 9 * (10 ^ (d - 1) - 9 ^ (d - 1)) 
 * 
 * Each of the above terms is a GP
 * Sum of first n terms of a GP, a, ar, ar^2, ar^3 .... ar^(n - 1) is => a * (r^n - 1) / (r - 1)
 * 
 * i'th term of G.P. 1 = 9 * ((10 ^ i) - 1) where 1 <= i <= d
 * i'th term of G.P. 2 = 9 * (( 9 ^ i) - 1) where 1 <= i <= d
 * 
 * The final answer is nothing but,
 *    
 * Sum of G.P 1 = 9 * ((10 ^ d) - 1) / (10 - 1) 
 *              = 9 * ((10 ^ d) - 1) / 9
 * 
 * Sum of G.P 2 = 9 * ((9 ^ d) - 1) / (9 - 1) 
 *              = 9 * ((9 ^ d) - 1) / 8 
 * 
 * TIME     : O(1)
 * SPACE    : O(1)
 *
 * 
 */

public class Positive_Numbers_With_Zeroes {

    public static void main(String[] args) {
        int numberOfDigits = 4;
        System.out.println(countPositiveNumbersWithZeroes(numberOfDigits));
    }

    private static long countPositiveNumbersWithZeroes(int numberOfDigits) {
        return 9 * (sumOfGP(10, numberOfDigits) - sumOfGP(9, numberOfDigits));
    }

    private static long sumOfGP(int r, int n) {
        return ((long)Math.pow(r, n) - 1) / (r - 1);
    }
}
