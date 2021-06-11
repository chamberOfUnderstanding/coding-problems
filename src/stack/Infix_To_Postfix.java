package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author 47un
 * 
 * Infix expression  : The expression of the form a op b. When an operator is in-between every pair of operands.
 * Postfix expression: The expression of the form a b op. When an operator is followed for every pair of operands.
 * 
 * The compiler scans the expression either from left to right or from right to left.
 * Consider the below expression: a + b * c + d
 *  The compiler first scans the expression to evaluate b * c, then again scans the expression to add a to it.
 *  The result is then added to d after another scan.
 * The repeated scanning makes it very in-efficient. It is better to convert the expression to postfix (or prefix) form before evaluation.
 * The corresponding expression in postfix form is: a b c * + d +.
 * The postfix expressions can be evaluated easily using a stack
 *
 * http://quiz.geeksforgeeks.org/stack-set-2-infix-to-postfix/
 *
 * =================
 * METHOD 1 : Stack
 * =================
 * Scan the infix expression
 *  If the character is an operand, add it to the output
 *  If it's an opening bracket
 *    Push it to stack
 *  If it's a closing bracket
 *    Pop and append all values to output until an opening bracket is seen
 *    While doing this, if an opening bracket is not seen, then the expression is invalid
 *    Now pop the opening bracket too
 *  If an operator is seen
 *    Pop and append to the output as long as the stack shows an operator of higher or equal priority
 *    This is because operators with higher priority need to evaluated first
 *    Now push this operator
 *  Empty the stack and append its contents to the output
 *  
 * TIME  : O(n)
 * SPACE : O(n)
 * 
 */

public class Infix_To_Postfix {

    public static Map<Character, Integer> priorityMap;

    public static void main(String...strings){
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(infixToPostfix(infix));
    }

    private static String infixToPostfix(String infixExpression) {
        preparePriorityMap();
        StringBuilder postfix  = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while(i < infixExpression.length()){
            Character infixCharacter = infixExpression.charAt(i++);
            switch(infixCharacter) {
            case '(' : 
                stack.push(infixCharacter);
                break;
            case ')' : 
                while(!stack.isEmpty() && stack.peek() != '(')
                    postfix.append(stack.pop());
                if(stack.isEmpty())
                    quit();
                stack.pop();
                break;
            case '^' :
            case '*' :
            case '/' :
            case '+' : 
            case '-' :
                while(!stack.isEmpty() && priority(infixCharacter) <= priorityMap.get(stack.peek()))
                    postfix.append(stack.pop());
                stack.push(infixCharacter);
                break;
             // operand gets added to output
            default :
                postfix.append(infixCharacter);
                break;
            }
        }
        while(!stack.isEmpty())
            postfix.append(stack.pop());
        return postfix.toString();
    }

    private static void preparePriorityMap() {
        priorityMap = new HashMap<>();
        priorityMap.put('^', 3);
        priorityMap.put('*', 2);
        priorityMap.put('/', 2);
        priorityMap.put('+', 1);
        priorityMap.put('-', 1);
        priorityMap.put('(', 0);
    }

    private static Integer priority(Character infixCharacter) {
        return priorityMap.get(infixCharacter);
    }

    private static void quit() {
        System.out.println("Invalid expression");
        System.exit(1);
    }
}
