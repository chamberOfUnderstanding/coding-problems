package dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 *
 * Given an amount and a collection of denominations (coins).
 * Find out the minimum number of coins needed to make that amount.
 * Find out the number of ways (coin combinations) in which that amount can be made using the given denominations
 * 
 * Recursive approach   : O(2^coins) [As each coin has two possibilities, whether to be included or excluded)
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 :  
 *       If the amount is 0 then you need 0 coins 
 *       If you've already found out a solution for this amount, use it!
 *       Go through all available coins
 *       If coin value is greater than the amount, the coin can't be selected. So move to the next coin 
 *       Else recurse after reducing the amount by the selected coin's value
 *       If this amount is achievable with less coins, update minimum
 *       This makes sure the minimum is proper, else you'll get minimum as 0 as all successful coin combinations
 *       will end in 'amount=0' and hence 'return 0'.
 *       If this statement is not there then minimum will just be 0 for all
 *       Update the solution repository with the new solution
 *
 *       To find coin combinations :
 *        We need a matrix here and size is coins+1 and amount+1 coz we need to consider 2 special cases :
 *          No coins (coin of denomination 0) & Amount of value 0
 *        For all coin denominations, there is EXACTLY 1 way of making the amount 0 ie. by NOT selecting any coin
 *        None of the possible amounts can be achieved using 0 COINS
 *        For each coin
 *          For each possible amount
 *              Let's assume this coin cannot be selected
 *                  See if it can be selected (coin value <= amount)
 *                       If yes, then update the amount

 * TIME     : O(coins*amount)
 * SPACE    : O(coins*amount)
 *
 * 
 */

public class Coin_Change_Problem_Minimum_Coins {
    public static void main(String...strings){		
        int amount = 50;
        int[] coin = {1,5,10};
        Map<Integer,Integer> lookup = new HashMap<>();
        System.out.println(minimumCoinsNeeded(amount, coin, lookup));
    }

    private static int minimumCoinsNeeded(int amount, int[] coin, Map<Integer, Integer> lookup) {
        if(amount == 0)
            return 0;
        if(lookup.containsKey(amount))
            return lookup.get(amount);
        int minimum = Integer.MAX_VALUE;
        for(int i = 0; i < coin.length; i++){
            if(coin[i] > amount)
                continue;
            int coinsNeeded = minimumCoinsNeeded(amount-coin[i], coin, lookup);
            if(coinsNeeded < minimum)
                minimum = coinsNeeded;
        }
        minimum = (minimum == Integer.MAX_VALUE)? minimum : minimum + 1;
        lookup.put(amount, minimum);
        return minimum;
    }
}
