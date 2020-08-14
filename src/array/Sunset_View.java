package array;

import java.util.Stack;

/**
 * @author 47un
 * 
 * An array of buildings is facing the sun.
 * The heights of the building is given in an array. Which all buildings will see the sunset?
 * 
 * http://www.geeksforgeeks.org/amazon-interview-experience-set-189-for-sde1/
 * 
 * ================
 * METHOD 1 : Meh..
 * ================
 * First one can obviously gawk at the vista.
 * Track the tallest so far
 * Scan the buildings,
 *  If this is one is taller than the tallest so far, update the tallest so far and this one can see the sunset
 *  Increase the count too
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * ============================
 * METHOD 1 : Reverse scan only
 * ============================
 * Push the last building into a stack
 * Scan the rest from right to left
 * While the current building is taller or of equal height when compared to the stack top, pop the stack
 *  These popped ones are the unlucky delinquents who can't see the sun set
 * Push the current building
 * After the scan, all the buildings in the stack get to see the sun set
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Sunset_View {

    public static void main(String[] args) {
        int[] buildings = {1, 7, 4, 8, 2, 9};
        System.out.println(whoAllCanSeeTheSunset(buildings));
        System.out.println(whoAllCanSeeTheSunsetReverseScan(buildings));
    }

    private static int whoAllCanSeeTheSunset(int[] array){
        int tallestSoFar  = array[0];
        int sunsetViewers = 1;
        for(int i = 1; i < array.length; i++)
            if(array[i] > tallestSoFar){
                tallestSoFar = array[i];
                sunsetViewers++;
            }
        return sunsetViewers;                
    }

    private static int whoAllCanSeeTheSunsetReverseScan(int[] array) {
        Stack<Integer> stack = new Stack<>();
        stack.push(array[array.length - 1]);
        for(int i = array.length - 2; i >= 0; i--) {
            while(!stack.isEmpty() && array[i] >= stack.peek())
                stack.pop();
            stack.push(array[i]);
        }
        return stack.size();
    }
}
