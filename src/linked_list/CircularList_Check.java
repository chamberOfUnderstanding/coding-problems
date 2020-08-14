package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Check if a given list is circular
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan the list, if you encounter a null -> not circular, if you encounter the first node again, circular !
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class CircularList_Check extends LinkedList_Utils{

	static Node first1 = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first1 = readList(scanner);
			System.out.println("\nIs it circular? : " + isCircular(first1));
		}
	}

	private static boolean isCircular(Node first1) {
		Node current = first1;
		while(true){
			if(current == null)
				return false;
			current = current.next;
			if(current == first)
				return true;
		}
	}
}
