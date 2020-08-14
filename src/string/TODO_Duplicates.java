package string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/remove-all-duplicates-from-the-input-string/
 * 
 * http://www.geeksforgeeks.org/print-all-the-duplicates-in-the-input-string/
 */
public class TODO_Duplicates {
	public static void main(String...x){
		String input = "aaaabbbbccccjjjjeeeeuuuusisisisisisi----++!!231231237";
		TODO_Duplicates duplicates = new TODO_Duplicates();
		System.out.println("\nDuplicates removed : " + duplicates.remove(input));
	}

	// O(n) , O(unique characters in input)
	private String remove(String input) {
		System.out.print("\nDuplicates : ");
		StringBuilder output = new StringBuilder("");
		Set<Character> set = new HashSet<>();
		for(int index = 0; index < input.length() ; index++){
			char currentCharacter = input.charAt(index); 

			// If the character has'nt been seen before, add it to the set and consider it in the output string 
			if(!set.contains(currentCharacter)){
				set.add(currentCharacter);
				output.append(currentCharacter);
			}

			// Else this is a duplicate
			else
				System.out.print(index + " ");
		}

		// This string with duplicates removed
		return output.toString();
	}

//	private String removeAdjacentDuplicates(String input){
//		Stack<Character> stack = new Stack();
//		StringBuilder output = new StringBuilder();
//		int index = 0;
//		while(index < input.length()){
//			Character current = input.charAt(index); 
//			if(stack.isEmpty()){
//				stack.push(current);
//				index++;
//			}
//			else{
//				int duplicateCount = index;
//				while(index != input.length()-1 && input.charAt(index) == stack.peek())
//					index++;
//				duplicateCount = index - duplicateCount;
//				if(duplicateCount != 1)
//					stack.pop();
//			}
//		}
//	}
}
