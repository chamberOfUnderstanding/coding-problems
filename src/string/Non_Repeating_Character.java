package string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author 47un
 * 
 * http://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
 * 
 * ALGORITHM v1
 * =========
 * Maintain a set and a list.
 * For each character seen, check if it is present in the set
 * If the set doesnt contain this character then it's appearing for the first time, so add it to the set and the list
 * If the set contains this character then it's a repeater, so remove it from the list
 * After the entire string is scanned, the list will have the non repeaters
 * 
 * 
 * ALGORITHM v2
 * =========
 * Maintain an character count array for each possible character (size = 255)
 * Scan the string and increase the count for each character that is seen
 * Scan the string again and for each character, check if the count in the count array is 1. If yes, the there's your non repeater
 * 
 */
public class Non_Repeating_Character {

	public static void main(String...strings){
		String input = "liquiball";
		Non_Repeating_Character nonRepeatingCharacter = new Non_Repeating_Character();
		nonRepeatingCharacter.findV1(input);
		nonRepeatingCharacter.findV2(input);

	}

	// For a string with no repetitions, this guy will take up O(n) for the list and O(n) for the set :'( 

	// O(n) , O(unique characters) for the set and O(non repeating characters) for the list
	private void findV1(String input){
		Set<Character> repeaters = new HashSet<>();
		List<Character> nonRepeaters = new LinkedList<>();
		for(int index = 0; index < input.length(); index++){
			char current = input.charAt(index);

			// New character? Add it to the set and the list
			if(!repeaters.contains(current)){
				repeaters.add(current);
				nonRepeaters.add(current);
			}

			// Already seen this character? Remove this character from the list
			else
				nonRepeaters.remove(new Character(current));
		}
		System.out.println(nonRepeaters.isEmpty()? "They all repeat" : nonRepeaters.get(0));
	}

	// O(n), O(1)
	private void findV2(String input){
		int[] count = new int[255];
		for(int index = 0; index < input.length(); index++)
			count[input.charAt(index)]++;
		for(int index = 0; index < input.length(); index++){
			char current = input.charAt(index); 
			if(count[current] == 1){
				System.out.println(current);
				break;
			}
		}
	}
}
