package bit_manipulation;

/**
 * @author 47un
 *
 * Given an unsorted array of numbers ranging from 1 to N. One number is missing (marked as -1). Find it.
 * 
 * ======================
 * METHOD 1  : Sum till N
 * ======================
 * Calculate sum till N using N(N + 1)/2
 * Scan the array and subtract each value from the sum
 * Return the sum
 * ! For larger values of N, there may be overflow when calculating sum till N 
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * ==============
 * METHOD 2 : XOR
 * ============== 
 * Scan the array and XOR the elements  => a
 * XOR all the values from 1 to N       => b
 * XOR the results of the above operations to get the missing number (i.e. a XOR b => Missing number)
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Missing_Number {

    public static void main(String[] args) {
        int[] numbers = {7, 2, 1, 5, 3, 4, -1, 6, 9};
        System.out.println("Method 1 : " + findTheMissingNumberUsingSum(numbers));
        System.out.println("Method 2 : " + findTheMissingNumberUsingXOR(numbers));
    }

    private static int findTheMissingNumberUsingSum(int[] numbers) {
        int lastNumber = numbers.length;
        int sumTillN   = (lastNumber * (lastNumber + 1)) / 2;
        for(int number : numbers)
            if(number != -1)
                sumTillN -= number;
        return sumTillN;
    }

    private static int findTheMissingNumberUsingXOR(int[] numbers) {
        int lastNumber = numbers.length;
        int xorNumbers = 0;
        int xorTillN   = 0;
        for(int i = 0; i < lastNumber; i++) {
            if(numbers[i] != -1)
                xorNumbers ^= numbers[i];
            xorTillN ^= (i + 1);
        }
        return xorNumbers ^ xorTillN;
    }
}
