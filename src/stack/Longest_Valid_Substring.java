package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Given a string consisting of opening and closing parenthesis, find length of the longest valid parenthesis substring.
 * 
 * http://www.geeksforgeeks.org/length-of-the-longest-valid-substring/
 * 
 * ================
 * METHOD 1 : Stack
 * ================
 * Push indices of (
 * If a ) is seen, pop, and if the stack is not empty, that means there's a balanced expression, so calculate length
 * If ) is seen at start or a continuous stream of ), only the last ) will be available in stack
 * -1 provides as base for the first pop when ) is seen, after that the ) gets pushed and it provides as base for the next )
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * ====================
 * METHOD 2 : Iteration
 * ====================
 * If ( increase the count of opening brackets
 * If ) and opening brackets count > 0, pair has been detected.
 *  So opening brackets reduces by 1 (as it has been paired)
 *  And current streak increases by 2
 * If ) and no opening brackets have been seen
 *  Update longestStreak, reset opening brackets and streak to 0 
 * The second update right after the loop is necessary to handle cases where # ( is greater than )
 *  e.g. ((() 
 *  
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Longest_Valid_Substring {

    public static void main(String[] args) {
        String string1 = "((()";
        String string2 = ")()())";
        String string3 = "()(()))))";
        System.out.println("Method I : Stack");
        System.out.println("Longest valid substring of " + string1 + " is " + longestValidSubstringI(string1));
        System.out.println("Longest valid substring of " + string2 + " is " + longestValidSubstringI(string2));
        System.out.println("Longest valid substring of " + string3 + " is " + longestValidSubstringI(string3));
        System.out.println("\nMethod II : Iteration");
        System.out.println("Longest valid substring of " + string1 + " is " + longestValidSubstringII(string1));
        System.out.println("Longest valid substring of " + string2 + " is " + longestValidSubstringII(string2));
        System.out.println("Longest valid substring of " + string3 + " is " + longestValidSubstringII(string3));
    }

    private static int longestValidSubstringI(String string) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        
        // ensures stack is never empty
        stack.push(-1);
        for(int i = 0; i < string.length(); i++)
            // push opening bracket indices right away
            if(string.charAt(i) == '(')
                stack.push(i);
            else {
                // pop it, mostly it's an opening bracket
                stack.pop();
                // if the stack aint empty, then some match has occured, calculate the length using i - top most index on stack
                if(!stack.isEmpty())
                    result = Math.max(result, i - stack.peek());
                else
                    stack.push(i);
            }
        return result;
    }

    private static int longestValidSubstringII(String string) {
        int openingBrackets = 0;
        int longest = Integer.MIN_VALUE;
        int streak = 0;
        for(int i = 0; i < string.length(); i++)
            if(string.charAt(i) == '(')
                openingBrackets++;
            else
                // if there are any ( to match with, we have a streak
                if(openingBrackets > 0) {
                    openingBrackets--;
                    streak += 2;
                }
                // if there is none, record the streak and reset it to 0
                else {
                    longest = Math.max(longest, streak);
                    openingBrackets = 0;
                    streak = 0;
                }
        // if there're any ( left out, then calculate streak again
        if(openingBrackets >= 0)
            longest =  Math.max(longest, streak);
        return longest;
    }
}
