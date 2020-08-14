package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Convert prefix expression to infix expression
 * 
 * http://scanftree.com/Data_Structure/prefix-to-infix
 * 
 * =================
 * METHOD 1 : Stack
 * =================
 * Scan the expression IN REVERSE
 *  If the character is an operand, push it to stack
 *  If the character is an operator
 *   If the stack has < 2 items in it, quit!
 *   Else pop 2 operands from the stack, stick the operator between them and push the result back in
 *    While doing this, first pop should go to right, so pop the stack and save the value
 *  If the stack has > 1 item, quit!
 * Pop the stack and reverse the string to get the result
 *  While reversing, make sure the brackets are flipped as well ( to ) and ) to (
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Prefix_To_Infix {

    public static void main(String[] args) {
        String prefix = "*+a-bc/-de+-fgh";
        System.out.println(prefixToInfix(prefix));
    }

    private static String prefixToInfix(String prefix) {        
        Stack<String> stack = new Stack<>();
        int i = prefix.length() - 1;
        while(i >= 0) {
            char postfixCharacter = prefix.charAt(i--);
            if(isOperator(postfixCharacter)) {
               if(stack.size() < 2)
                   quit();
               String operand = stack.pop();
               stack.push("(" + stack.pop() + postfixCharacter + operand + ")");   
            }
            else
                stack.push("" + postfixCharacter);
        }
        if(stack.size() != 1)
            quit();        
        return reverse(stack.pop());
    }

    private static String reverse(String infix) {
        StringBuilder infixStringBuilder = new StringBuilder(infix);
        for(int low = 0, high = infix.length() - 1; low <= high; low++, high--) {
            char charAtLow  = infixStringBuilder.charAt(low);
            char charAtHigh = infixStringBuilder.charAt(high);
            charAtLow  += charAtLow  == '('? 1 : charAtLow  == ')'? -1 : 0;
            charAtHigh += charAtHigh == '('? 1 : charAtHigh == ')'? -1 : 0;
            infixStringBuilder.setCharAt(low, charAtHigh);            
            infixStringBuilder.setCharAt(high, charAtLow);            
        }
        return infixStringBuilder.toString();
    }
    
    private static boolean isOperator(char postfixCharacter) {
        return  postfixCharacter == '^' ||
                postfixCharacter == '*' ||
                postfixCharacter == '/' ||
                postfixCharacter == '+' ||
                postfixCharacter == '-';
    }
    
    private static void quit() {
        System.out.println("Invalid expression");
        System.exit(1);
    }
}
