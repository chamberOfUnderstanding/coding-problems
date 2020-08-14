package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Swap elements pairwise, in a singly linked list 
 * e.g. 1->2->3->4->5->6->7   =>  2->1->4->3->6->5->7
 *      1->2->3->4->5->6      =>  2->1->4->3->6->5
 * 
 * http://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list-by-changing-links/ * 
 * http://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Pair wise swap links or pair wise swap data
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Pairwise_Swap extends LinkedList_Utils{
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			readList(scanner);
			pairWiseSwapLinks();
			displayList();
		}
	}
	
	private static void pairWiseSwapLinks() {
		if(first == null || first.next == null)
			return;
		Node previous = first;
		Node current  = first.next;
		Node next     = null;
		first         = current;
		while(true){			
			next         = current.next;
			current.next = previous;
			if(next == null || next.next == null){
				previous.next = next;
				break;
			}
			previous.next = next.next;
			previous = next;
			current = next.next;
		}
	}
	
	@SuppressWarnings("unused")
    private static void pairWiseSwapData(){
		if(first == null || first.next == null)
			return;
		int temp;
		Node current = first;
		while(true){
			if(current == null || current.next == null)
				break;
			temp = current.next.data;
			current.next.data = current.data;
			current.data = temp;
			current = current.next.next;
		}
	}
}
