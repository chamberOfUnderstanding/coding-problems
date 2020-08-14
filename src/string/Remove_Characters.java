package string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 47un
 * 
 * http://www.geeksforgeeks.org/remove-characters-from-the-first-string-which-are-present-in-the-second-string/
 */
public class Remove_Characters {
	public static void main(String...x){
		String string1 = "abcdaebfc";
		String string2 = "abc";
		new Remove_Characters().remove(new StringBuilder(string1), string2);
	}
	
	// O(longer string) , O(second string)
	private void remove(StringBuilder stringBuilder, String string2) {
		Set<Character> set = new HashSet<>();
		
		// Store characters of second string onto a set
		for(int index = 0 ; index < string2.length() ; index++){
			char current = string2.charAt(index);
			if(!set.contains(string2.charAt(index)))
				set.add(current);
		}
		
		// Scan first string and delete characters that are present in the set
		// REMEMBER : If you delete a character at an index, DO NOT UPDATE THE INDEX
		for(int index = 0 ; index < stringBuilder.length() ; ){
			if(set.contains(stringBuilder.charAt(index)))
				stringBuilder.deleteCharAt(index);
			else
				index++;
		}
		System.out.println(stringBuilder.toString());
	}
}
