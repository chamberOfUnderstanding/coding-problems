package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Sort the items in a stack
 *  
 * http://www.geeksforgeeks.org/sort-a-stack-using-recursion/
 * 
 * =========
 * METHOD 1 
 * =========
 * This is like the lost extra limbed twin of Stack_Reverse
 * Just add the condition where you make sure the larger ones are on top
 *
 * Hold all values in Function Call Stack until the stack becomes empty.
 *  Pop an item into a variable, recursively call sort
 * When the stack becomes empty, push all items one by one in a sorted way
 * 
 * sortedInsert()
 * If there's nothing in the stack or the item is greater than stack top, gladly push it
 * If the item is less than the top of stack, find a spot for this item
 * 
 * TIME     : O(n^2)
 * SPACE    : O(n)
 * 
 * =========
 * METHOD 2 
 * =========
 * Use a temp stack instead of the function call stack.
 * 
 * 
 */

public class Stack_Sort {

	public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int[] array = {1,4,5,6,7,82,49,50,-1};
        System.out.print("Stack before sorting : ");
        for(int i = array.length - 1; i >= 0; i--)
            System.out.print(array[i] + " ");
        for(int element : array)
            stack.push(element);
        sort2(stack);
		sort1(stack);
        System.out.print("\nStack after sorting : ");
        while(!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }

    private static void sort1(Stack<Integer> stack) {
        if(!stack.isEmpty()){
            int poppedItem = stack.pop();
            sort1(stack);
            sortedInsert(poppedItem, stack);
        }			
    }

    private static void sortedInsert(int item, Stack<Integer> stack) {
        if(stack.isEmpty() || item > stack.peek())
            stack.push(item);		
        else{
            int poppedItem = stack.pop();
            sortedInsert(item, stack);
            stack.push(poppedItem);
        }
    }
    
	private static void sort2(Stack<Integer> stack) {
    	Stack<Integer> tempStack = new Stack<>();
		while (!stack.isEmpty()) {
			int poppedItem = stack.pop();
			while (!tempStack.isEmpty() && poppedItem < tempStack.peek())
				stack.push(tempStack.pop());
			tempStack.push(poppedItem);
    	}
		for (Object i : tempStack.toArray()) {
			System.out.print(i + " ");
		}
    }
}