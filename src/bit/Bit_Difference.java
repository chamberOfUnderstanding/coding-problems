package bit;

/**
 * @author 47un
 * 
 * Given two numbers A and B. 
 * Count number of bits needed to be flipped to convert A to B.
 * 
 * http://www.geeksforgeeks.org/count-number-of-bits-to-be-flipped-to-convert-a-to-b/
 *
 * ==============
 * METHOD 1 : XOR
 * ==============
 * XOR the numbers
 * Count the number of 1s in the result
 *  If number & 1 == 1, then the last bit of the number is a 1, increase count
 *  Right shift the number by 1 bit
 * 
 * TIME     : O(1)
 * SPACE    : O(1)
 * 
 * =========
 * METHOD 2
 * =========
 * Repeat till both numbers are 0
 *  If the %2 of both numbers are different, increase flips
 *  Update both numbers by dividing by 2 
 * 
 * TIME     : O(d) // d bits in the binary representation
 * SPACE    : O(1)
 * 
 */

public class Bit_Difference {
    
    public static void main(String[] args) {
        int number1 = 5;
        int number2 = 9;
        System.out.println(findBitDifferenceXOR(number1, number2));
        System.out.println(findBitDifference(number1, number2));
    }

    private static int findBitDifferenceXOR(int number1, int number2) {
        int xor = number1 ^ number2;
        int flips = 0;
        while(xor != 0) {
            if((xor & 1) == 1)
                flips++;
            xor = xor >> 1;
        }           
        return flips;
    }

    private static int findBitDifference(int number1, int number2) {
        if(number1 == number2)
            return 0;
        int flips = 0;
        while(number1 != 0 || number2 != 0){
            if(number1 % 2 != number2 % 2)
                flips++;
            number1 /= 2;
            number2 /= 2;
        }
        return flips;
    }
}
