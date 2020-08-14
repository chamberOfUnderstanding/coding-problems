package string;

/**
 * @author 47un
 *
 * Generate all strings obtained by adding a space after characters in the given string
 * There can be either no space or one space between characters
 * e.g.
 *  abcd
 *  abc d
 *  ab cd
 *  ab c d
 *  a bcd
 *  a bc d
 *  a b cd
 *  a b c d
 * 
 * http://www.geeksforgeeks.org/print-possible-strings-can-made-placing-spaces/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Recursion
 *            Maintain a buffer
 *            Start from index 0
 *              If the index = size of input string, print the buffer and exit
 *              Include the character at 'index' of input in the buffer and recurse
 *              If the index is not 0, add a space and include the character at 'index' and recurse
 *                 Coz we dont want the permutations where space is the first character
 *
 * TIME     : O(2 ^ number of slots for space insertion)
 * SPACE    : O(1)
 *
 * 
 */

public class Space_Separated_Permutations {

    public static void main(String[] args) {
        String input = "abcd";
        generateSpaceSeparatedPermutations("", input, 0);
    }

    private static void generateSpaceSeparatedPermutations(String buffer, String input, int index) {
        if(index == input.length()) {
            System.out.println(buffer);
            return;
        }
        generateSpaceSeparatedPermutations(buffer + input.charAt(index), input, index + 1);
        if(index != 0)
            generateSpaceSeparatedPermutations(buffer + " " + input.charAt(index), input, index + 1);
    }
}
