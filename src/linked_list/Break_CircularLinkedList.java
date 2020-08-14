package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Break a circular list into two circular lists
 * 
 * http://www.geeksforgeeks.org/split-a-circular-linked-list-into-two-halves/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Find the middle of the list
 *            Get the next to middle
 *            Connect middle to first of the original list
 *            Connect last of the original list to next of middle
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Break_CircularLinkedList extends CircularLinkedList_Utils{

	static Node lastNodeOfOriginalList = null;
	static Node lastNodeOfCircularList1 = null;
	static Node lastNodeOfCircularList2 = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			lastNodeOfOriginalList = readList(scanner);
			breakerOfCircles();
			System.out.print("\nList 1 : ");
			displayList(lastNodeOfCircularList1);
			System.out.print("\nList 2 : ");			
			displayList(lastNodeOfCircularList2);
		}
	}
	
	private static void breakerOfCircles() {
		Node current = lastNodeOfOriginalList.link;
		Node middle  = lastNodeOfOriginalList.link;
		Node nextOfMiddle = null;
		int count = 0;
		while(current != lastNodeOfOriginalList){
			if(count%2 == 1)
				middle = middle.link;
			count++;
			current = current.link;
		}		
		nextOfMiddle = middle.link;		
		middle.link = lastNodeOfOriginalList.link;
		lastNodeOfOriginalList.link = nextOfMiddle;
		lastNodeOfCircularList1 = middle;
		lastNodeOfCircularList2 = lastNodeOfOriginalList;
	}
}
