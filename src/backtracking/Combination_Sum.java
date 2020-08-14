package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 47un
 *
 * Given an array of integers A and a sum B, find all unique combinations in A where the sum is equal to B.
 * Each number in A may only be used once in the combination.
 * 
 * https://discuss.leetcode.com/topic/46161/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
 * 
 * =======================
 * METHOD 1 : Backtracking 
 * =======================
 * All the combinations are stored in a list of lists (Better than storing them in a string)
 * Sort the input array
 * Call the method while passing the allCombinations list, a new list that holds the first combination, the array, the remaining value (initially = sum), and starting index as 0
 *  If remaining sum < 0, then no more values can be added to this combinations, so return
 *  If remaining = 0, then prepare a copy of the current combination and add it to the list of all combinations
 *  Else scan each item from 'index' to the end of the list
 *   Add the first item into the combination
 *   Recurse for the new combination while reducing the remaining sum by the array's value at that index. Also no need to advance the index as duplicates are allowed
 *   Remove the item that was last added from the combination
 * 
 * If duplicates were not allowed,
 *  While looping through the items, if the index is not the start index and this item is same as the previous item, skip this item
 *   Since the array is sorted initially elements of equal value appear together, so just skip it bruh.
 *  Also while recursing, advance to the next index.
 *              
 * TIME    : O(2^n) // Each item can / cannot be included in the sum
 * SPACE   : O(n)
 * 
 */

public class Combination_Sum {

	public static void main(String[] args) {
		//int[] array  = {10, 1, 2, 7, 6, 1, 5};
		int[] array  = {7, 2, 6, 5};
		int sum = 16;
		findCombinationSumIncludingDuplicates(array, sum);
		findCombinationSumExcludingDuplicates(array, sum);
	}

	private static void findCombinationSumIncludingDuplicates(int[] array, int sum) {
	    Arrays.sort(array);
        List<List<Integer>> allCombinations = new ArrayList<>();
        findCombinationSumIncludingDuplicates(allCombinations, new ArrayList<>(), array, sum, 0);
        System.out.println(allCombinations);
    }

    private static void findCombinationSumIncludingDuplicates(List<List<Integer>> allCombinations, List<Integer> combination, int[] array, int remaining, int index) {
		if(remaining < 0)
			return;
		else if(remaining == 0)
			allCombinations.add(new ArrayList<>(combination));
		else {
			for(int i = index; i < array.length; i++) {
				combination.add(array[i]);
				findCombinationSumIncludingDuplicates(allCombinations, combination, array, remaining - array[i], index);
				combination.remove(combination.size() - 1);
			}
		}
	}
	
    private static void findCombinationSumExcludingDuplicates(int[] array, int sum) {
        Arrays.sort(array);
        List<List<Integer>> allCombinations = new ArrayList<>();
        findCombinationSumExcludingDuplicates(allCombinations, new ArrayList<>(), array, sum, 0);
        System.out.println(allCombinations);
    }
    
	private static void findCombinationSumExcludingDuplicates(List<List<Integer>> allCombinations, List<Integer> combination, int[] array, int remaining, int index) {
		if(remaining < 0)
			return;
		else if(remaining == 0)
			allCombinations.add(new ArrayList<>(combination));
		else {
			for(int i = index; i < array.length; i++) {
				if(i > index && array[i] == array[i - 1])
					continue;
				combination.add(array[i]);
				findCombinationSumIncludingDuplicates(allCombinations, combination, array, remaining - array[i], index + 1);
				combination.remove(combination.size() - 1);
			}
		}
	}
}
