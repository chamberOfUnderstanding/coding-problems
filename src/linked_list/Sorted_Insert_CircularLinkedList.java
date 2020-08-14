package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Insert a node into a sorted circular linked list
 * 
 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Maintain pointers to first and second nodes
 *            Scan till the last node and check if the new node's data falls between the two nodes
 *            If yes, do stick it in
 *            No? keep scanning
 *            If the list is scanned and the item hasn't been inserted, it needs to go between the last node and the first node
 *            Insert it at the end and if this node is greater than the last node, make it the new last node
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Sorted_Insert_CircularLinkedList extends CircularLinkedList_Utils{

	static Node last = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			last = readList(scanner);
			System.out.print("\nEnter data to be inserted ");			
			int data = scanner.nextInt();
			last = sortedInsert(last, data);
			displayList(last);
		}
	}
	
	private static Node sortedInsert(Node last, int data) {
		if(last == null)
			return null;
		Node previous = last.link;
		Node current = last.link.link;
		Node newNode = new Node(data);
		boolean inserted = false;
		while(true){
			if(previous.data < data && data < current.data){
				previous.link = newNode;
				newNode.link = current;
				inserted = true;
				return last;
			}
			previous = previous.link;
			current = current.link;
			if(current == last)
				break;
		}
		if(!inserted){
			newNode.link = last.link;
			last.link = newNode;
			if(data > last.data)
				last = newNode;
		}
		return last;
	}
}
