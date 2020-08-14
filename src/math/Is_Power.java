package math;

/**
 * @author 47un
 * 
 * Given a positive integer n, find if it can be expressed as x ^ y.
 * Where y > 1 and x > 0 and x and y both are both integers
 * 
 * =========
 * METHOD 1
 * =========
 * If the number is 1, return true
 * Since the exponent must be > 1, start from 2 and go up till number/2
 *  If there is a base and exponent that can give this number, it should be within this range
 * Now the base can go up from i to number/i
 *  The nth root of a number is always < number/n  
 * Find the power, if it is =, return, if it is > number, move to the next exponent
 * 
 * TIME     : O(<n)
 * SPACE    : O(1)
 * 
 */

public class Is_Power {

    public static void main(String[] args) {
        int number = 15625;
        System.out.println(isPowerable(number));
    }

    private static boolean isPowerable(int number) {
        if(number == 1)
            return true;
        for(int exponent = 2; exponent <= number/2; exponent++)
            for(int base = 2; base <= number/exponent; base++) {
                int power = (int) Math.pow(base, exponent); 
                if(power == number)
                    return true;
                else if(power > number)
                    break;
            }
        return false;
    }
}
