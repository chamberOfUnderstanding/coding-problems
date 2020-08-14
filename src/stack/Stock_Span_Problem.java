package stack;

import java.util.Stack;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/the-stock-span-problem/
 * 
 * Given the stock for a few days, find the span for each day
 * Span : Number of consecutive days before given day where the stock was less than or equal to current day
 * Span of first day is always 1
 * 
 * e.g.   : 100 60 65 70 65 80 85
 * Span   :  1  1  2   3  1  5  6
 * 
 * ================
 * METHOD 1 : Stack
 * ================
 * Span for the first day is always 1
 * Push the stock for the first day into stack. NOTE : The index is pushed and not the actual value
 * Scan the stock for all days
 *   Pop the stack UNTIL a value greater than current day's stock is seen
 *    This is to find how many consecutive days are present with stock value <= current day value
 *   Once a value has been spotted that's greater than current stock value, we can calculate the span
 *    If the stack is empty, then all the values before current day are <= current day stock, ergo span = current + 1 (coz 0 index)
 *    If stack is not empty, span = number of days between current day and the day on top of stack (the day where stock value was greater)
 *     
 * TIME  : O(n)
 * SPACE : O(n) // When the elements are in descending order, all of them need to be maintained in the stack
 * 
 */

public class Stock_Span_Problem {

    public static void main(String...strings){
        int[] stock = {100, 60, 65, 70, 65, 80, 85};
        findSpan(stock);
    }

    private static void findSpan(int[] stock) {
        int[] span = new int[stock.length];
        Stack<Integer> stack = new Stack<>();
        span[0] = 1;
        stack.push(0);
        for(int i = 1; i < stock.length; i++) {
            while(!stack.isEmpty() && stock[i] >= stock[stack.peek()])
                stack.pop();			
            span[i] = stack.isEmpty()? i + 1 : i - stack.peek();
            stack.push(i);
        }
        System.out.println("Stock    Span");
        for(int i = 0; i < stock.length; i++)
            System.out.println(stock[i] + "        " + span[i]);
    }
}
