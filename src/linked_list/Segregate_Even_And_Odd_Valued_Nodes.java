package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Segregate odd and even nodes in a list. Even nodes should come first
 * 
 *  Input  4->19->13->99->7884->55->610->45
 *  Output 4->7884->610->19->13->99->55->45
 * 
 * http://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked-list/ * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Make two lists, connect them
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Segregate_Even_And_Odd_Valued_Nodes extends LinkedList_Utils{

	static Node first = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readList(scanner);
			segregateEvenAndOddNodes();
			System.out.print("\nSegregated List : ");
			displayList(first);			
		}
	}

	private static void segregateEvenAndOddNodes() {
		if(first == null || first.next == null)
			return;
		Node firstEven = null;
		Node firstOdd = null;
		Node lastEven = null;
		Node lastOdd = null;
		Node current = first;
		Node next = null;
		while(current != null){
			next = current.next;
			current.next = null;
			if(current.data%2 == 0)
				if(lastEven == null){
					lastEven = current;
					firstEven = lastEven;
				}
				else{
					lastEven.next = current;
					lastEven = lastEven.next;
				}
			else
				if(lastOdd == null){
					lastOdd = current;
					firstOdd = lastOdd;
				}
				else{
					lastOdd.next = current;
					lastOdd = lastOdd.next;
				}
			current = next;
		}
		first = firstEven;
		lastEven.next = firstOdd;
	}
}
