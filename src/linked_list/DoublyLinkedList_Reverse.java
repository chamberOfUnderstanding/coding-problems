package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Reverse a doubly linked list - both iterative and recursive methods
 * 
 * e.g 1=>40=>56=>345=>10
 *     10=>345=>56=>40=>1
 * 
 * http://www.geeksforgeeks.org/reverse-a-doubly-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Swap previous and next for all nodes
 *            Since you have swapped the pointers, advance to previous instead of next
 *            After reversing, new first is the previous of swapper
 *            Swapper is the second node of the reversed list
 *            If the head and tail pointers of this list are available, then you can reverse in O(1), by swapping just the head and tail
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class DoublyLinkedList_Reverse extends DoublyLinkedList_Utils{
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			readList(scanner);
			reverseIterative();
			displayList();
			reverseRecursive(first);
			System.out.println();
			displayList();			
		}
	}

	private static void reverseIterative() {
		if(first == null || first.next == null)
			return;
		Node current = first;
		Node swapper = null;
		while(current != null){
			swapper = current.previous;
			current.previous = current.next;
			current.next = swapper;
			current = current.previous;
		}
		if(swapper != null)
			first = swapper.previous;
	}
	
	private static void reverseRecursive(Node node){
		if(node == null)
			return;
		Node swapper = node.previous;
		node.previous = node.next;
		node.next = swapper;
		if(node.previous == null)
			first = node;
		else
			reverseRecursive(node.previous);
	}

}
