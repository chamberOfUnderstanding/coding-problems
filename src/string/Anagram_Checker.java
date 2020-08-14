package string;

/**
 * @author 47un
 *
 * https://practice.geeksforgeeks.org/problems/anagram/0
 * 
 * If the lengths are different, quit
 * Keep a character count array of size 256 (= number of possible ascii values)
 * Scan both strings
 * 	For each character in string1, increase the character count of that character in the array
 *  For each character in string2, decrease the character count of that character in the array
 * Now if the strings are anagrams, the count array will be full of zeroes
 * Scan the array and verify that
 * 
 * TIME  :  O(n) //Number of characters in the strings
 * SPACE :  O(1)
 * 
 */

public class Anagram_Checker {
	
	public static void main(String...x){
		String sentence1 = "heavenandhell";
		String sentence2 = "hheeeaavnndll";
		System.out.println(new Anagram_Checker().areTheyAnagrams(sentence1, sentence2));
	}
		
	private boolean areTheyAnagrams(String sentence1, String sentence2){	
		if(sentence1.length() != sentence2.length())
			return false;
		int[] characterCount = new int[255];
		for(int index = 0; index < sentence1.length(); index++){
			characterCount[sentence1.charAt(index)]++;
			characterCount[sentence2.charAt(index)]--;
		}
		for(int index = 0; index < 255; index++)
			if(characterCount[index] != 0)
				return false;
		return true;
	}
}
