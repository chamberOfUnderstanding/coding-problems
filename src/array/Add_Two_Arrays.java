package array;

/**
 * @author 47un
 * 
 * Given two numbers represented by two arrays, write a function that returns sum array.
 * The sum array is an array representation of addition of two input arrays.
 * It is not allowed to modify the arrays.
 * 
 * =========
 * METHOD 1
 * =========
 * Scan both arrays, add them, manage the carry
 * Pffft...
 * 
 * TIME     : O(max(m, n))
 * SPACE    : O(1)
 *
 * 
 */

public class Add_Two_Arrays {

    public static void main(String[] args) {
        int[] number1 = {2, 2, 7, 5, 3, 3, 7, 3, 3, 6, 8, 3, 0, 5, 0, 6};
        int[] number2 =                                     {4, 3, 3, 8};
        int[] sum     = add_Arrays(number1, number2);
        printArray(sum);
    }

    private static int[] add_Arrays(int[] number1, int[] number2) {
        int carry = 0;
        int[] sum = new int[Math.max(number1.length, number2.length) + 1];
        int i = number1.length - 1;
        int j = number2.length - 1;
        int k = sum.length - 1;
        while(i >= 0 && j >= 0) {
            sum[k] = number1[i] + number2[j] + carry;
            carry  = sum[k] / 10;
            sum[k] %= 10;
            i--;
            j--;
            k--;
        }
        while(i >= 0)
            sum[k--] = number1[i--];
        while(j >= 0)
            sum[k--] = number2[j--];
        if(carry == 1)
            sum[0] = 1;
        return sum;
    }

    private static void printArray(int[] number) {
        for(int i = 0; i < number.length; i++)
            if(i == 0 && number[i] == 0)
                continue;
            else
                System.out.print(number[i] + " ");
        System.out.println();
    }
}
