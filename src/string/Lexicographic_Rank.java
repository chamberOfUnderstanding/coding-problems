package string;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/lexicographic-rank-of-a-string/
 * 
 * Algorithm
 * =========
 * Fix the first character of the input and find out the possible permutations that can lexicographically come before this one.
 * e.g. For the string, "string", 4 characters (i,n,g,r) are smaller than s. So possible number of strings that are lexicographically
 *      smaller than all strings starting with s are => 4*5!
 * Now fix the second character and repeat the process.
 * Keep adding up the number of strings that can lexicographically appear before a string => Rank 
 * 
 * Note : Assignment operators return the value that got assigned to the variable
 *        e.g. int y; System.out.println(y=222); will print 222
 */

public class Lexicographic_Rank {

	public static void main(String...x){
		String input = "string";		
		Lexicographic_Rank lexicographic_Rank = new Lexicographic_Rank();
		System.out.println(lexicographic_Rank.getRank(input));
	}

	// O(n2) , O(1)
	private int getRank(String input) {
		int rank = 1;
		int length = input.length();
		int factorial = factorial(length);
		for(int i = 0; i < length; i++){
			factorial /= length-i;
			rank += findSmallerCharactersToTheRight(input, i) * factorial;
		}
		return rank;
	}

	private int findSmallerCharactersToTheRight(String input, int start) {
		int count = 0;
		char current = input.charAt(start);
		for(int i = start+1; i < input.length(); i++)
			count += input.charAt(i) < current? 1 : 0;
		return count;
	}

	private int factorial(int number) {
		return number <= 1? 1 : number * factorial(number-1);
	}
}
