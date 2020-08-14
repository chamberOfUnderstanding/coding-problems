package linked_list;

/**
 * @author 47un
 *
 * Insertion sort a linked list
 * Input  : 108 6 22 -88 0 98 -477
 * Output : -477->-88->0->6->22->98->108

 * REFERENCE	
 * ============
 * http://quiz.geeksforgeeks.org/insertion-sort-for-singly-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Insert each item into another list in a sorted way
 *
 * TIME     : O(n^2)
 * SPACE    : O(1)
 *
 * 
 */

public class Insertion_Sort_LinkedList extends LinkedList_Utils{

	static Node first2 = null;

	public static void main(String...strings){
			Node first = readListFromString("99 28 -22 10 44 2995 238 -6");
			insertionSort(first);
			System.out.print("Sorted list : ");
			displayList(first2);
	}
	
	private static void insertionSort(Node first) {
		Node current = first;
		while(current != null){
			Node next = current.next;
			current.next = null;
			insertIntoSortedList(current);
			current = next;
		}
	}

	private static void insertIntoSortedList(Node node) {
		if(first2 == null)
			first2 = node;
		else if(node.data < first2.data){
			node.next = first2;
			first2 = node;
		}
		else{
			Node previous = first2;
			Node current  = first2.next; 
			while(current != null && isNotBetween(previous.data, node.data, current.data)){
				previous = previous.next;
				current = current.next;
			}
			previous.next = node;
			node.next = current;
		}
	}

	private static boolean isNotBetween(int data1, int data2, int data3) {
		return data1 <= data2 && data2 < data3? false : true;
	}
}
