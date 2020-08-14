package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given two lists sorted in increasing order, create and return a new list representing the intersection of the two lists.
 * The new list should be made with its own memory — the original lists should not be changed.
 * 
 * For example, let the first linked list be 1->2->3->4->6 and second linked list be 2->4->6->8,
 * then your function should create and return a third list as 2->4->6.
 * 
 * http://www.geeksforgeeks.org/intersection-of-two-sorted-linked-lists/>
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan both lists together
 *             If data of the two nodes are equal, add this node to the result list
 *              To make adding nodes easy, keep track of the last node of the result list
 *              Advance both
 *             Advance the list that had the lesser value while comparing 
 *              
 * TIME     : O(m + n)
 * SPACE    : O(1)
 *
 * 
 */

public class SortedList_Intersection extends LinkedList_Utils{

	static Node first2 = null;
	static Node first3 = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			readList(scanner);
			Node backup = first;
			first = null;
			readList(scanner);
			first2 = first;
			first = backup;
			first3 = intersect(first, first2);
			first = first3;
			displayList();
			first = backup;
		}
	}

	private static Node intersect(Node first, Node first2) {
		if(first == null || first2 == null)
			return null;
		Node first3 = null;
		Node last3 = null;
		Node current1 = first;
		Node current2 = first2;
		while(current1 != null && current2 != null){
			if(current1.data == current2.data){
				Node newNode = new Node(current1.data);
				if(first3 == null){
					first3 = newNode;
					last3 = first3;
				}
				else{
					last3.next = newNode;
					last3 = last3.next;
				}
				current1 = current1.next;
				current2 = current2.next;
			}
			else if(current1.data < current2.data)
				current1 = current1.next;
			else
				current2 = current2.next;
		}
		return first3;
	}
}
