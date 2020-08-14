package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Find middle of a linked list.
 * If given linked list is 1->2->3->4->5 then output should be 3.
 * If there are even nodes, then there would be two middle nodes, we need to print second middle element.
 * If given linked list is 1->2->3->4->5->6 then output should be 4.
 * 
 * http://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Slow and Fast pointers/Rabbit and Hare
 *            Fast pointer jumps two links while slow jumps one
              When fast reaches the end, slow reaches the middle
              
 * TIME     : O(n/2)
 * SPACE    : O(1)
 *
 * METHOD 2 : Using count
 *            Scan the list, keep count of the nodes
 *            Update the middle pointer for every odd count
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 * 
 */

public class LinkedList_Middle extends LinkedList_Utils{

	static Node first = null;
	
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readList(scanner);			
			System.out.println("\n(I)  Middle is : " + middleI(first));
			System.out.println("\n(II) Middle is : " + middleII(first));
		}
	}

	private static int middleI(Node first){
		if(null == first)
			return -1;
		if(null == first.next)
			return first.data;
		Node slow = first;
		Node fast = first;
		while(null != fast && null != fast.next){
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow.data;
	}
	
	private static int middleII(Node first){
		int count = 0;
		Node temp   = first;
		Node middle = first;
		while(null != temp){
			if(count++ % 2 != 0)
				middle = middle.next;
			temp = temp.next;
		}
		return middle == null? -1 : middle.data;
	}
}
