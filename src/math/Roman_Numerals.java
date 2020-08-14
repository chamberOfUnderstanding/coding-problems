package math;

/**
 * @author 47un
 *
 * Given a number, find it's Roman numeral.
 *  I       1
 *  IV      4
 *  V       5
 *  IX      9
 *  XL     40
 *  L      50
 *  XC     90
 *  C     100
 *  CD    400
 *  D     500
 *  DM    900
 *  M    1000 
 *  
 *  4,9,40,90,400,900 look absurd to avoid 4 of the same symbols coming together
 *  4 = 5 - 1, so I before V
 * 
 * http://www.geeksforgeeks.org/converting-decimal-number-lying-between-1-to-3999-to-roman-numerals/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Conditions
 *  
 *            Just divide by a base and append the symbols to the output
 *
 * TIME     : O(sum of the % values)    // Ends up linear anyways
 * SPACE    : O(1)
 *
 * 
 */

public class Roman_Numerals {

    public static void main(String[] args) {
        int number = 3794;
        System.out.println(getRoman(number));
    }

    private static String getRoman(int number) {
        StringBuilder roman = new StringBuilder();
        if(number >= 1000) {
            for(int i = number/1000; i != 0; i--)
                roman.append('M');
            number %= 1000;
        }
        if(number >= 900) {
            roman.append("CM");
            number %= 900;
        }
        if(number >= 500) {
            roman.append('D');
            number %= 500;
        }
        if(number >= 400) {
            roman.append("CD");
            number %= 400;
        }
        if(number >= 100) {
            for(int i = number/100; i != 0; i--)
                roman.append('C');
            number %= 100;
        }
        if(number >= 90) {
            roman.append("XC");
            number %= 90;
        }
        if(number >= 50) {
            roman.append('L');
            number %= 50;
        }
        if(number >= 40) {
            roman.append("XL");
            number %= 40;
        }
        if(number >= 10) {
            for(int i = number/10; i != 0; i--)
                roman.append('X');
            number %= 10;
        }
        if(number >= 9) {
            roman.append("IX");
            number %= 9;
        }
        if(number >= 5) {
            roman.append('V');
            number %= 5;
        }
        if(number >= 4) {
            roman.append("IV");
            number %= 4;
        }
        for(int i = number; i != 0; i--)
            roman.append('I');
        return roman.toString();
    }
}
