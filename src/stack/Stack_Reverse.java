package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Reverse a stack using recursion 
 * 
 * http://www.geeksforgeeks.org/reverse-a-stack-using-recursion/
 * 
 * ============
 * METHOD 1 : 
 * ============
 * Hold all values in Function Call Stack until the stack becomes empty.
 *  Pop an item into a variable, recursively call reverse
 * When the stack becomes empty, insert all held items one by one at the bottom of the stack.
 * 
 * insertAtBottom()
 *  This method is first called for the bottom most item on the stack
 *    The bottom most item is simply popped (in the reverse() method) and pushed (the isEmpty(), push() statements of this method)
 *  The rest of the items get added at the end of the stack
 *  This is to make sure that the old items stay on top and the new ones go to the bottom
 *  
 * !Stack, before reversal, will hold the items of the array in reverse order
 * 
 * TIME     : O(n^2)
 * SPACE    : O(n)
 *
 */

public class Stack_Reverse {
  
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int[] array = {1,4,5,6,7,82,49,50,-1};
        System.out.print("Stack before reversal : ");
        for(int i = array.length - 1; i >= 0; i--)
            System.out.print(array[i] + " ");
        for(int element : array)
            stack.push(element);
        reverse(stack);
        System.out.print("\nStack after reversal : ");
        while(!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }

    private static void reverse(Stack<Integer> stack) {
        if(!stack.isEmpty()){
            int item = stack.pop();
            reverse(stack);
            insertAtBottom(item, stack);
        }
    }

    private static void insertAtBottom(int item, Stack<Integer> stack) {
        if(stack.isEmpty())
            stack.push(item);
        else{
            int poppedItem = stack.pop();
            insertAtBottom(item, stack);
            stack.push(poppedItem);
        }
    }
}

