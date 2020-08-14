package math;

import java.util.Scanner;

/**
 * @author 47un
 *
 * A modified Kaprekar number is a positive whole number 'n' with 'd' digits, such that
 * when we split its square into two pieces - a right hand piece 'r' with 'd' digits and a left hand piece 'l' that contains the remaining 'd' or 'd-1' digits,
 * the sum of the pieces is equal to the original number (i.e. r + l = n).
 * Find all Kaprekar numbers between the given range
 * 
 * https://www.hackerrank.com/challenges/kaprekar-numbers
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Iterative
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */
public class Kaprekar_Number {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            int lowest = Integer.parseInt(scanner.nextLine());
            int highest = Integer.parseInt(scanner.nextLine());
            boolean validRange = false;
            for(int number = lowest; number <= highest; number++)
                if(isKaprekarNumber(number)){
                    validRange = true;
                    System.out.print(number+" ");
                }
            if(!validRange)
                System.out.println("Invalid Range");
        }
    }

    private static boolean isKaprekarNumber(int number) {
        if(number==1)
            return true;
        int digits = getNumberOfDigits(number);
        long square = (long)Math.pow(number, 2);
        long power = (long)Math.pow(10,digits);
        return number == (square/power + square%power);
    }

    private static int getNumberOfDigits(int number) {
        int digits = 0;
        while(true)
            if(number < Math.pow(10, ++digits))
                return digits;
    }
}
