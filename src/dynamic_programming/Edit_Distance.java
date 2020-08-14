package dynamic_programming;

/**
 * @author 47un
 * 
 * Given two strings of size m, n. Find minimum number of operations required to convert one string into another.
 * More precisely,we have to find Edit Distance between two strings.
 * 
 * https://algorithmsbyme.wordpress.com/2012/05/26/levenshtein-distanceedit-distance-using-recursion-and-dynamic-programming/
 * 
 * ====================
 * METHOD 1 : Recursive 
 * ====================
 * If either string is null, quit
 * If index of either string is 0, return the index of the other (That many operations will be left, duh!)
 * Check if the last two characters of the strings are equal, if yes recurse for the previous 2 characters
 * Else :
 *  1. Insertion : If a character from string2 is inserted into string1, then there are m characters in string1 and n - 1 in string2
 *  2. Deletion  : If a character from string1 is deleted, then there are m - 1 characters in string1 and n in string2
 *  3. Replacement : If a character from string1 is replaced, then there are m - 1 characters in string1 and n - 1 in string2
 *  Find the minimum edit distance after doing the above three operations for the current character
 *             
 * TIME    : O(3^n) // Since there're 3 possible operations for each character, recursion tree is ternary
 * SPACE   : O(logn)
 * 
 * =============
 * METHOD 2 : DP 
 * =============
 * Pretty much self explanatory
 * 
 * http://www.ideserve.co.in/learn/edit-distance-dynamic-programming 
 * 
 * TIME    : O(m * n)
 * SPACE   : O(m * n)
 * 
 */

public class Edit_Distance {

	public static void main(String[] args) {
		String string1 = "INTENTION";
		String string2 = "EXECUTION";
		System.out.println(findEditDistanceDP(string1, string2));
		System.out.println(findEditDistanceRecursive(string1, string2));
	}

	private static int findEditDistanceDP(String string1, String string2) {
		int rows    = string1.length() + 1;
		int columns = string2.length() + 1;
		int[][] editDistance = new int[rows][columns];
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++) {
				if(i == 0)
					editDistance[i][j] = j;
				else if(j == 0)
					editDistance[i][j] = i;
				else if(string1.charAt(i - 1) == string2.charAt(j - 1))
					editDistance[i][j] = editDistance[i - 1][j - 1];
				else
					editDistance[i][j] = 1 + minimum(editDistance[i - 1][j], editDistance[i][j - 1], editDistance[i - 1][j - 1]);
			}
		return editDistance[rows - 1][columns - 1];
	}

	private static int findEditDistanceRecursive(String string1, String string2) {
		return findEditDistanceRecursive(string1, string2, string1.length(), string2.length());
	}

	private static int findEditDistanceRecursive(String string1, String string2, int m, int n) {
		if(string1 == null || string2 == null)
			return -1;
		if(m == 0)
			return n;
		if(n == 0)
			return m;
		if(string1.charAt(m - 1) == string2.charAt(n - 1))
			return findEditDistanceRecursive(string1, string2, m - 1, n - 1);
		return 1 + minimum(
				       findEditDistanceRecursive(string1, string2, m, n - 1), 
				       findEditDistanceRecursive(string1, string2, m - 1, n), 
				       findEditDistanceRecursive(string1, string2, m - 1, n - 1));
	}

	private static int minimum(int x, int y, int z) {		
		return Math.min(x, Math.min(y, z));
	}
}
