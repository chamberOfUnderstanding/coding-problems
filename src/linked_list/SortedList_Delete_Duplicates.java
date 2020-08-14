package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Delete duplicates nodes in a sorted list in O(n)
 * 
 * http://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Compare current node with next node
 *            If they have the same data, connect current to next's next
 *            If the nodes have different data, advance the current node
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class SortedList_Delete_Duplicates extends LinkedList_Utils{
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			readList(scanner);
			removeDuplicates();
			displayList();
		}
	}

	private static void removeDuplicates() {
		if(first == null)
			return;
		Node current = first;		
		while(true){
			if(current.data == current.next.data)
				current.next = current.next.next;
			else
				current = current.next;
			if(current.next == null)
				break;
		}
	}
}
