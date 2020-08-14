package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Delete the middle of a given linked list
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan and find the middle (slow is the middle) and previous of middle
 *            Delete
 *
 * TIME     : O(n/2)
 * SPACE    : O(1)
 *
 * 
 */

public class LinkedList_Delete_Middle extends LinkedList_Utils{
	
	static Node first = null;
	
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readList(scanner);
			deleteMiddle();
			System.out.print("\nList after deleting middle : ");
			displayList();
		}
	}
	
	private static void deleteMiddle() {
		if(first == null || first.next == null)
			return;
		Node slow = first;
		Node fast = first;
		Node previousOfMiddle = null;
		Node middle = null;
		while(fast != null && fast.next != null){
			previousOfMiddle = slow;			
			slow = slow.next;
			fast = fast.next.next;			
		}
		middle = slow;
		previousOfMiddle.next = middle.next;		
	}
}
