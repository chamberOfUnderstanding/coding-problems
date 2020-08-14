package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Split a list to make two smaller lists ‘a’ and ‘b’.
 * The sublists should be made from alternating elements in the original list.
 * So if the original list is 0->1->0->1->0->1 then one sublist should be 0->0->0 and the other should be 1->1->1.
 * 
 * http://www.geeksforgeeks.org/alternating-split-of-a-given-singly-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : If either of the nodes hit null, break
 *            Connect first node's next to second node's next (Node III of original) and advance it
 *            Connect second node's next to third node's next (Node IV of original) and advance it 
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Alternating_Split extends LinkedList_Utils{

	static Node first = null;
	static Node split1 = null;
	static Node split2 = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readList(scanner);
			alternatingSplit(first);
			System.out.print("\nSplit 1: ");
			displayList(split1);
			System.out.print("\nSplit 2: ");
			displayList(split2);
		}
	}
	
	private static void alternatingSplit(Node first) {
		if(first == null || first.next == null){
			System.out.println("\nNeed more than one nodes to do a split!");
			return;
		}
		Node current1 = first;
		Node current2 = first.next;
		split1 = current1;
		split2 = current2;
		while(true){
			if(current2 == null)
				break;
			current1.next = current2.next;
			current1 = current1.next;			
			if(current1 == null)
				break;
			current2.next = current1.next;
			current2 = current2.next;
		}
	}
}
