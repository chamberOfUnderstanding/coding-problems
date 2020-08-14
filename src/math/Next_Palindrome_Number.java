package math;

/**
 * @author 47un
 * 
 * Given a number, find the next palindrome number
 * 
 * METHOD 1 : Get the index of the middle character
 *              Math.ceil(5/2)   => 2 and not 3 coz 5/2 is integer division
 *              Math.ceil(5/2.0) => 3 as 5/2.0 is float division
 *            Set carry as 1 (Add 1 to the middle and propagate the carry)
 *            Scan from the middle character to the start
 *              Get the numeric value of the character, add the carry, get the new digit (%10)
 *                  Since only 1 is added, carry will be generated for 9s alone
 *              Update the carry
 *              Get the character value of the new digit
 *              Set the current index and the index that lies at the same distance from the right end as this digit
 *            If the carry is 1 (Happens for numbers that are all 9s)
 *              Add a 1 to the front and back of the result
 *              Delete the middle character (Which is an extra 0)
 *
 * TIME     : O(n)  // n/2 to be precise
 * SPACE    : O(1)  // Two strings though
 * 
 */

public class Next_Palindrome_Number {

    public static void main(String[] args) {
        int case1 = 1221;       
        int case2 = 979;        
        int case3 = 99;         
        int case4 = 999;        
        int case5 = 1991;       
        int case6 = 41914;      
        int case7 = 123999321;
        int case8 = 29997;
        int case9 = 96574;
        int case10 = 16975;
        System.out.println(case1 + " : " + nextPalindromeNumber(case1));        
        System.out.println(case2 + " : " + nextPalindromeNumber(case2));        
        System.out.println(case3 + " : " + nextPalindromeNumber(case3));        
        System.out.println(case4 + " : " + nextPalindromeNumber(case4));        
        System.out.println(case5 + " : " + nextPalindromeNumber(case5));        
        System.out.println(case6 + " : " + nextPalindromeNumber(case6));        
        System.out.println(case7 + " : " + nextPalindromeNumber(case7));        
        System.out.println(case8 + " : " + nextPalindromeNumber(case8));        
        System.out.println(case9 + " : " + nextPalindromeNumber(case9));        
        System.out.println(case10 + " : " + nextPalindromeNumber(case10));        
    }

    private static int nextPalindromeNumber(int input) {
        StringBuilder number = new StringBuilder(Integer.toString(input));
        int numberOfDigits   = number.length();
        int middleIndex      = (int) (Math.ceil(numberOfDigits/2.0) - 1);
        int carry = 1;
        for(int i = middleIndex; i >= 0; i--) {
            int intNewDigit = (Character.getNumericValue(number.charAt(i)) + carry) % 10;
            carry = intNewDigit == 0? 1 : 0;
            char charNewDigit = Character.forDigit(intNewDigit, 10);
            number.setCharAt(i, charNewDigit);
            number.setCharAt(numberOfDigits - i - 1, charNewDigit);
        }
        if(carry == 1) {
            number.insert(0, '1');
            number.deleteCharAt(middleIndex + 1);
            number.insert(number.length(), '1');
        }
        return Integer.parseInt(number.toString());
    }
}
