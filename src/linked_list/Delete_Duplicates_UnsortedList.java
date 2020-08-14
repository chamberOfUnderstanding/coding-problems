package linked_list;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 47un
 *
 * Remove duplicates from an unsorted linked list
 * 
 * http://www.geeksforgeeks.org/remove-duplicates-from-an-unsorted-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : HashSet
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Delete_Duplicates_UnsortedList extends LinkedList_Utils{
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			readList(scanner);
			removeDuplicates();
			displayList();
		}
	}

	private static void removeDuplicates() {
		if(first == null || first.next == null)
			return;
		Set<Integer> hashSet = new HashSet<>();
		Node previous = null;
		Node current  = first;
		while(true){
			while(current != null && hashSet.contains(current.data))
				current = current.next;
			if(previous != null)
				previous.next = current;
			if(current == null)
				break;
			hashSet.add(current.data);
			previous = current;
			current  = current.next;
		}
	}
}