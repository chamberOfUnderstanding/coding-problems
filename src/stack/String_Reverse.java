package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Reverse a string using a stack
 * 
 * http://quiz.geeksforgeeks.org/stack-set-3-reverse-string-using-stack/
 *
 * =========
 * METHOD 1
 * =========
 * Push each character into stack
 * Pop it into a new string
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class String_Reverse {

    public static void main(String...strings){
        String input = "I need to get myself a life";
        System.out.println("\nReversed : " + reverse(input));
    }

    private static String reverse(String input) {
        char[] reversedString = new char[input.length()];
        int i = 0;
        Stack<Character> stack = new Stack<>();
        for(Character character : input.toCharArray())
            stack.push(character);
        while(!stack.isEmpty())
            reversedString[i++] = stack.pop();
        return new String(reversedString);
    }
}
