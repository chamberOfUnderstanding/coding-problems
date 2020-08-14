package string;

/***
 * Given a string, find the first non-repeating character in it.
 * For example, if the input string is “GeeksforGeeks”, then output should be ‘f’ and if input string is “GeeksQuiz”, then output should be ‘G’.
 * @author 47un
 *
 */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class First_Non_Repeating_Character {
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			System.out.print("\nEnter String ");
			String string = scanner.nextLine();
			System.out.print("\nFirst non repeating character is "+firstNonRepeatingCharacter(string));
		}
	}

	private static char firstNonRepeatingCharacter(String string) {
		
		//Map maintains character, occurrence count mapping
		Map<Character,Integer> map = new LinkedHashMap<>();
		
		//Populate the map
		for(char c : string.toCharArray()){
			
			//If the character is already in the map, increase the count
			if(map.containsKey(c))
				map.put(c,map.get(c)+1);
			
			//Else the count is 1
			else
				map.put(c,1);
		}
		
		//Retrieve the key set and iterate it
		Set<Character> keys = map.keySet();
		for(char c : keys){
			
			//If any of the keys (characters) is having a count of 1, BINGO!
			if(map.get(c)==1)
				return c;
		}
		
		//Nope. None repeat
		return '!';
	}
}
