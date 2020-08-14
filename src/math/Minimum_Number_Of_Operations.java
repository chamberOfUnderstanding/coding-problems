package math;

/**
 * @author 47un
 *
 * Given a number x & a target number y. Find out minimum number of operations required to convert x to y, where only the following
 * operations are allowed :
 *      Multiply number by 2.
 *      Subtract 1 from the number.  
 * 
 * http://www.geeksforgeeks.org/minimum-number-operation-required-convert-number-x-y/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Iteration
 *            Perform the reverse operation of generating x from y by either dividing by 2 or adding 1
 *            While y > x
 *             If y is even, divide by 2
 *             If y is odd, add 1
 *             Increment operations
 *            If y < x add x - y to the operations (we have to add x - y # of 1s)
 *            If y = x add nothing to the operations
 *            
 * TIME     : O(logy)
 * SPACE    : O(1)
 *
 * 
 */

public class Minimum_Number_Of_Operations {

    public static void main(String[] args) {
        int number1 = 4;
        int number2 = 7;
        System.out.println(getMinimumNumberOfOperations(number1, number2));
    }

    private static int getMinimumNumberOfOperations(int number1, int number2) {
        int operations = 0;
        while(number2 > number1) {
            if((number2 & 1) == 0)
                number2 /= 2;
            else
                number2++;
            operations++;
        }
        return operations + ((number1 == number2)? 0 : number1 - number2);
    }
}
