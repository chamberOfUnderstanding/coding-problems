package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * 
 * Given an array, print the Next Greater Element (NGE) for every element.
 * The Next greater Element for an element x is the first greater element on the right side of x in array.
 * Elements for which no greater element exist, consider next greater element as -1.
 * 
 * http://www.geeksforgeeks.org/next-greater-element/
 * 
 * ================
 * METHOD 1 : Stack
 * ================
 * Push first item onto stack
 * Scan the rest of the elements
 *  If current element is greater than stack top, then current element is the NGE of the element at stack top
 *  This element is also the NGE of all elements on stack that are less than this, so scan till you see something greater
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
 * Instead of pushing the elements, push the indices and maintain an nge array to track the popped items
 * Push index of first item to stack
 * Scan the rest of the elements
 *  If current element is greater than stack top, then current element is the NGE of the element at stack top,
 *      update nge[stack top] as current element
 *  This element is also the NGE of all elements on stack that are less than this, so scan till you see something greater
 *  Every element gets pushed, no matter what
 * Now empty the stack. There is no NGE for these elements
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!
 * The same logic when applied to the array elements in reverse gives you Previous Greatest Element
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 */

public class Next_Greatest_Element {

    public static void main(String[] args) {
        int[] array = {11, 13, 21, 3};
        System.out.println("Method 1 : Does not preserve order");
        findNextGreatestElementI(array);	
        System.out.println("\nMethod 2 : Preserves order");
        findNextGreatestElementII(array);		
    }

    public static void findNextGreatestElementI(int[] array) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(array[0]);
        for (int i = 1; i < array.length; i++){
            while(!stack.isEmpty() && array[i] >= stack.peek())
                System.out.println(stack.pop() + " : " + array[i]);
            stack.push(array[i]); 
        } 
        while (!stack.isEmpty() && stack.peek() != null) 
            System.out.println(stack.pop() + " : " + Integer.MIN_VALUE); 
    }

    public static void findNextGreatestElementII(int[] array) {
        int[] nge = new int[array.length];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for (int i = 1; i < array.length; i++){
            while(!stack.isEmpty() && array[i] >= array[stack.peek()])
                nge[stack.pop()] =  array[i];
            stack.push(i); 
        } 
        while (!stack.isEmpty() && stack.peek() != null) 
            nge[stack.pop()] = Integer.MIN_VALUE;
        for(int index = 0; index < array.length; index++)
            System.out.println(array[index] + " : " + nge[index]);
    }	
}
