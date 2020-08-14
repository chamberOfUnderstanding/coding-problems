package string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 * 
 * Given the Morse code for a word, find out the number of words of same length that can be formed using the characters of this Morse Code 
 * 
 * 
 * METHOD 1 :  A valid Morse code can be of length 1,2,3 or 4. 
 *             Find out the number of ways of achieving the given code length using 1,2,3,4 and with just input.length() slots available.
 *             For each way, check if all the numbers are valid morse codes, if yes increase count
 *             e.g. Input string is of length 3
 *                  Morse code of input is of length 10
 *                  Find out the number of ways in which a sum of 10 can be achieved by using any 3 among 1,2,3,4
 *                  For every such combination, lookup the morse code table and see if they represent valid characters
 *                  10 = 2,2,4 (See if the first 2 letters form a character, second 2 then last 4, if they all do, then this is a valid string) 
 *             
 * TIME     : O(4^n) // n is the length of the input string
 * SPACE    : O(4^n)
 *
 * 
 */

public class Morse_Code {

    public static final Map<String, Character> MORSE_TO_LETTER = new HashMap<>();
    public static final Map<Character, String> LETTER_TO_MORSE = new HashMap<>();

    public static void main(String[] args) {
        String input = "dank";
        StringBuilder morseCode = new StringBuilder();
        for(int i = 0; i < input.length(); i++)
            morseCode.append(LETTER_TO_MORSE.get(Character.toLowerCase(input.charAt(i))));
        System.out.println("Input : " + input);
        System.out.println("Morse : " + morseCode);
        count = 0;
        findPossibleStrings(0, morseCode.length(), 0, input.length(), morseCode.toString());
        System.out.println(count);
    }

    static int count = 0;

    private static void findPossibleStrings(int startIndex, int targetSum, int currentSum, int slots, String morseCode) {
        if(slots == 0) {
            if(currentSum == targetSum)
                count++;
            return;
        }
        if(currentSum > targetSum)
            return;
        if(targetSum > 1 && startIndex + 1 <= morseCode.length() && MORSE_TO_LETTER.containsKey(morseCode.substring(startIndex, startIndex + 1)))
            findPossibleStrings(startIndex + 1, targetSum, currentSum + 1, slots - 1, morseCode);
        if(targetSum > 2 && startIndex + 2 <= morseCode.length() && MORSE_TO_LETTER.containsKey(morseCode.substring(startIndex, startIndex + 2)))
            findPossibleStrings(startIndex + 2, targetSum, currentSum + 2, slots - 1, morseCode);
        if(targetSum > 3 && startIndex + 3 <= morseCode.length() && MORSE_TO_LETTER.containsKey(morseCode.substring(startIndex, startIndex + 3)))
            findPossibleStrings(startIndex + 3, targetSum, currentSum + 3, slots - 1, morseCode);
        if(targetSum > 4 && startIndex + 4 <= morseCode.length() && MORSE_TO_LETTER.containsKey(morseCode.substring(startIndex, startIndex + 4)))
            findPossibleStrings(startIndex + 4, targetSum, currentSum + 4, slots - 1, morseCode);
    }

    static {
        MORSE_TO_LETTER.put(".-", 'a');
        MORSE_TO_LETTER.put("-...", 'b');
        MORSE_TO_LETTER.put("-.-.", 'c');
        MORSE_TO_LETTER.put("-..", 'd');
        MORSE_TO_LETTER.put(".", 'e');
        MORSE_TO_LETTER.put("..-.", 'f');
        MORSE_TO_LETTER.put("--.", 'g');
        MORSE_TO_LETTER.put("....", 'h');
        MORSE_TO_LETTER.put("..", 'i');
        MORSE_TO_LETTER.put(".---", 'j');
        MORSE_TO_LETTER.put("-.-", 'k');
        MORSE_TO_LETTER.put(".-..", 'l');
        MORSE_TO_LETTER.put("--", 'm');
        MORSE_TO_LETTER.put("-.", 'n');
        MORSE_TO_LETTER.put("---", 'o');
        MORSE_TO_LETTER.put(".--.", 'p');
        MORSE_TO_LETTER.put("--.-", 'q');
        MORSE_TO_LETTER.put(".-.", 'r');
        MORSE_TO_LETTER.put("...", 's');
        MORSE_TO_LETTER.put("-", 't');
        MORSE_TO_LETTER.put("..-", 'u');
        MORSE_TO_LETTER.put("...-", 'v');
        MORSE_TO_LETTER.put(".--", 'w');
        MORSE_TO_LETTER.put("-..-", 'x');
        MORSE_TO_LETTER.put("-.--", 'y');
        MORSE_TO_LETTER.put("--..", 'z');

        for(String morse : MORSE_TO_LETTER.keySet())
            LETTER_TO_MORSE.put(MORSE_TO_LETTER.get(morse), morse);
    }
}