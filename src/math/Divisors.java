package math;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author 47un
 *
 * Find all the divisors of a natural number
 * 
 * http://www.geeksforgeeks.org/find-all-divisors-of-a-natural-number-set-2/
 * http://www.geeksforgeeks.org/find-divisors-natural-number-set-1/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Iterate from 1 to square root of the number //TODO Why the square root senpai?
 *              If i divides the number and it is not on the list, add i and number/i to the divisors list
 *            Since we want the divisors in a sorted order, we use a TreeSet
 * TIME     : O(sqrt(n))
 * SPACE    : O(1)
 *
 * 
 */

public class Divisors {
    public static void main(String[] args) {
        int number = 12;
        new Divisors().findAllDivisors(number);
    }

    private void findAllDivisors(int number) {
        Set<Integer> divisors = new TreeSet<>();
        for(int i = 1; i <= Math.sqrt(number); i++) {
            if(number % i == 0 && !divisors.contains(i)) {
                divisors.add(i);
                divisors.add(number / i);
            }
        }
        System.out.println(divisors);    
    }
}
