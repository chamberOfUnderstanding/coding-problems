package dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 47un
 *
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins.
 * Find how many ways can we make the change. The order of coins doesn't matter.
 * For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
 * So output should be 4. For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
 * So the output should be 5.
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 * 
 * METHOD 1 : Recursion
 *            If the amount = 0, there is 1 way of achieving an amount of 0, by not considering any coin
 *            If the amount < 0, there is no way to do it
 *            If there are no coins left over, there's no way! 
 *            Find out how many ways change FOR THE SAME AMOUNT can be made by including previous coin, and excluding it
 *              Including the coin will reduce the amount by value of the coin, the number of coins remains the same as there are an infinite supply of coins
 *                  changeMakerRecursive(coins, availableCoins, amount - coins[availableCoins - 1])
 *              Excluding the coin will reduce the available coins by 1 but wont change the amount
 *                  changeMakerRecursive(coins, availableCoins - 1, amount)
 *            
 * TIME    : O(2^n)     // Power set, number of sub problems
 * SPACE   : O(n)       // Height of the tree with 2^n sub problems => n
 * 
 * METHOD 2 : Dynamic Programming
 *            For each amount, coin combination,
 *              If the previous coin cant be included, amount stays the same and #coins reduce,i.e. reuse the solution in hand
 *                  lookup[amount][coin] = lookup[amount][coin-1];
 *              Check if this coin can be included
 *              if(coins[coin - 1] <= amount)
 *              If yes, reduce the amount by the value and add to the current result
 *                  lookup[amount][coin] += lookup[amount - coins[coin - 1]][coin];
 * 
 * TIME    : O(coins * amount)
 * SPACE   : O(coins * amount)             
 * 
 * METHOD 3 : Dynamic Programming (Uses less space)
 *            The coins must be sorted in increasing order
 *            Only the amounts above the value of the current coin need to be considered, there's no way this coin will be contribute to the smaller amounts
 *            So only the amounts are kept track of, hence O(amount)
 *
 * TIME    : O(coins * amount)
 * SPACE   : O(amount)
 *
 * Ways to make change
 * 
 * For each coin, add it to the change if it is <= amount and recurse while keeping the number of coins the same
 * When the recursion ends, remove this coin from the change
 * If the amount hits 0 any time, print the solution and quit recursing
 * 
 * TIME    : O(2^n)  // Each coin can/cannot be considered
 * SPACE   : O(n)
 */

public class Coin_Change_Problem_Making_Change {

    public static void main(String[] args) {
        int[] denominations = {2, 5, 3};
        int amount  = 10;
        System.out.println("Recursive  : " + changeMakerRecursive(denominations, denominations.length, amount));
        System.out.println("Dynamic    : " + changeMakerDynamicProgramming(denominations, amount));
        System.out.println("Dynamic v2 : " + changeMakerDynamicProgrammingv2(denominations, amount));
        System.out.println("Ways to make change : ");
        waysToMakeChangeRecursive(amount, denominations);
    }

    private static int changeMakerRecursive(int[] denominations, int availableCoins, int amount) {
        // only 1 way to achieve 0 -> Dont include any coins
        if(amount == 0)
            return 1;
        
        // no ways to achieve < 0
        // no ways to achieve anything if there are no coins
        if(amount < 0 || availableCoins <= 0)
            return 0;
        
        // (1) including the coin will reduce the amount by the coins denomination
        // (2) excluding the coin will reduce the number of coins by 1
        // total = (1) + (2)    
        return changeMakerRecursive(denominations, availableCoins, amount - denominations[availableCoins - 1])
                + changeMakerRecursive(denominations, availableCoins - 1, amount);
    }

    private static int changeMakerDynamicProgramming(int[] denominations, int maxAmount) {
        int numberOfCoins = denominations.length;
        int[][] lookup = new int[maxAmount + 1][numberOfCoins + 1];
        Arrays.fill(lookup[0], 1);
        for(int amount = 0; amount <= maxAmount; amount++)
            for(int coin = 1; coin <= numberOfCoins; coin++) {
                lookup[amount][coin] = lookup[amount][coin-1];
                if(denominations[coin - 1] <= amount)
                    lookup[amount][coin] += lookup[amount - denominations[coin - 1]][coin];
            }
        return lookup[maxAmount][numberOfCoins];
    }

    private static int changeMakerDynamicProgrammingv2(int[] denominations, int target) {
        int numberOfCoins = denominations.length;
        
        // sort the demonimations to go from low to high denominations
        Arrays.sort(denominations);
        
        // prepare a lookup for ways to achieve all amounts till the target amount
        int[] lookup = new int[target + 1];
        
        // only 1 way to achieve 0 -> By not including any coin
        lookup[0] = 1;
        
        // loop for all available denominations
        for(int coin = 0; coin < numberOfCoins; coin++)
            
            // loop from the lowest denomination
            // there are 0 ways to achieve any amount below the current denomination
            // so they can be skipped
            for(int amount = denominations[coin]; amount <= target; amount++)
                
                // ways to achieve the current amount 
                //   = ways to achieve this amount if this coin was not included
                //     + ways to achieve this amount if this coin was included (this reduces the amount)
                // e.g amount = 10, denominations = 5, 10
                // for coin = 5
                // lookup[5] = lookup[5] + lookup[10-5]
                // lookup[5] = 0 + lookup[0] => 1
                // there's one way to achieve 5 with a denomination of 5
                // lookup[10] = lookup[10] + lookup[5] => 1
                // for coin = 10, lookup[10] will already be 1 thanks to the previous step
                // lookup[10] = lookup[10] + lookup[10-10] => 1 + 1 => 2
                lookup[amount] += lookup[amount - denominations[coin]];
        return lookup[target];
    }

    private static void waysToMakeChangeRecursive(int maxAmount, int[] denominations) {
        List<Integer> change = new ArrayList<>();
        findChanges(0, maxAmount, change, denominations);
    }

    private static void findChanges(int index, int maxAmount, List<Integer> change, int[] denominations) {
        if(maxAmount == 0) {
            printChange(change);
            return;
        }
        for(int coin = index; coin < denominations.length; coin++)
            if(denominations[coin] <= maxAmount) {
                change.add(denominations[coin]);
                findChanges(index, maxAmount - denominations[coin], change, denominations);
                change.remove(change.size() - 1);
            }
    }

    private static void printChange(List<Integer> change) {
        for(int denomination : change)
            System.out.print(denomination + " ");
        System.out.println();
    }
}
