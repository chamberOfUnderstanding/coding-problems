package math;

/**
 * @author 47un
 * 
 * Check whether the given binary number is a multiple of 3
 * 
 * http://www.geeksforgeeks.org/write-an-efficient-method-to-check-if-a-number-is-multiple-of-3/
 * 
 * =========
 * METHOD 1
 * =========
 * Count the number of 1s at odd positions 
 * Count the number of 1s at even positions
 * If the difference of the above two is a multiple of 3, then the number is divisible by 3 too 
 * 
 * TIME     : O(d)  // d = number of digits
 * SPACE    : O(1)
 *
 * 
 */

public class Is_Binary_Number_A_Multiple_Of_Three {

    public static void main(String[] args) {
        long number = 100;
        System.out.println(isDivisibleByThree(number));
    }

    private static boolean isDivisibleByThree(long number) {
        if(number == 0)
            return true;
        number = Math.abs(number);
        if(number == 1)
            return false;
        int onesAtOddPositions  = 0;
        int onesAtEvenPositions = 0;
        while(number != 0) {
            if(number % 10 == 1)
                onesAtOddPositions++;
            number /= 10;
            if(number % 10 == 1)
                onesAtEvenPositions++;
            number /= 10;
        }
        return (onesAtOddPositions - onesAtEvenPositions) % 3 == 0;
    }
}
