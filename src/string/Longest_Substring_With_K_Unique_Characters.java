package string;

import java.util.BitSet;
import java.util.Scanner;

/***
 * Given a string you need to print longest possible substring that has exactly M unique characters.
 * If there are more than one substring of longest possible length, then print any one of them.
 * 
 * Samples
 * 
 * "aabbcc", k = 1
 * Max substring can be any one from {"aa" , "bb" , "cc"}.
 * 
 * "aabbcc", k = 2
 * Max substring can be any one from {"aabb" , "bbcc"}.
 * 
 * "aabbcc", k = 3
 * There are substrings with exactly 3 unique characters
 * {"aabbcc" , "abbcc" , "aabbc" , "abbc" } 
 * Max is "aabbcc" with length 6.
 * 
 * "aaabbb", k = 3 * 
 * There are only two unique characters, thus show error message. 
 * @author 47un
 *
 */
public class Longest_Substring_With_K_Unique_Characters {

	public static void main(String...strings){
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		int k = scanner.nextInt();
		System.out.println(longestSubstring(new StringBuilder(string),k));
		scanner.close();
	}

	private static StringBuilder longestSubstring(StringBuilder string, int k) {
		
		// Check the validity of the input string. Check if it has less than k unique characters (Condition 1)
		if(!isValid(string,k,'1'))
			return new StringBuilder("Invalid String");
		
		// Maintain a window, whose start and end are defined here
		int start = 0,end = 1;
		
		// Generate a substring from the contents of the window
		StringBuilder substring = null;
		
		// As long as the window has'nt reached the end of the string
		while(end<=string.length()){
			
			// Get a string from the window (substring from start to end of input string)
			substring = new StringBuilder(string.substring(start, end));
			
			// Check if this window is valid. Check if it has less than k unique characters (Condition 2)
			// Notice how Conditions 1 and 2 are opposite
			if(isValid(substring,k,'2'))
				
				// If the window is valid, add one more character to the window by increasing the end
				end++;
			else
				
				// If the window is invalid, remove a charcter from the window by increasing the start
				start++;			
		}
		return substring;
	}

	private static boolean isValid(StringBuilder string, int k,char condition) {
		
		// Maintain a bitset to store all unique characters of the string
		BitSet bitSet = new BitSet(26);
		
		// Assuming all characters are lowercase alphabets, subtract 'a' or 97 to get the index.
		// Set a bit at this index of the bitset
		for(int i=0;i<string.length();i++)
			bitSet.set(string.charAt(i)-'a');
		
		// Condition 1 : return false if the string has less than k unique characters
		// Condition 2 : return false if the string has more than k unique characters
		switch(condition){
		case '1' : 	return (bitSet.cardinality()<k)? false : true;
		case '2' :  return (bitSet.cardinality()>k)? false : true;
		}
		return false;
	}

}
