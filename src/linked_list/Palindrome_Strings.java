package linked_list;

import java.util.Scanner;


/**
 * @author 47un
 * 
 * http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 * 
 * A string is represented as a list. Check if it's palindrome
 * 
 * Input  : ax ob p vvp box a
 * Output : Yes 
 * 
 */

public class Palindrome_Strings extends LinkedList_Utils{

	static Node first = null;

	static class Node{
		String data;
		Node link;

		Node(String data){
			this.data = data;			
		}
	}

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readStringList(scanner);
			System.out.println("\nPalindrome? " + (isPalindromeI()? "Yes" : "No"));
			displayList();
		}
	}

	// O(n) O(1)
	private static boolean isPalindromeI() {
		StringBuilder stringBuilderOutOfList = new StringBuilder("");
		Node current = first;
		while(current != null){
			stringBuilderOutOfList.append(current.data);
			current = current.link;
		}
		return isThisStringPalindrome(stringBuilderOutOfList.toString());
	}
	
	// Prefer this method over StringBuilder.reverse()
	private static boolean isThisStringPalindrome(String string) {
		int index1 = 0, index2 = string.length()-1;
		while(index1 < string.length()/2){
			if(string.charAt(index1++) != string.charAt(index2--))
				return false;
		}
		return true;
	}

	static Node readStringList(Scanner scanner) {
		System.out.print("\nEnter list : ");
		String[] nodes = scanner.nextLine().split(" ");
		Node newNode, first2 = null, last = null;
		for(String node:nodes){
			newNode = new Node(node);
			if(first2 == null){
				first2 = newNode;
				last = newNode;
			}
			else{
				last.link = newNode;
				last = last.link;
			}
		}
		return first2;		
	}
}