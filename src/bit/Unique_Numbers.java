package bit;

/**
 * @author 47un
 *
 * Given an array of positive numbers, in which all but 2 numbers repeat exactly once.
 * Find these two numbers and display them in ascending order
 * 
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=1376
 * 
 * METHOD 1 : 
 * Xor all the numbers => xor1
 * Find the index of the rightmost 1 in the xor result (Go right to left)
 * Xor all the numbers that have the bit as 1 at this index => xor2
 * xor2 holds one unique number and xor1 ^ xor2 gives the other one
 * 
 * But how? or why?
 * To find the rightmost index of the xor with a 1, negate the xor (2's complement), then & it with the original, result will be the rightmost 1 bit with zeroes (if any)
 * e.g.   
 *        xor = 1001
 *       -xor = 0111
 * xor & -xor = 0001
 * 
 * Also this bit position is the smallest power of 2 that this number contains
 * xor all the elements in the array that have the bit at this index, i as 1
 * To check whether or not a number has a bit at position i as 1, & it with 2^i
 * If the result is > 0, it is set else it is not set
 * 	 e.g. 50 is 110010. To check if bit 5 is set, do 50 & 2^5 = 50 & 32 =>
 *         110010 & 100000 => 100000 > 0 => yes!
 *         But but 3 is a 0, coz 50 & 2^3 => 50 & 8 => 110010 & 001000 => 0
 * Here just & each number with the value found out through xor & -xor (smallest power of 2)
 * The numbers with the i bit as 1 will have one unique number and the rest as duplicates (Similar to the unique number problem)
 * Once you have the unique number and the sum of which it is a part of, another xor gives the second unique number (Similar to the missing number problem)
 * 
 * The bit at index i of the xor is 1 iff there's something different going on at that index.
 * Xor gives 1 if the bits are different. Something was different in the first place due to the presence of the unique numbers.
 *  
 * TIME    : O(n)
 * SPACE   : O(1)
 * 
 */

public class Unique_Numbers {

    public static void main(String[] args) {
        int[] array = {1, 8, 9, 10, 9, 10};
        findUniqueNumbers(array);
    }

    private static void findUniqueNumbers(int[] array) {
        int xor = 0;
        for(int element : array)
            xor ^= element;
        int rightMostSetBit = xor & -xor;
        int uniqueNumber1 = 0;
        for(int element : array)
            if((element & rightMostSetBit) > 0)
                uniqueNumber1 ^= element;
        int uniqueNumber2 = xor ^ uniqueNumber1;
        System.out.println(
                uniqueNumber1 < uniqueNumber2? 
                        uniqueNumber1 + " " + uniqueNumber2 : 
                            uniqueNumber2 + " " + uniqueNumber1);
    }
}
