package string;

/***
 * Compress a given string "aabbbccc" to "a2b3c3" 
 * Constraint: inplace compression, no extra space to be used 
 * Assumption : output size will not exceed input size.. ex input:"abb" -> "a1b2" buffer overflow.. such inputs will not be given
 */

import java.util.Scanner;

public class String_Compression {
	public static void main(String...x){
		try(Scanner scanner = new Scanner(System.in)){
			String input = scanner.next();
			System.out.println(compress(input));
			System.out.println(compressInPlace(new StringBuilder(input)));
		}
	}

	private static StringBuilder compress(String input) {
		
		StringBuilder compressedString = new StringBuilder();
		
		for(int i=0;i<input.length();){
			int index = i;
			int count = 1;
			while(i<input.length()-1 && input.charAt(i) == input.charAt(i+1)){
				count++;
				i++;
			}
			compressedString.append(input.charAt(index));
			compressedString.append(count);
			i++;
		}
		return compressedString;
	}

	private static StringBuilder compressInPlace(StringBuilder input){
		
		//indexForCount is where the 'count' will be inserted
		//processedUntil shows till where the input string has been processed
		int indexForCount=1,processedUntil=0;
		
		//Go through each character
		for(int i=0;i<input.length();)
		{	
			//Since indexForCount is the place where you keep the count, indexForCount-1 must get the character with that count
			input.setCharAt(indexForCount-1, input.charAt(i));
						
			Integer count=1;
			
			//Loop around until you see that the current character and the next character are UNEQUAL
			while(i<input.length()-1 && (input.charAt(i)==input.charAt(i+1))){
				count++;
				i++;
			}
			
			//For count = 1, insert the count at indexForCount
			//Next place you'd insert the count would be indexForCount+2
			//Two more chars of the original string have been processed
			if(count==1)
			{			
				input.insert(indexForCount,count);
				indexForCount+=2;
				processedUntil += 2;
			}
			
			//For other values of count, delete the char at index
			//Insert the count at index
			//Advance indexForCount by number of digits in count +1
			//Advance processedUntil by count and the digits in count
			else
			{					
				input.deleteCharAt(indexForCount);
				input.insert(indexForCount,count);
				int digitsInCount = count.toString().length();
				
				//Advance indexForCount by digitsInCount+1
				indexForCount += digitsInCount+1;			
				
				//The -1 is because : in this part (The else), first a character is deleted, so string length dips, then some characters
				//are inserted (equal to digitsInCount), in effect we have inserted digitsInCount number of characters.
				processedUntil += count+digitsInCount-1;
			}
			i = processedUntil;
		}
		input.setLength(indexForCount-1);
		return input;
	}
}
