package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Convert postfix expression to an infix expression
 * 
 * http://scanftree.com/Data_Structure/postfix-to-infix
 * 
 * =================
 * METHOD 1 : Stack
 * =================
 * Scan the expression
 *  If the character is an operand, push it to stack
 *  If the character is an operator
 *   If the stack has < 2 items in it, quit!
 *   Else pop 2 operands from the stack, stick the operator between them and push the result back in
 *    While doing this, first pop should go to right, so pop the stack and save the value
 *  If the stack has < 2 items, quit!
 *  Pop the stack to get the result
 * 
 * TIME     : O(n)
 * SPACE    : O(n)  // It does create a crap ton of strings though
 *
 * 
 */

public class Postfix_To_Infix {

    public static void main(String[] args) {
        String postfix = "abc-+de-fg-h+/*";
        System.out.println(postfixToInfix(postfix));
    }

    private static String postfixToInfix(String postfix) {
        Stack<String> stack = new Stack<>();
        int i = 0;
        while(i < postfix.length()) {
            char postfixCharacter = postfix.charAt(i++);
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
        return stack.pop();
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
