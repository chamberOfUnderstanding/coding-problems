package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author 47un
 * 
 * Convert infix expression to prefix expression
 *  
 * http://scanftree.com/Data_Structure/infix-to-prefix
 * 
 * ================
 * METHOD 1 : Stack
 * ================
 * Reverse the infix expression, literally. Also flip all ( to ) and ) to (
 *  For flipping the braces : ( has ascii 40 and ) has 41. So if it's ( add 1 else if it's ) subtract 1 (or add -1)
 * Find postfix of the above reversed thing
 * Reverse the postfix
 * 
 * TIME  : O(n)
 * SPACE : O(n)
 * 
 */

public class Infix_To_Prefix {

    public static Map<Character, Integer> priorityMap;

    public static void main(String...strings){
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(infixToPrefix(infix));
    }

    private static String infixToPrefix(String infix) {
        return infixToPostfix(reverse(infix)).reverse().toString();
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

    private static StringBuilder infixToPostfix(String infixExpression) {
        preparePriorityMap();
        StringBuilder postfix  = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int index = 0;
        while(index < infixExpression.length()){
            Character infixCharacter = infixExpression.charAt(index++);
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
            default :
                postfix.append(infixCharacter);
                break;
            }
        }
        while(!stack.isEmpty())
            postfix.append(stack.pop());
        return postfix;
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
