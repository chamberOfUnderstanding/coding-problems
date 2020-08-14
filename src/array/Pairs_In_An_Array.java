package array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 47un
 *
 * Generate all possible unique pairs in an array
 * Generate all possible pairs in an array
 * 
 * http://stackoverflow.com/questions/2392652/java-find-all-possible-pairs-in-an-array
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Iterate
 *            Create a string out of the two elements
 *            If either this string or the reverse of it (Reverse of the pair and NOT THE STRING) is present in the pairs list, continue
 *            Else add this pair to the pair list
 *            Number of unique pairs = nC2 where n is the number of UNIQUE items
 *
 * TIME     : O(n^2)
 * SPACE    : O(pairs*l) where pairs is the total number of pairs and l is the length of one pair string
 * 
 */

public class Pairs_In_An_Array {
    
    public static void main(String[] args) {
        int[] array = {0, 1, 0, 2, 4, 5, 5};
        Set<String> uniquePairs = new Pairs_In_An_Array().findAllUniquePairs(array);
        Set<String> pairs       = new Pairs_In_An_Array().findAllPairs(array);
        System.out.print("All " + uniquePairs.size() + " unique pairs\n");
        System.out.println(uniquePairs);
        System.out.print("\nAll " + pairs.size() + " possible pairs\n");
        System.out.print(pairs);
    }

    private Set<String> findAllUniquePairs(int[] array) {
        Set<String> uniquePairs = new HashSet<>();
        for(int i = 0; i < array.length; i++)
            for(int j = i + 1; j < array.length; j++) {
                if(array[i] == array[j])
                    continue;
                String possiblePair1 = new String(array[i] + ":" + array[j]);
                String possiblePair2 = new String(array[j] + ":" + array[i]);
                if(!uniquePairs.contains(possiblePair1) && !uniquePairs.contains(possiblePair2))
                    uniquePairs.add(possiblePair1);
            }
        return uniquePairs;
    }

    private Set<String> findAllPairs(int[] array) {
        Set<String> allPairs = new HashSet<>();
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array.length; j++)
                if(array[i] != array[j]) {
                    String possiblePair1 = new String(array[i] + ":" + array[j]);
                    if(!allPairs.contains(possiblePair1))
                        allPairs.add(new String(array[i] + ":" + array[j]));
                }
        return allPairs;
    }   
}
