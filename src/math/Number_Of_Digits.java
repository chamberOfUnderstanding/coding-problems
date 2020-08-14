package math;

/**
 * @author 47un
 *
 * Find the number of digits in a number 
 * 
 * TODO
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : This is the best way to do this
 *            Keep comparing the number of 10th power
 *            If this number is < that power, the exponent is the number of digits
 *            Else increase the exponent
 *
 * TIME     : O(d) // d is the number of digits
 * SPACE    : O(1)
 *
 * 
 */

public class Number_Of_Digits {
    public static void main(String[] args) {
        int number = 299120023;
        System.out.println(new Number_Of_Digits().findNumberOfDigits(number));
    }

    private int findNumberOfDigits(int number) {
        int digits = 0;
        while(true)
            if(number < Math.pow(10, ++digits))
                return digits;
    }
}
