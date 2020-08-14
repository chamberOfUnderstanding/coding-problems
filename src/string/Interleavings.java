package string;

/**
 * @author 47un
 * 
 * Check if a string is the interleaving of two other strings
 * 
 * http://www.geeksforgeeks.org/check-whether-a-given-string-is-an-interleaving-of-two-other-given-strings/
 * 
 * Algorithm
 * """""""""
 * Scan the third string while maintaining indices of first and second strings.
 * If either of the two strings reach the end, stop the scan
 * If the current character of the third string is equal to the current character of the first string, advance first string
 * If the current character of the third string is equal to the current character of the second string, advance second string
 * If it's equal to neither, then return false (not an interleaving as a new character is present)
 * Once all characters of the third string are scanned, check if both the strings have hit the end, if yes, this is an interleaving
 * 
 * Generate all the interleavings of two strings
 * 
 * http://www.geeksforgeeks.org/print-all-interleavings-of-given-two-strings/
 *
 * Algorithm
 * """""""""
 * Maintain indices to first and second strings
 * If the third string's length is the sum of first and second, we have generated one interleaving, so print it
 * If the first string's index hasnt reached the end, add the character at that index to the third string and advance first index. Now recursively generate interleavings for this new string
 * Do the above process for the second string.
 */

public class Interleavings {
	public static void main(String...strings){
		String string1 = "ABC";
		String string2 = "XEFGH";
		String string3 = "XABCEFGH";
		Interleavings interleavings = new Interleavings();
		System.out.println(interleavings.isInterleaved(string1.toCharArray(),string2.toCharArray(),string3) ? "Yes\n" : "No\n");
		interleavings.generateInterleavings(string1, 0, string2, 0, "");
	}

	// O(third), O(first+second+third)
	public boolean isInterleaved(char[] string1, char[] string2, String string3){
		int string1Index = 0, string2Index = 0;
		for(char character : string3.toCharArray()){			
			if(string1Index < string1.length && character == string1[string1Index])
				string1Index++;
			else if(string2Index < string2.length && character == string2[string2Index])
				string2Index++;
			else
				return false;			
		}
		return true;
	}

	public void generateInterleavings(String string1, int index1, String string2, int index2, String interleaving){
		if(interleaving.length() == string1.length() + string2.length()){
			System.out.println(interleaving);
			return;
		}
		if(index1 < string1.length())
			generateInterleavings(string1, index1 + 1, string2, index2, interleaving + string1.charAt(index1));
		if(index2 < string2.length())
			generateInterleavings(string1, index1, string2, index2 + 1, interleaving + string2.charAt(index2));
	}
}
