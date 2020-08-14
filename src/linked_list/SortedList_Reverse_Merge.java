package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Reverse merge a list
 * 
 * Input  : 90 91 97 120 and -4 8 55
 * Output : 120->97->91->90->55->8->-4
 * 
 * http://www.geeksforgeeks.org/merge-two-sorted-linked-lists-such-that-merged-list-is-in-reverse-order/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan both lists
 *            Get the node with the least value
 *            Backup next of the least node
 *            Stick this at the front of the result list and update front
 *            Advance through either list depending on which had its value picked
 *            If list are of unequal size, add remaining nodes to the start of the list
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * METHOD 2 : Reverse both lists, then merge sort them
 * TIME     : O(m) + O(n) + O((m+n)log(m+n))
 * SPACE    : O(n)
 * 
 */

public class SortedList_Reverse_Merge extends LinkedList_Utils{

	static Node first1 = null;
	static Node first2 = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first1 = readList(scanner);
			first2 = readList(scanner);
			Node first3 = sortedMerge(first1,first2);
			System.out.print("\nSorted List : ");
			displayList(first3);			
		}
	}

	private static Node sortedMerge(Node node1, Node node2){
		Node result = null;
		Node current1 = node1;
		Node current2 = node2;
		Node leastNode = null;
		Node next = null;
		boolean isList1HavingTheLeastValue = false;
		while(current1 != null && current2 != null){
			isList1HavingTheLeastValue = current1.data <= current2.data; 			
			leastNode       = isList1HavingTheLeastValue? current1 : current2;
			next            = leastNode.next;
			leastNode.next  = result;
			result          = leastNode;
			if(isList1HavingTheLeastValue)
				current1 = next;
			else
				current2 = next;
		}
		while(current1 != null){
			next = current1.next;
			current1.next = result;
			result = current1;
			current1 = next;
		}
		while(current2 != null){
			next = current2.next;
			current2.next = result;
			result = current2;
			current2 = next;
		}
		return result;
	}
}
