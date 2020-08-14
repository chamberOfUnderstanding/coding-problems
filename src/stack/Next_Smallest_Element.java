package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Given an array, print the Next Smallest Element (NSE) for every element.
 * The Next smallest Element for an element x is the first smallest element on the right side of x in array.
 * Elements for which no smaller element exist, consider next smaller element as +Infinity.
 * 
 * http://www.geeksforgeeks.org/next-greater-element/
 * 
 * * ================
 * METHOD 1 : Stack
 * ================
 * Push first item onto stack
 * Scan the rest of the elements
 *  If current element is smaller than stack top, then current element is the NGE of the element at stack top
 *  This element is also the NGE of all elements on stack that are less than this, so scan till you see something smaller
 *  Every element gets pushed, no matter what
 * Now empty the stack. There is no NGE for these elements
 * - Order is not preserved
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 * ==================================
 * METHOD 2 : Stack [Preserves order]
 * ==================================
 * Instead of pushing the elements, push the indices and maintain an NSE array to track the popped items
 * Push index of first item to stack
 * Scan the rest of the elements
 *  If current element is smaller than stack top, then current element is the NSE of the element at stack top,
 *      update NSE[stack top] as current element
 *  This element is also the NGE of all elements on stack that are less than this, so scan till you see something smaller
 *  Every element gets pushed, no matter what
 * Now empty the stack. There is no NSE for these elements
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * The same logic when applied to the array elements in reverse gives you Previous Smallest Element
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 */

public class Next_Smallest_Element {

    public static void main(String[] args) {
        int[] array = {11, 57, 13, 10, 21, 38};
        System.out.println("Method 1 : Does not preserve order");
        findNextSmallestElementI(array);   
        System.out.println("\nMethod 2 : Preserves order");
        findNextSmallestElementII(array);       
    }

    public static void findNextSmallestElementI(int[] array) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(array[0]);
        for (int index = array.length - 1; index >= 0; index--){
            while(!stack.isEmpty() && array[index] <= stack.peek())
                System.out.println(stack.pop() + " : " + array[index]);
            stack.push(array[index]); 
        } 
        while (!stack.isEmpty() && stack.peek() != null) 
            System.out.println(stack.pop() + " : " + Integer.MAX_VALUE); 
    }

    public static void findNextSmallestElementII(int[] array) {
        int[] nse = new int[array.length];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for (int i = 1; i < array.length; i++){
            while(!stack.isEmpty() && array[i] <= array[stack.peek()])
                nse[stack.pop()] =  array[i];
            stack.push(i); 
        } 
        while (!stack.isEmpty() && stack.peek() != null) 
            nse[stack.pop()] = Integer.MAX_VALUE;
        for(int index = 0; index < array.length; index++)
            System.out.println(array[index] + " : " + nse[index]);
    }   
}