package stack;

import java.util.Stack;

/**
 * @author 47un
 *
 * Given an expression with only ‘}’ and ‘{‘.
 * The expression may not be balanced.
 * Find minimum number of bracket reversals to make the expression balanced.
 * 
 * http://www.geeksforgeeks.org/minimum-number-of-bracket-reversals-needed-to-make-an-expression-balanced/
 * 
 * =======================
 * METHOD 1 : Stack
 * =======================
 * If the expression is of odd length, it cannot be balanced
 * Remove the balanced part of the expression using a stack as follows
 *    If the bracket is an opening bracket, push it
 *    Else if it's a closing bracket, peek the stack for an opening bracket
 *      If found, pop it
 *      Else push the closing bracket in (Push happens if the stack is empty too)
 * Now the stack contains the imbalanced part of the expression
 * Get the reduced stack size (size of the imbalanced part)
 * Empty the stack and count the number of opening brackets
 * Number of reversals needed = imbalanced part size /2 + opening brackets % 2
 *    Since we have to reverse atleast half of the imbalanced part, imbalanced/2 is done
 *    If the opening brackets in the imbalanced part are odd numbered, we have to reverse one more hence the %2
 *
 * TIME   : O(n)
 * SPACE  : O(n)
 * 
 * =====================
 * METHOD 2 : Iterative
 * =====================
 * If the expression is of odd length, it cannot be balanced
 * Scan the expression 
 *   If the bracket is opening, increase count of opening brackets
 *   Else if the bracket is closing,
 *       If at least one opening bracket has been seen so far, decrease the count of the opening bracket, as this counts as a match/balance
 *       If no opening bracket has been seen so far, this bracket needs a reversal
 *          Hence increase reversals. Since this } just got reversed to a {, the number of opening brackets increase
 * Number of reversals = reversals + opening/2
 *   As half the number of opening brackets need reversing
 * 
 * TIME   : O(n)
 * SPACE  : O(1)
 *                        
 */

public class Bracket_Reversals {

    public static void main(String[] args) {
        String expression1 = "{{{";
        String expression2 = "{{{{";
        String expression3 = "{{{{}}";
        String expression4 = "}{{}}{{{";
        Bracket_Reversals bracket_Reversals = new Bracket_Reversals();

        System.out.println("Method 1 : Stack");
        int reversals = bracket_Reversals.getReversalsNeeded(expression1);
        System.out.println(expression1 + (reversals == -1? " cant be balanced" : " needs " + reversals + " reversals"));
        reversals = bracket_Reversals.getReversalsNeeded(expression2);
        System.out.println(expression2 + (reversals == -1? " cant be balanced" : " needs " + reversals + " reversals"));
        reversals = bracket_Reversals.getReversalsNeeded(expression3);
        System.out.println(expression3 + (reversals == -1? " cant be balanced" : " needs " + reversals + " reversals"));
        reversals = bracket_Reversals.getReversalsNeeded(expression4);
        System.out.println(expression4 + (reversals == -1? " cant be balanced" : " needs " + reversals + " reversals"));

        System.out.println("\nMethod 2 : Iteration");
        reversals = bracket_Reversals.getReversalsNeededII(expression1);
        System.out.println(expression1 + (reversals == -1? " cant be balanced" : " needs " + reversals + " reversals"));
        reversals = bracket_Reversals.getReversalsNeededII(expression2);
        System.out.println(expression2 + (reversals == -1? " cant be balanced" : " needs " + reversals + " reversals"));
        reversals = bracket_Reversals.getReversalsNeededII(expression3);
        System.out.println(expression3 + (reversals == -1? " cant be balanced" : " needs " + reversals + " reversals"));
        reversals = bracket_Reversals.getReversalsNeededII(expression4);
        System.out.println(expression4 + (reversals == -1? " cant be balanced" : " needs " + reversals + " reversals"));
    }		

    private int getReversalsNeeded(String expression) {		
        if(isOddLength(expression))
            return -1;
        Stack<Character> stack = new Stack<>();
        for(int bracket = 0; bracket < expression.length(); bracket++)
            switch(expression.charAt(bracket)){
            case '{' : 
                stack.push('{');
                break;
            case '}' : 
                if(!stack.isEmpty() && stack.peek() == '{')
                    stack.pop();
                else
                    stack.push('}');
            }
        int imbalancedPart = stack.size();
        int openingBrackets = 0;
        while(!stack.isEmpty() && stack.peek() == '{'){
            stack.pop();
            openingBrackets++;
        }
        return imbalancedPart/2 + openingBrackets%2;
    }

    private int getReversalsNeededII(String expression){
        if(isOddLength(expression))
            return -1;
        int openingBrackets = 0;
        int reversals = 0;
        for(int i = 0; i < expression.length(); i++)
            switch(expression.charAt(i)){
            case '{' :
                openingBrackets++;
                break;
            case '}' :
                if(openingBrackets > 0)
                    openingBrackets--;
                else{
                    reversals++;
                    openingBrackets++;
                }					
            }
        return reversals + openingBrackets/2;
    }

    private boolean isOddLength(String expression) {
        return expression.length() % 2 != 0;
    }
}
