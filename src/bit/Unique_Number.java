package bit;

/**
 * @author 47un
 *
 * In an array of integers, all items but one occur in pairs. Find this unique item.
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : XOR all items together
 *            The result is the unique item
 *            a xor  a =  0
 *            0 xor  a =  a
 *            1 xor  a = ~a
 *            a xor ~a =  1
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Unique_Number {
    
    public static void main(String[] args) {
        int size    = 12;
        int[] array = {4, 1, 3, 4, 6, 6, 7, 5, 7, 5, 1};        
        System.out.println(findUniqueInteger(array, size));
    }
    
    private static int findUniqueInteger(int[] array, int size) {
        int unique = 0;
        for(int element : array)
            unique ^= element;
        return unique;
    }	 
}
