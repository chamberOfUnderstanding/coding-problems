package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given a Singly Linked List, starting from the second node delete all alternate nodes of it.
 * For example, if the given linked list is 1->2->3->4->5 then your function should convert it to 1->3->5.
 * If the given linked list is 1->2->3->4 then convert it to 1->3. 
 * 
 * http://www.geeksforgeeks.org/delete-alternate-nodes-of-a-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Connect a node to the next of its next node
 *            Repeat this till the end of the list
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Delete_Alternate_Nodes extends LinkedList_Utils{

    public static void main(String...strings){
        readListFromString("1 2 3 4 5 6 7 8 9 10 11 12");
        System.out.print("\nDisplaying alternate nodes : ");
        displayAlternateNodes();
        System.out.print("\nRemoving alternate nodes :   ");
        removeAlternateNodes();
        displayList();
    }

    private static void removeAlternateNodes() {
        if(first == null)
            return;
        Node current = first;
        while(current != null && current.next != null){
            current.next = current.next.next;
            current = current.next;
        }
    }

    private static void displayAlternateNodes() {
        Node current = first;
        while(current != null && current.next != null){			
            System.out.print(current.data + "->");
            current = current.next.next;
        }
        if(current != null)
            System.out.print(current.data);
    }
}
