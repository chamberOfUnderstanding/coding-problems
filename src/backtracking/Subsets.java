package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 47un
 * 
 * Given an array (set of items) generate all sub arrays (sub sets)
 * 
 * =========
 * METHOD 1
 * =========
 * Add an item to the current sub set
 * Recurse for the next item
 * Remove the last added item
 *  
 * If input contains duplicates then check if the item is same as the previous item, if yes, do not add it
 * 
 * TIME     : O(2^n) // 2^n possible sub sets
 * SPACE    : O(n)
 *
 * 
 */

public class Subsets {

	public static void main(String[] args) {
		int[] array  = {8, 1, 8, 6, 8};
		generateSubsetsDistinctInputs(array);
		generateSubsetsDuplicateInputs(array);
	}

	private static void generateSubsetsDistinctInputs(int[] array) {
	    Arrays.sort(array);
        List<List<Integer>> powerset = new ArrayList<>();
        generateSubsetsDistinctInputs(powerset, new ArrayList<>(), array, 0);
        System.out.println(powerset);
    }

    private static void generateSubsetsDistinctInputs(List<List<Integer>> powerset, List<Integer> subset, int[] array, int index) {
		powerset.add(new ArrayList<>(subset));
		for(int i = index; i < array.length; i++) {
			subset.add(array[i]);
			generateSubsetsDistinctInputs(powerset, subset, array, index + 1);
			subset.remove(subset.size() - 1);
		}
	}

    private static void generateSubsetsDuplicateInputs(int[] array) {
        Arrays.sort(array);
        List<List<Integer>> powerset = new ArrayList<>();
        generateSubsetsDuplicateInputs(powerset, new ArrayList<>(), array, 0);
        System.out.println(powerset);
    }

	private static void generateSubsetsDuplicateInputs(List<List<Integer>> powerset, List<Integer> subset, int[] array, int index) {
		powerset.add(new ArrayList<>(subset));
		for(int i = index; i < array.length; i++) {
			if(i > index && array[i] == array[i - 1])
				continue;
			subset.add(array[i]);
			generateSubsetsDuplicateInputs(powerset, subset, array, index + 1);
			subset.remove(subset.size() - 1);
		}
	}
}
