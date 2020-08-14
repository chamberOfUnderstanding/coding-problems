package math;

import java.util.Arrays;

/**
 * @author 47un
 * 
 * Given a number n, generate all prime numbers up to n.
 * 
 * =========
 * METHOD 1
 * =========
 * Maintain a flag for each number from 2 to n
 * Set the value of all numbers from 2 to n as true, indicating that they are not prime
 * Scan from 2 to square root of n, i loop
 *  If this number is prime,
 *   Scan from 2 * i to n, j loop
 *      Set all these as false (Non prime).
 *      These are multiples of a number we have considered as prime. So they cant be prime.
 * Scan the flag array and print the number if the flag is set as true for it
 * 
 * TIME     : O(n ^ 1.5)
 * SPACE    : O(n)
 *
 * 
 */

public class Sieve_Of_Eratosthenes {

    public static void main(String[] args) {
        int n = 50;
        generatePrimesTill(n);
    }

    private static void generatePrimesTill(int n) {
        boolean[] seive = new boolean[n + 1];
        Arrays.fill(seive, 2, n, true);
        for(int i = 2; seive[i] && i * i <= n; i++)
            for(int j = i * i; j <= n; j += i)
                seive[j] = false;
        for(int i = 0; i < n + 1; i++)
            if(seive[i])
                System.out.print(i + ","); 
    }

}
