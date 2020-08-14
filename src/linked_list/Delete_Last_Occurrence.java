package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given a liked list and a key to be deleted. Delete last occurrence of key from linked.
 * The list may have duplicates.
 * Examples:
 * Input:   1->2->3->5->2->10, key = 2
 * Output:  1->2->3->5->10
 * 
 * http://quiz.geeksforgeeks.org/delete-last-occurrence-of-an-item-from-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Reverse the list
 *            Delete the first occurrence of the key
 *            Reverse the list again
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * METHOD 2 : Scan the list
 *            If the next to current node's data is the key, save it as previous
 *            If the current node's data is the key, save it as the node to be deleted
 *            Delete it
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Delete_Last_Occurrence extends LinkedList_Utils{

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			readList(scanner);
			System.out.print("\nEnter key to be deleted ");
			int key = scanner.nextInt();
//			deleteLastOccurrenceI(key);
			deleteLastOccurrenceII(key);
			displayList();
		}
	}

	@SuppressWarnings("unused")
    private static void deleteLastOccurrenceI(int key) {
		reverse(null, first);
		Node current = first, previous = null;
		while(null != current && current.data != key){
			previous = current;
			current = current.next;
		}
		previous.next = current.next;
		reverse(null, first);		
	}

	private static void deleteLastOccurrenceII(int key){
	    Node previous = null;
		Node current = first;
		Node nodeToBeDeleted = null;
		while(null != current){
			if(current.next != null && current.next.data == key)
				previous = current;
			if(current.data == key)
				nodeToBeDeleted = current;
			current = current.next;
		}
		if(previous == null)
			first = first.next;
		else
			previous.next = nodeToBeDeleted.next;
	}


	private static void reverse(Node previous, Node current){
		Node next = current.next;
		current.next = previous;
		if(previous == null)
			last = current;
		if(next == null)
			first = current;
		else
			reverse(current,next);
	}
}
