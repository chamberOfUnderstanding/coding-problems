package linked_list;

/**
 * @author 47un
 *
 * Given a linked list, rearrange it such that converted list should be of the form : a < b > c < d > e < f
 * Where a, b, c.. are consecutive data node of linked list.
 * 
 * Input:  1->2->3->4
 * Output: 1->3->2->4
 * 
 * Input:  11->15->20->5->10
 * Output: 11->20->5->15->10
 * 
 * http://www.geeksforgeeks.org/linked-list-in-zig-zag-fashion/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : A flag decides ascending/descending
 *            If the current and next need to be in ascending/descending and they are in the opposite order, swap the data
 *            Advance and flip the flag
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * METHOD 2  : Sort the list (Merge sort preferably) and swap alternate nodes
 * 
 * TIME     : O(nlogn)
 * SPACE    : O(1)
 *
 */

public class LinkedList_ZigZag extends LinkedList_Utils{

    static Node first1 = null;	

    public static void main(String...strings){
        first1 = readListFromString("11 15 20 5 10");
        zigzag();
        System.out.print("\nZigzagged list : ");
        displayList(first1);
    }

    private static void zigzag() {
        boolean ascending = true;
        Node current = first1;
        while(current.next != null){
            Node next = current.next;
            if(ascending && current.data > next.data || !ascending && current.data < next.data)			
                swapData(current, next);		
            current = next;
            ascending = !ascending;
        }
    }

    private static void swapData(Node current, Node next) {
        next.data    ^= current.data;
        current.data ^= next.data;
        next.data    ^= current.data;
    }
}
