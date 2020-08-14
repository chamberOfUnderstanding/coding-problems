package string;

/**
 * @author 47un
 * 
 * Given a binary string, count number of substrings that start and end with 1.
 * For example, if the input string is “00100101”, then there are three substrings “1001”, “100101” and “101” 
 * 
 * http://www.geeksforgeeks.org/given-binary-string-count-number-substrings-start-end-1/
 * 
 * =========
 * METHOD 1
 * =========
 * # 1s in the string       # Sub Strings
 * ------------------       -------------
 *         0                      1
 *         1                      3
 *         2                      6
 *         3                     10
 *         4                     15
 *         5                     21  
 * Notice the pattern.
 * If the number of 1s in the string is i, then the number of sub strings is = Sum of natural numbers till i -1
 *  i.e. (i * (i -1))/2
 * 
 * Example : 1011011
 * 
 * 0 1 2 3 4 5 6
 * -------------
 * 1 0 1 1 0 1 1
 * 
 * Number of 1s = 5
 * 
 * Substrings that start with the 1 at index 0
 * 101, 1011, 101101, 1011011 => 4
 * Substrings that start with the 1 at index 2
 * 11, 1101, 11011            => 3
 * Substrings that start with the 1 at index 3
 * 101, 1011                  => 2
 * Substrings that start with the 1 at index 5
 * 11                         => 1
 * Substrings that start with the 1 at index 6
 * None                       => 0
 * 
 * Add them up => 4 + 3 + 2 + 1 + 0 => 10 = (4 * 5)/2
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Binary_Strings {

    public static void main(String[] args) {
        String binaryNumber = "00100101";
        System.out.println(findSubStrings(binaryNumber));
    }

    private static int findSubStrings(String binaryNumber) {
        int oneCount = 0;
        for(int i = 0; i < binaryNumber.length(); i++)
            if(binaryNumber.charAt(i) == '1')
                oneCount++;        
        return oneCount == 0? 0 : (oneCount * (oneCount - 1)) >> 1;
    }
}
