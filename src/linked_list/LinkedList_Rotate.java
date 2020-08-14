package linked_list;

/**
 * @author 47un
 *
 * Given a singly linked list, rotate the linked list counter-clockwise by k nodes. Where k is a given positive integer.
 * For example, if the given linked list is 10->20->30->40->50->60 and k is 4, the list should be modified to 50->60->10->20->30->40. 
 * Assume that k is smaller than the count of nodes in linked list.
 * 
 * http://www.geeksforgeeks.org/rotate-a-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Get the last node
 *            Make a cycle
 *            Go to the kth node
 *            Make the next node the first node and Disconnect this node from the rest
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class LinkedList_Rotate extends LinkedList_Utils{

    public static void main(String...strings){
        readListFromString("1 2 3 4 5 6 7 8");
        int k = 4;
        rotate(k);
        displayList();
    }

    private static void rotate(int k) {
        Node current = first;
        while(current.next != null)
            current = current.next;
        current.next = first;
        current = first;
        while(k-- > 1)
            current = current.next;
        first = current.next;       
        current.next = null;
    }
}