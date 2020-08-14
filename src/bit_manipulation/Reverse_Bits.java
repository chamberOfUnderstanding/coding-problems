package bit_manipulation;

/**
 * @author 47un
 *
 * Reverse the bits of a given number
 * 
 * http://www.geeksforgeeks.org/write-an-efficient-c-program-to-reverse-bits-of-a-number/
 * 
 * ==========
 * METHOD 1 
 * ==========
 * Scan all bits of the input
 *  If bit i is set (number & ( 1 << i) > 0)
 *   Set the bit at NUMBER_OF_BITS - i of the reversed number
 *   To do this, shift the NUMBER_OF_BITS - i by 1 (Now you get which bit of the output needs to be set)
 *   OR this value with the reversed number  
 *             
 * TIME    : O(logn)
 * SPACE   : O(1)
 * 
 */

public class Reverse_Bits {

	public static void main(String[] args) {
		int number = 846930887;
		System.out.println(number);
		System.out.println(String.format("%32s", Integer.toBinaryString(number)).replaceAll(" ", "0"));
		System.out.println("\nReversing...\n");
		int reversedNumber = reverseBits(number);
		System.out.println(String.format("%32s", Integer.toBinaryString(reversedNumber)).replaceAll(" ", "0"));
	}

	private static int reverseBits(int number) {
		final int NUMBER_OF_BITS = 31; 
		int reversedNumber = 0;
		for(int i = 0; i <= NUMBER_OF_BITS; i++)
			if((number & (1 << i)) > 0)
				reversedNumber |= 1 << (NUMBER_OF_BITS - i);
		System.out.println(reversedNumber);
		return reversedNumber;
	}
}
