package math;

/**
 * @author 47un
 *
 * Find the nth fibonacci number
 * 
 * ==============
 * Alternatives
 * ==============
 * Given a floor of dimensions 2 x W and tiles of dimensions 2 x 1, write code to find the number of ways the floor can be tiled.
 * A tile can either be placed horizontally i.e as a 1 x 2 tile or vertically i.e as 2 x 1 tile.
 * 
 * ===========
 * METHOD 1 : Recursion
 * ===========
 * TIME     : O(fib(n)) //Golden ratio
 * SPACE    : O(1) 
 * 
 * =========
 * METHOD 2 Iteration
 * ===========
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * ===========
 * METHOD 3 : DP
 * ==============
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Fibonacci {
    public static void main(String[] args) {
        int number = 15;
        System.out.println(
                "Recursive : " + fibonacciRecursive(number) + 
                "\nIterative : " + fibonacciIterative(number) + 
                "\nDynamic Programming : " + fibonacciDynamic(number));
    }
    
    private static int fibonacciRecursive(int number) {
        return number <= 1? number : fibonacciRecursive(number - 1) + fibonacciRecursive(number - 2);
    }

    private static int fibonacciIterative(int number){
        int first = 0;
        int second = 1;
        int third = 1;
        while(number-- > 1){
            third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    private static int fibonacciDynamic(int number){
        int[] lookup = new int[number + 1];
        lookup[0] = 0;
        lookup[1] = 1;
        if(number <= 1)
            return lookup[number];
        for(int i = 2; i <= number; i++)
            lookup[i] = lookup[i - 1] + lookup[i - 2];
        return lookup[number];
    }
}
