package bit;

/**
 * @author 47un
 *
 * Given a number n, generate the n bit gray code sequence.
 * 
 * http://www.geeksforgeeks.org/given-a-number-n-generate-bit-patterns-from-0-to-2n-1-so-that-successive-patterns-differ-by-one-bit/
 * 
 * ===========================
 * METHOD 1 : Bit manipulation
 * ===========================
 * Since 2^n elements are there in a gray code of n bits, scan from 0 to 2^n
 *  Divide the current value by 2 (Right shift by 1)
 *  XOR the new value with the original value
 *  Convert this to its binary representation using Integer.toBinaryString();
 * 
 * Example, for n = 3;
 * (000)^(000) = 000
 * (000)^(001) = 001
 * (001)^(010) = 011
 * (001)^(011) = 010
 * (010)^(100) = 110
 * (010)^(101) = 111
 * (011)^(110) = 101
 * (011)^(111) = 100
 * 
 * TIME   :  O(2^n)
 * SPACE  :  O(1)
 * 
 * =================
 * METHOD 2 : Arrays
 * =================
 * n bit gray code can be generated with n-1 bit gray code
 * Example : Generate 2 bit gray code using 1 bit gray code :
 * 	Start off with the 1 bit gray code, {0, 1}
 *  	gray code          =   {0, 1}
 *  
 * 	Reverse it
 *  	reversed gray code =   {1, 0}
 *  
 * 	Prefix the items in original with 0
 *	    gray code          =   {00, 01}
 *
 * 	Prefix the items in reversed with 1
 *  	reversed gray code =   {11, 10}
 *  
 * 	Concatenate the arrays
 *  	gray code for 2 bits = {00, 10, 11, 10}
 *   
 * Repeat the above for n bits
 *             
 * TIME    : O(2^n)
 * SPACE   : O(1)
 * 
 */

public class Gray_Code_Generator {

	public static void main(String[] args) {
		int numberOfBits = 5;
		System.out.println("Method II");
		generateGrayCodeII(numberOfBits);
		System.out.println("Method I");
		String[] grayCode = generateGrayCodeI(numberOfBits);
		for(String i : grayCode)
			System.out.println(i);
	}


	private static void generateGrayCodeII(int numberOfBits) {
		for(int i = 0; i < (1 << numberOfBits); i++)
			System.out.println(Integer.toBinaryString((i >> 1) ^ i));			
	}


	private static String[] generateGrayCodeI(int numberOfBits) {
		String[] grayCode         = {"0", "1"};
		String[] reversedGrayCode = {"1", "0"};
		for(int i = 1; i < numberOfBits; i++) {
			prefix(grayCode, 0);
			prefix(reversedGrayCode, 1);
			grayCode = concatenate(grayCode, reversedGrayCode);
			reversedGrayCode = reverse(grayCode);
		}
		return grayCode;
	}

	private static String[] reverse(String[] grayCode) {
		int length = grayCode.length;
		String[] reverseGrayCode = new String[length];
		for(int i = 0; i < length; i++)
			reverseGrayCode[i] = grayCode[length - i - 1];
		return reverseGrayCode;
	}

	private static void prefix(String[] grayCode, int prefix) {
		for(int i = 0; i < grayCode.length; i++)
			grayCode[i] = prefix + grayCode[i];
	}

	private static String[] concatenate(String[] grayCode, String[] reverseGrayCode) {
		int length = grayCode.length;
		String[] completeGrayCode = new String[length * 2];
		int i = 0;
		int j = 0;
		while(j != length)
			completeGrayCode[i++] = grayCode[j++];
		j = 0;
		while(j != length)
			completeGrayCode[i++] = reverseGrayCode[j++];
		return completeGrayCode;
	}
}
