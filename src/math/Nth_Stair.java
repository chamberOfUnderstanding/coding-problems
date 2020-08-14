package math;

/**
 * @author 47un
 *
 * If you could climb 1 step or 2 steps at a time, find the number of ways you can reach the N th stair
 * 
 * http://www.geeksforgeeks.org/count-ways-reach-nth-stair/
 * http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibpuzzles.html
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : The number of ways to get to the nth stair is the same as the nth fibonacci number
 *
 * TIME     : O(n) // Iterative
 * SPACE    : O(1)
 *
 * 
 */

public class Nth_Stair {
	
	public static void main(String...strings){
		int stairs = 4;
		Nth_Stair nth_Stair = new Nth_Stair();
		System.out.println(nth_Stair.waysToGetToTheTop(stairs));
	}

	private int waysToGetToTheTop(int stairs) {		
		int fr = fibonacciRecursive(stairs + 1);
		int fi = fibonacciIterative(stairs + 1);
		int fdp = fibonacciDynamic(stairs + 1);
		System.out.println(
				"Recursive : " + fr + 
				"\nIterative : " + fi + 
				"\nDynamic Programming : " + fdp);
		return fr;
	}

	private int fibonacciRecursive(int number) {
		return number <= 1? number : fibonacciRecursive(number - 1) + fibonacciRecursive(number - 2);
	}
	
	private int fibonacciIterative(int number){
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
	
	private int fibonacciDynamic(int number){
		int[] lookup = new int[number+1];
		lookup[0] = 0;
		lookup[1] = 1;
		if(number <= 1)
			return lookup[number];
		for(int i = 2; i <= number; i++)
			lookup[i] = lookup[i-1] + lookup[i-2];
		return lookup[number];
	}
}
