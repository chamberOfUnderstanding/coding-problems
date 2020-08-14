package stack;

import java.util.Stack;

/**
 * @author 47un
 *
 * Given an expression examine whether the pairs and the orders of { } ( ) [ ] are correct in it.
 * For example
 *  expression = [()]{}{[()()]()} => true
 *  expression = [(])             => false
 * 
 * http://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
 * 
 * =======================
 * METHOD 1 : Stack
 * =======================
 * Scan the expression
 * Push the opening brackets
 * In case a closing bracket is seen
 *    If the stack is empty (no opening brackets have been seen), return false
 *    If the stack top is not the proper opening bracket for this closing bracket, return false
 * If stack is empty, the expression is balanced
 * 
 * TIME  : O(n)
 * SPACE : O(n)
 * 
 */

public class Balanced_Paranthesis {
    
    public static void main(String[] args) {
    	Balanced_Paranthesis balancedParanthesis = new Balanced_Paranthesis();
        String expression1 = "[()]{}{[()()]()}";
        String expression2 = "[(])";
		String expression3 = "[((";
		System.out.println(expression1 + " " + balancedParanthesis.isBalanced(expression1));
		System.out.println(expression2 + " " + balancedParanthesis.isBalanced(expression2));
		System.out.println(expression3 + " " + balancedParanthesis.isBalanced(expression3));
    }

    private boolean isBalanced(String expression) {
		if (expression.length() % 2 != 0) {
			return false;
		}
        Stack<Character> stack = new Stack<Character>();		
        for(Character character : expression.toCharArray()){
            switch(character){
            case '(' :
            case '{' :
            case '[' :
                stack.push(character);
                break;
            case ')' :
            case '}' :
            case ']' : 
                if(stack.isEmpty())
                    return false;				
                char lastSeen = stack.peek();
                if(character == ')' && lastSeen != '('
                        || character == '}' && lastSeen != '{'
                        || character == ']' && lastSeen != '[')
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
