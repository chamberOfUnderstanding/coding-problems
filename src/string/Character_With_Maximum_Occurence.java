package string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/return-maximum-occurring-character-in-the-input-string/
 */

public class Character_With_Maximum_Occurence {
	public static void main(String...x){
		String input = "Don'tletyourdreamsbedreams!";
		Character_With_Maximum_Occurence maxOccurrence = new Character_With_Maximum_Occurence();
		System.out.println(maxOccurrence.getMaximumOccurrenceII(input));
	}

	@SuppressWarnings("unused")
	
	// Scans the string once, Needs space = number of unique characters in the string

	// O(n) , O(m)
	private char getMaximumOccurrenceI(String input) {
		char maxChar = '\u0000';
		int maxCount = Integer.MIN_VALUE;
		Map<Character,Integer> map = new HashMap<>();		
		for(char current : input.toCharArray()){

			// If the map contains this key, update it's count else the count is 1 (first occurence)
			int count = map.containsKey(current)? map.get(current) + 1 : 1;

			// Update the map with the count
			map.put(current,count);

			// If the count is greater than max, update max count and max char
			if(count > maxCount){
				maxCount = count;
				maxChar = current;
			}
		}
		return maxChar;
	}

	// O(n) , O(256)
	private char getMaximumOccurrenceII(String input){

		// Array of size 255, to store all possible characters
		int[] allPossibleCharacters = new int[255];
		int maxCount = Integer.MIN_VALUE;
		char maxChar = '\u0000';
		for(char current : input.toCharArray()){
			int ascii = current;

			// Increase the count of that character
			allPossibleCharacters[ascii]++;

			// If the count exceeds the maximum value, update maximum count and char with max occurrence
			if(allPossibleCharacters[ascii] > maxCount){
				maxCount = allPossibleCharacters[ascii];
				maxChar = current;				
			}			
		}
		return maxChar;
	}
}
