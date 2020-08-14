package string;

/**
 * @author 47un
 * 
 * Given a string of character, find the length of longest proper prefix which is also a proper suffix.
 * Example:S = abab
 * Result is 2 because, ab.. is prefix and ..ab is also a suffix.
 * 
 * =========
 * METHOD 1
 * =========
 * 
 * 
 * 
 * 
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Longest_Prefix_Suffix {

    public static void main(String[] args) {
        String input = "abxxasdasdascabxxas";
        System.out.println(findLongestPrefixSuffix(input));
    }

    private static int findLongestPrefixSuffix(String input) {
        int longestPrefixSuffix = 0;
        int middleIndex = input.length() / 2;
        char firstCharacter = input.charAt(0);
        char lastCharacter = input.charAt(input.length() - 1);
        int i = middleIndex;
        int j = middleIndex;
        while(i >= 0 && input.charAt(i) != lastCharacter)
            i--;
        while(j < input.length() && input.charAt(j) != firstCharacter)
            j++;
        while(i >= 0 && j < input.length()) {
            longestPrefixSuffix++;
            i--;
            j++;
        }
        return longestPrefixSuffix;
    }
}
