package string;

import java.util.Arrays;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 */

public class String_Permutations{
	public static void main(String...strings){
		String input = "mzza";
		String_Permutations permutations = new String_Permutations();
		System.out.println("\nPermuting without repeating characters in the input");
		permutations.permuteV1(input.toCharArray(), 0 , input.length()-1);
		System.out.println("\nPermuting while repeating characters in the input");
		permutations.permuteV2(input, new char[input.length()], 0);
		System.out.println("\nPermutations for a string with duplicate characters");
		permutations.permuteV3(input.toCharArray());		
	}

	// This generates permutations without repeating the characters in the input string
	// Also the input string should not have any repetitions

	// n! permutations for a string of length n and each permutation costs O(n)
	// The stack at any time can grow up to (n-1)! 

	// O(n*n!) , O(n-1)!
	private void permuteV1(char[] input, int start, int end){

		// Scanned all characters? Print the permutation
		if(start == end)
			System.out.println(input);
		else
			// Permute till the end
			for(int index = start ; index <= end ; index++){

				// Swap two characters
				// For each iteration, the start value never changes, the index value changes to values to its right
				// Hence we get to generate the permutations
				swap(input, start, index);

				// Permute the rest
				permuteV1(input, start+1, end);

				// Backtrack
				swap(input, start, index);
			}
	}

	
	// Here, the characters in the input string can be repeated. So for a string of length n, the permutation is n*n
	// Maintain a temporary string to which characters of the original are appended

	// O(n*n) TIME , O(n*n) SPACE for the Strings created, O(n) stack space 
	private void permuteV2(String input, char[] temp, int tempIndex){

		// If the temp string is full, print it
		if(input.length() == tempIndex)
			System.out.println(temp);

		// Else recursively call this method, after appending one character of the input to the temp
		// Do this for all characters in the input
		else
			for(int i=0; i<input.length(); i++){
				temp[tempIndex] = input.charAt(i);
				permuteV2(input, temp, tempIndex+1);
			}
	}


	// Here the input string itself can have repeating characters
	// O(n2+n!)
	// O(1)
	private void permuteV3(char[] input){
		int length = input.length;
		
		// Sort the characters in the string
		Arrays.sort(input);
		while(true){
			System.out.println(input);
			int lowestIndex = length-2;
			
			// Scan from right to left until you find a something SMALLER than its value to the right(a pit)
			while(lowestIndex >= 0 && !(input[lowestIndex] < input[lowestIndex+1]))
				lowestIndex--;
			
			// If you fell past the leftmost side, you are dead, so leave!
			if(lowestIndex < 0)
				break;
			
			// Now start again from the right to the left
			int highestIndex = length-1;
			
			// Scan till the point where you fell first until you see a value that's LARGER
			while(highestIndex > lowestIndex && !(input[highestIndex] > input[lowestIndex]))
				highestIndex--;
			
			// Swap these two characters
			swap(input, lowestIndex, highestIndex);
			
			// Sort the characters from the point you fell to the end
			Arrays.sort(input, lowestIndex+1, length);
		}
	}	

	private void swap(char[] input, int index1, int index2){
		if(input[index1] == input[index2])
			return;
		char temp = input[index1];
		input[index1] = input[index2];
		input[index2] = temp;
	}
}
