package string;

import java.util.BitSet;

/**
 * @author 47un
 * 
 * http://www.geeksforgeeks.org/print-list-items-containing-all-characters-of-a-given-word/
 */
public class Contains {
	public static void main(String...strings){
		
		// The characters in the main string are all unique
		String mainString = "sun";
		String[] list = {"sunnyday", "sundance", "smokin","soakedutensils","sunnninesssun","xyz", "sss"};
		new Contains().check(mainString, list);
	}

	// a is the number of strings in the list, b is the length of the main string and c is the length of the max length string in the list
	
	// O(a(b+c)) , O(256)
	private void check(String mainString, String[] strings) {
		int mainStringLength = mainString.length();
		BitSet bitset = new BitSet(255);
		
		// Scan each string
		for(String string : strings){
			
			// Set the bits for all characters in the main string
			for(int index=0 ; index < mainStringLength ; index++){
				char current = mainString.charAt(index);
				if(!bitset.get(current))
					bitset.set(current);
			}
			
			// Scan the current string
			int count = 0;
			for(int index=0 ; index < string.length() ; index++){
				char current = string.charAt(index);
				
				// Flip the bit if this character is present in the main string and increase the count
				if(bitset.get(current)){
					bitset.flip(current);
					count++;
				}
				
				// If the count hits main string length, print the string
				if(count == mainStringLength){
					System.out.println(string);
					break;
				}
			}
		}
	}
	
	//TODO repeating characters
}
