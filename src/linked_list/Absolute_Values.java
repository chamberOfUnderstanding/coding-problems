package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given a linked list which is sorted based on absolute values. Sort the list based on actual values.
 * 
 * Examples:
 * 
 * Input :  1 -> -10 
 * output: -10 -> 1
 * 
 * Input :  1 -> -2 -> -3 -> 4 -> -5 
 * output: -5 -> -3 -> -2 -> 1 ->  4 
 * 
 * Input : -5 -> -10 
 * Output: -10 -> -5
 * 
 * Input : 5 -> 10 
 * Output: 5 -> 10
 * 
 * http://www.geeksforgeeks.org/sort-linked-list-already-sorted-absolute-values/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Disconnect the current node from the list
 *            If this node is -ve, stick it at the front of the list
 *            If this node is +ve, stick it after the last positive value on the list
 *            Here previous always points to the last positive value
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Absolute_Values extends LinkedList_Utils{

	static Node first = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readListFromString("1 -2 -3 4 -5");
			sort();
			System.out.print("\nSorted list : ");
			displayList(first);
		}
	}

	private static void sort(){
	    Node previous = null;
		Node current  = first;		
		Node next     = null;
		while(current != null){
			next = current.next;
			current.next = null;
			if(current.data < 0){
				current.next = first;
				first = current;
			}
			else{
				if(previous == null)
					previous = current;
				else{
					previous.next = current;
					previous = current;
				}
			}
			current = next;
		}
	}
}
