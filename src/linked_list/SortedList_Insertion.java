package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Insert a node into a sorted list
 * 
 * http://www.geeksforgeeks.org/given-a-linked-list-which-is-sorted-how-will-you-insert-in-sorted-way/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Use previous and current pointers
 *            Scan till the end of the list
 *            If the data to be inserted falls between these pointers, insert it there
 *            If the list has been fully scanned and node has'nt been inserted, add it to the start/end of the list after comparing the values
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class SortedList_Insertion extends LinkedList_Utils{

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			readList(scanner);
			System.out.print("\nEnter item to be inserted ");
			int data = scanner.nextInt();
			sortedInsert(data);
			displayList();
		}
	}

	private static void sortedInsert(int data) {
		Node newNode = new Node(data);
		if(first == null)
			first = newNode;
		else if(data < first.data){
			newNode.next = first;
			first = newNode;
		}
		else if(data > last.data) {
		    last.next = newNode;
		    last = newNode;
		}
		else{
			Node lessThan = first;
			Node greaterThan = first.next;
			while(greaterThan != null && isNotBetween(lessThan.data, data, greaterThan.data)){
				lessThan = lessThan.next;
				greaterThan = greaterThan.next;
			}
			lessThan.next = newNode;
			newNode.next = greaterThan;					
		}
	}

	private static boolean isNotBetween(int data1, int data2, int data3) {
		return data1 <= data2 && data2 <= data3;
	}
}
