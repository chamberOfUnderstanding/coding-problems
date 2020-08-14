package dynamic_programming;

import java.util.Arrays;

/**
 * @author 47un
 *
 * Check whether a given set of numbers can be partitioned into two subsets such that the sum of elements in both subsets is same or not. 
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
 * 
 * METHOD 1 : Recursive
 *            Generate all subsets, add up the items, if any = sum/2 return true
 *     
 * TIME     : O(2^n)
 * SPACE    : O(1)
 * 
 * METHOD 2 : Dynamic Programming
 * 
 * http://www.ideserve.co.in/learn/set-partition-problem-dynamic-programming
 * 
 * If the set is cut into two subsets with equal values, then both these subsets sum up to sum/2
 * So all we need to do is to find a subset that gives a sum of sum/2
 * If the sum of the items in the set is odd, then there's no possible partition, exit!
 * For an even sum, maintain a lookup table that holds details on the subsets and the sum they can add up to.
 * Lookup table is of size halfSum + 1 x cardinality + 1 and the base case is at (0,0) and final result is at (halfSum,cardinality)
 * The lookup table is filled the following way :
 *  lookup[i][j] = true; means there is a subset in the set from 0..j that can give a sum i
 *  For all items, there is a exactly one way to get the sum 0, the empty set. The empty set is a subset of all sets
 *       lookup[0][item] = true;
 *  For each item, j if lookup[i][j-1] is true (subset till j-1 can give a sum of i) then :
 *       lookup[i][j] = true;    // Set till j-1 is a subset of set till j, if you already saw the sum, then it applies here
 *  See if adding the item j-1 to the sum is still within halfSum, if yes update that sum value for the item j
 *       lookup[i + set[j-1]][j] = true
 *   
 * This method has horrid performance for large sums. 
 * In such a situation, remove the common value from all the items and run the same algorithm.
 * e.g. {1788,2901,4442,1799} gives a sum of 10k
 * Removing the common value, 1788 => {0, 1113, 2654, 11} which has a sum of <5k (Cutting the above value by half!!)
 * The results are the same if done with/without the 10k
 * 
 * TIME  : O(cardinality * halfSum)
 * SPACE : O(cardinality * halfSum)
 * 
 * Printing the partitions
 * ***********************
 * To print the first partition, 
 * 
 * Start with the lower right corner of the lookup table
 * Case 1 :If the sum is possible for this item, move down to the next item
 * Keep moving down the items as long as the sum is possible
 * Case 2 :If a false is seen for the first case
 *  Move to the cell with sum = sum - set[current item] of the current item
 *      Here we are checking if the sum is still possible after excluding the current item
 *  If this is a true, print the item, update the sum and move down an item
 * Case 3 : If both of the above are false, quit
 * 
 * To print the second partition,
 * 
 * Start with the lower right corner
 * If this item is < sum, print it, exclude it from the the sum, update sum and move down the items
 * 
 * TIME  : O(cardinality)
 * SPACE : O(1)
 */

public class Partition_Problem {

    public static void main(String[] args) {
        int[] set = {1,2,3};
        System.out.println(isPartitionableRecursive(set)? "Can be partitioned" : "Cannot be partitioned");
        System.out.println(isPartitionableDynamicProgramming(set)? "Can be partitioned" : "Cannot be partitioned");
    }

    private static boolean isPartitionableRecursive(int[] set) {
        int sum = getSum(set);
        if(sum % 2 == 1)
            return false;
        int cardinality = set.length;
        for(int i = 0; i < 1 << cardinality; i++) {
            int subsetSum = 0;
            for(int j = 0; j < cardinality; j++)
                if((i & 1 << j) > 0)
                    subsetSum += set[j];
            if(subsetSum == sum/2)
                return true;
        }
        return false;
    }

    private static boolean isPartitionableDynamicProgramming(int[] set) {
        int halfSum = getSum(set)/2;
        int cardinality = set.length;
        boolean[][] lookup = new boolean[halfSum + 1][cardinality + 1];
        Arrays.fill(lookup[0], true);
        for (int sum = 0; sum <= halfSum; sum++)
            for (int item = 1; item <= cardinality; item++)
                if (lookup[sum][item-1]){
                    lookup[sum][item] = true;
                    if (sum + set[item-1] <= halfSum)
                        lookup[sum + set[item-1]][item] = true;
                }
        if(lookup[halfSum][cardinality])
            printPartition(cardinality, halfSum, set, lookup);
        return lookup[halfSum][cardinality];
    }

    private static int getSum(int[] set) {
        int sum = 0;
        for(int item : set)
            sum += item;
        return sum;
    }

    private static void printPartition(int cardinality, int halfSum, int[] set, boolean[][] lookup) {
        int sum  = halfSum;
        int item = cardinality;
        System.out.print("Subsets : {");
        while(sum > 0 && item > 0) {
            if(lookup[sum][item - 1])
                item--;
            else if(lookup[sum - set[item - 1]][item - 1]) {
                System.out.print(set[item - 1] + " ");
                sum -= set[item - 1];
                item--;
            }
            else
                break;
        }
        System.out.print("} {");
        sum = halfSum;
        item = cardinality;
        while(sum > 0 && item > 0) {
            if(set[item - 1] <= sum)
                if(lookup[sum - set[item - 1]][item - 1]) {
                    System.out.print(set[item - 1] + " ");
                    sum -= set[item - 1];
                }
            item--;
        }
        System.out.println("}");
    }

    @SuppressWarnings("unused")
    private static void printLookUp(boolean[][] lookup) {
        for(boolean[] row : lookup) {
            for(boolean cell : row)
                System.out.print(cell? "T " : "F ");
            System.out.println();
        }
    }
}
