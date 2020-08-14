package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Delete a node from a doubly linked list
 * 
 * 1=>2=>5=>78=>1  Deleting 1  2=>5=>78=>1
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Corner cases
 *            Search for the node
 *            Connect the one before him to the one after him
 *
 * TIME     : O(n)
 * SPACE    : O(1) 
 *
 * 
 */

public class DoublyLinkedList_Delete_Node extends DoublyLinkedList_Utils {
    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            readList(scanner);
            System.out.print("\nEnter data ");
            int data = scanner.nextInt();
            deleteNode(data);
            displayList();			
        }
    }

    private static void deleteNode(int data) {
        if(first == null)
            return;
        if(first.data == data) {
            first = first.next;
            return;
        }
        Node current = first;
        while(current != null && current.data != data)
            current = current.next;
        if(current == null)
            return;
        current.previous.next = current.next;
        if(current.next != null)
            current.next.previous = current.previous;
    }
}
