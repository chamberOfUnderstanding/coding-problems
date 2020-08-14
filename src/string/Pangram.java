/*https://www.hackerrank.com/challenges/pangrams
 * Check if the given string has all the letters of the alphabet
 * */

package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

public class Pangram {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String sentence = reader.readLine();
		System.out.println(isPangram(sentence.toLowerCase()));
	}

	public static String isPangram(String sentence){
		BitSet bitset = new BitSet(26);
		int index=0;
		while(index<sentence.length()){
			if(sentence.charAt(index)!=' ')
				bitset.set(sentence.charAt(index)-97);
			index++;
		}
		if(bitset.cardinality()==26)
			return "pangram";
		else
			return "not pangram";
	}
}
