package math;

/**
 * @author 47un
 *
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler003
 * 
 * Given a number, find its largest prime factor
 * 
 * ALGORITHM
 * ---------
 * As long as the number is even, repeatedly divide the number by 2
 * If the number is now 1, 2 is the LPF
 * Else we are left with an odd number
 * Start from 3 and go up till sqrt(number). If the number is divisible by i, repeatedly divide it, until it is not. Advance i by 2.
 * In the above step, we advance by 2 to skip all even factors. AN ODD NUMBER DOES NOT HAVE EVEN FACTORS !!
 * Now, if the number is <2 then the LPF is the i in the previous iteration, hence return i-2 
 */

public class Largest_Prime_Factor {
	public static void main(String...strings){
		long number = 9;
		Largest_Prime_Factor largest_Prime_Factor = new Largest_Prime_Factor();
		System.out.println(largest_Prime_Factor.findLargestPrimeFactor(number));
	}

	// O(n^0.5), O(1)
	private long findLargestPrimeFactor(long number) {		
		while(number%2 == 0)
			number /= 2;
		if(number == 1)
			return 2;
		int i = 3;
		for( ; i <= Math.sqrt(number); i += 2 )
			while(number % i == 0)
				number /= i;
		return number > 2? number : i - 2;
	}
}
