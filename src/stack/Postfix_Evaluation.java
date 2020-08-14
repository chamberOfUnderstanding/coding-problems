package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Evaluate the given postfix expression
 * 
 * http://quiz.geeksforgeeks.org/stack-set-4-evaluation-postfix-expression/
 * 
 * ================
 * METHOD 1 : Stack
 * ================
 * Scan the expression
 * Push the character if it's an operand
 * Else check if there're 2 items on stack to operate with, else quit!
 * If there are >=2 items on stack, pop them out, operate on them, push the result back in
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Postfix_Evaluation {

    public static void main(String...strings){
        String postfix = "231*+9-";
        System.out.println(postfix + " upon evaluation gives " + evaluatePostfix(postfix));
    }

    private static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < postfix.length(); i++){
            char currentCharacter = postfix.charAt(i);
            if(isOperand(currentCharacter))
                stack.push(Character.getNumericValue(currentCharacter));
            else{
                if(stack.size() < 2)
                    quit();
                int	operand1 = stack.pop();
                int operand2 = stack.pop();
                int result = 0;
                switch(currentCharacter){
                case '+' : result = operand2 + operand1; break;
                case '-' : result = operand2 - operand1; break;
                case '*' : result = operand2 * operand1; break;
                case '/' : result = operand2 / operand1; break;
                case '^' : result = (int) Math.pow(operand2, operand1); break;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    private static boolean isOperand(Character character) {		
        return '0' <= character && character <= '9';
    }

    private static void quit() {
        System.out.println("Invalid expression");
        System.exit(1);
    }
}
