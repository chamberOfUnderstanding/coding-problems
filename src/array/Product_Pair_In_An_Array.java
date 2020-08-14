package array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 47un
 * 
 * Given an array of integers check whether there are two numbers present with given product.
 * 
 * http://www.geeksforgeeks.org/pair-with-given-product-set-1-find-if-any-pair-exists/
 * 
 * =========
 * METHOD 1
 * =========
 * Scan the array
 *  If the target is 0 and the item is 0, return true (Since the target is a 0 and one item is a 0, no need to find the other half)
 *  If the item is not 0 (0 items are useless otherwise)
 *   Check if the hashSet contains this item (Someone else is looking for it)
 *    If yes, return true
 *   Else if this item perfectly divides the target (Quotient > 0 and Remainder = 0)
 *    Add both the item and the quotient to the hashSet 
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Product_Pair_In_An_Array {

    public static void main(String[] args) {
        int[] array = {5, 7, 9, 22, 15, 344, 92, 8};
        int target  = 46;
        System.out.println(isProductPresent(target, array)? "Yes" : "No");
    }

    private static boolean isProductPresent(int target, int[] array){
        Set<Integer> hashSet = new HashSet<>();
        for(int element : array)
            if(element == 0 && target == 0)
                return true;
            else if(element != 0)
                if(hashSet.contains(element))
                    return true;
                else if(target / element > 0 && target % element == 0){
                    hashSet.add(element);
                    hashSet.add(target / element);
                }
        return false;
    }
}
