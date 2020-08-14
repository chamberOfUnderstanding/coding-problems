package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 47un
 *
 * https://www.geeksforgeeks.org/alternate-vowel-consonant-string/
 * 
 */

public class Alternate_Vowel_Consonant_Sorting {

	public static void main(String[] args) {
		test("zxcvbaeiou");
		test("zxcvbaeio");
		test("zxcvaeiou");
		test("zxcvbnm");
		test("aeiou");
	}

	private static String sortVowelsAndConsonantsAlternatively(String input) {
		StringBuilder output = new StringBuilder();
		List<Character> vowels = new ArrayList<Character>();
		List<Character> consonants = new ArrayList<Character>();
		for(int i = 0; i < input.length(); i++) {
			char letter = input.charAt(i);
			if(VOWELS.contains(letter))
				vowels.add(letter);
			else
				consonants.add(letter);
		}
		if(Math.abs(consonants.size()- vowels.size()) > 1)
			return "No such string";
		int v = 0, c = 0;
		Character currentVowel = vowels.get(v);
		Character currentConsonant = consonants.get(c);
		if(vowels.size() > consonants.size()){
			output.append(currentVowel);
			v++;
		}
		else if(consonants.size() > vowels.size()) {
			output.append(currentConsonant);
			c++;
		}
		else {
			output.append(
					currentVowel < currentConsonant ? 
							new char[] {currentVowel, currentConsonant} :
								new char[] {currentConsonant, currentVowel});
			v++;
			c++;
		}
		for( ; v < vowels.size() && c < consonants.size(); v++, c++) {
			currentVowel = vowels.get(v);
			currentConsonant = consonants.get(c);
			output.append(
					v < c ? 
							new char[] {currentVowel, currentConsonant} :
								new char[] {currentConsonant, currentVowel});
		}
		return output.toString();
	}
	
	private static Set<Character> VOWELS = new HashSet<Character>(){{
		add('a');
		add('e');
		add('i');
		add('o');
		add('u');
	}};


	private static void test(String input) {
		System.out.println(input + " -> " + sortVowelsAndConsonantsAlternatively(input));
	}

}
