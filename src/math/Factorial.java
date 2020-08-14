package math;

/**
 * @author 47un
 *
 * Find the factorial of a number 
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Recursive
 *
 * TIME     : O(n)
 * SPACE    : O(1)  // Tail recursion
 *
 * METHOD 2 : Iterative
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Factorial {
    
	public static void main(String...x){
	    Factorial factorial = new Factorial();	     
		System.out.println(factorial.findFactorialRecursive(7));
		System.out.println(factorial.findFactorialIterative(7));
	}

	private int findFactorialRecursive(int number){
		return number == 0 || number == 1? 1 : number * findFactorialRecursive(number-1);
	}
	
	private int findFactorialIterative(int number){
		if(number == 0)
			return 1;
		int factorial = 1;
		while(number != 1)
			factorial *= number--;
		return factorial;
	}
}
