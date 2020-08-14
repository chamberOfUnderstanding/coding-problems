package bit;

/**
 * @author 47un
 * 
 * Given a number n and a number k. If the kth bit of n is a 1, clear it. 
 * 
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=1638
 * 
 * =========
 * METHOD 1
 * =========
 * Count the number of bits in the binary of this number
 * Generate a number = 2 ^ (bitCount - position). This number will have just the kth bit as 1. Rest of them will be 0.
 * & this number with the original number. 
 *  If the value is > 0, then kth bit is set.
 *   To clear it, subtract the generated number
 *  Else
 *   Just return the number
 * 
 * TIME     : O(n)  // Bit count of the number's binary
 * SPACE    : O(1)
 *
 * 
 */

public class Replace_The_Kth_Bit_From_MSB {

    public static void main (String[] args) {
        int number   = 13;
        int position = 2;
        System.out.println(clearKthBitFromMSB(number, position));
    }
    
    private static int clearKthBitFromMSB(int number, int position) {
        int bitCount = countBits(number);
        int kthBit   = (1 << (bitCount - position));
        return (number & kthBit) > 0? number - kthBit : number;
    }

    private static int countBits(int number){
        int count = 0;
        while(number != 0) {
            number /= 2;
            count++;
        }
        return count;
    }
}
