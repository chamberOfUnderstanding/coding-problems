package bit;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author 47un
 *
 * Given an array of integers that might contain duplicates, return all possible subsets.
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * The subsets must be sorted lexicographically.
 * 
 * http://www.geeksforgeeks.org/find-distinct-subsets-given-set/
 * http://www.geeksforgeeks.org/power-set/ 
 * http://javabypatel.blogspot.in/2015/10/all-subsets-of-set-powerset.html
 * 
 * ===========================
 * METHOD 1 : Bit manipulation 
 * ===========================
 * Sort the array to avoid considering an item more than the number of times it is available in the set
 * For a set of n items, the number of subsets are 2^n (Powerset)
 * Generate a subset and add it to the list of subsets if it doesnt contain it
 * 
 * Powerset generation
 * *******************
 * Find the number of subsets, 2^n
 * Loop for each subset, i, from 0 to 2^n
 *  Loop for each bit, j, in the subset from 0 to n
 *      If bit j is set in i, add set[j] to the subset[i] 
 * 
 * That weird bit manipulation line (i & 1 << j) > 0 explanation
 * 
 * NOTE : LSB is bit 0 (Starts with zero like an array)
 * To check if 4th bit from LSB is set in 11101, (Yes it is)
 *          i = 11101 (Given number)
 *          j = 00100 (Bit to be checked)
 *     1 << j = 01000 (Shift one bit of j to the left)
 * i & 1 << j = 01000 (& this with the number, get the result)
 *  01000 > 0 = Yes   (If bit 4 is set in the number this value will be > 0)
 * To check if 1st bit from LSB is set in 11101, (No it is not!)
 *          i = 11101 (Given number)
 *          j = 00001 (Bit to be checked)
 *     1 << j = 00010 (Shift one bit of j to the left)
 * i & 1 << j = 00000 (& this with the number, get the result)
 *  00000 > 0 = Nope  (If bit 1 is set in the number this value will be > 0)
 * 
 * ====
 * TLDR
 * ====
 * The above logic works on the fact that : to check if jth bit of a number is set, AND the number with 2^j
 * Because, in the binary of 2^j, only the jth bit will be set. So if the number is having its jth bit set as well, the AND operation will give a value > 0
 * And any number when left shifted by 1 gives 2^number
 * 
 * Left shift operation on a number
 * ********************************
 * 
 * 1<<3 => 2^3
 * 2<<3 => 2^ (3+1)
 * 3<<3 => 2^ (3+2)
 * .......
 * n << x = 2 ^ (x + n - 1)
 * 
 * Right shift operation on a number
 * *********************************
 * 
 * 3>>1 = 3/2
 * 3>>2 = 3/4
 * n >> x = x / 2^n
 * 
 * TIME    : O(2^n)
 * SPACE   : O(1)
 * 
 */

public class Subsets {

    public static void main(String[] args) {
        int[] set = {8, 1, 8, 6, 8};
        printSubsets(set);
    }

    private static void printSubsets(int[] set){
        Arrays.sort(set);
        Set<String> subsets = new TreeSet<>();
        int cardinality     = set.length;
        for (int i = 0; i < (1 << cardinality); i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < cardinality; j++)
                if ((i & 1 << j) > 0)
                    subset.append(set[j]).append(" ");
            subsets.add(subset.toString());
        }
        for(String subset : subsets)
            System.out.println("(" + subset.trim() +")");
    }
}
