package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given a binary number as a list, convert it to its decimal equivalent
 *  
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Multiply each digit by 2 and add the 1 or 0
 *            101 => 5
 *            i.e. first * 2  + second's data
 *                 second * 2 + third's data
 *                 ...
 *            The numbers in [] are the binary digits of 101
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Binary_To_Decimal extends LinkedList_Utils{

	static Node first = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readList(scanner);			
			convertToDecimal(first);
		}
	}

	private static void convertToDecimal(Node first) {
		if(first == null)
			System.out.println("\nEmpty List");
		int value    = first.data;
		Node current = first.next;
		while(current != null){
			value   = value*2 + current.data;
			current = current.next;
		}
		System.out.println(value);
	}
}
