package array;

import java.util.Stack;

/**
 * @author 47un
 *
 * Given array of integers, find the maximum absolute difference between NEAREST left and right smaller elements of every element in array.
 * If there is no smaller element on right side or left side of any element then we take zero as smaller element.
 * For example for leftmost element, nearest smaller element on left side is considered as 0.
 * Similarly for rightmost elements, smaller element on right side is considered as 0.
 * e.g.
 * Input  :    array[] = {2, 4, 8, 7, 7, 9, 3}
 * Output :     4
 * Left smaller   LS[] = {0, 2, 4, 4, 4, 7, 2}
 * Right smaller  RS[] = {0, 3, 7, 3, 3, 3, 0}
 * Maximum Difference is abs(LS[i] - RS[i]) = 7 - 3 = 4
 * 
 * http://www.geeksforgeeks.org/find-maximum-difference-between-nearest-left-and-right-smaller-elements/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Similar to finding Next Greatest Element
 *            Maintain a stack and find the left smallest and right smallest values of each array element
 *            Scan the array
 *              While the stack top holds a larger value (surely it can't be the smallest value to the left of this element)
 *               Pop it
 *              If stack is empty left smallest is 0 else stack top
 *              Push the current item onto stack
 *            Clear the stack and do the same process to find right smallest values. But this time, scan the array in reverse
 *              This is because smallest value to the right becomes the smallest value to the left if the array is reversed
 *            Scan both arrays and perform the calculation              
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Largest_Absolute_Difference {
    
    public static void main(String[] args) {
        int[] array = {2, 4, 8, 7, 7, 9, 3};
        System.out.println(getMaximumDifference(array));
    }

    private static int getMaximumDifference(int[] array) {
        int[] leftSmallest = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(array[0]);
        for(int i = 1; i < array.length; i++) {
            while(!stack.isEmpty() && array[i] <= stack.peek())
                stack.pop();
            leftSmallest[i] = stack.isEmpty()? 0 : stack.peek();
            stack.push(array[i]);
        }
        
        int[] rightSmallest = new int[array.length];
        stack.clear();
        stack.push(array[array.length - 1]);
        for(int i = array.length - 2; i >= 0; i--) {
            while(!stack.isEmpty() && array[i] <= stack.peek())
                stack.pop();
            rightSmallest[i] = stack.isEmpty()? 0 : stack.peek();
            stack.push(array[i]);
        }
        
        int result = -1;
        for(int i = 0; i < array.length; i++)
            result = Math.max(result, Math.abs(leftSmallest[i] - rightSmallest[i]));
        return result;
    }
}
