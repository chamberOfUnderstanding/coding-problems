package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given a linked list, write a function to reverse every k nodes (where k is an input to the function).
 * 
 * Input:   1->2->3->4->5->6->7->8->NULL and k = 3 
 * Output:  3->2->1->6->5->4->8->7->NULL. 
 * 
 * Input:   1->2->3->4->5->6->7->8->NULL and k = 5
 * Output:  5->4->3->2->1->8->7->6->NULL.
 * 
 * Now reverse alternate k nodes
 * 
 * Input  : 1 2 3 4 5 6 7 8 9 10 and k =3
 * Output : 3->2->1->4->5->6->9->8->7->10
 * 
 * http://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Reverse K nodes
 *            If the end is not reached
 *             Connect current node to the reverse of next k nodes
 *            Return previous
 *            
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * Reverse alternate K nodes
 * 
 * METHOD 1 : Maintain a flag. If the flag is hoisted, reverse next k nodes and de hoist it
 *            i.e. If reverse, connect current to previous, else just scan through k nodes
 *            Now if the end is not reached
 *              If reversed, set next of current as reverse's result
 *              If not reversed, set next of previous as reverse's result
 *              Next of previous because, since the last k nodes weren't reversed, we just have to stick things to the end of the
 *              kth node, which is previous
 *             If reversed, return previous, else node
 *             
 * TIME     : O(n)
 * SPACE    : O(n)
 */

public class LinkedList_Reverse_K_Nodes extends LinkedList_Utils{

	static Node first = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readList(scanner);
			System.out.print("\nEnter k : ");
			int k = scanner.nextInt();
			System.out.print("\nReversing           : ");
			first = reverseEveryKNodes(first,k);
			displayList(first);		
			System.out.print("\nReversing again     : ");
			first = reverseEveryKNodes(first,k);
			displayList(first);
			System.out.print("\nReversing alternate : ");
			displayList(reverseAlternateKNodes(first, k, true));
		}
	}

	private static Node reverseEveryKNodes(Node node, int k) {
		Node current = node;
		Node previous = null;
		Node next = null;
		int count = 0;
		while(current != null && count++ < k){
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		if(next != null)
			node.next = reverseEveryKNodes(next, k);
		return previous;
	}

	private static Node reverseAlternateKNodes(Node node, int k, boolean reverse) {
		Node current = node;
		Node previous = null;
        Node next = null;
		int count = 0;
		while(current != null && count++ < k){
			next = current.next;
			if(reverse)
				current.next = previous;
			previous = current;
			current = next;
		}
		if(next != null)
			(reverse? node : previous).next = reverseAlternateKNodes(next, k, !reverse);
		return reverse? previous : node;
	}
}
