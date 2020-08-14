package linked_list;

/**
 * @author 47un
 *
 * Given a singly linked list L0 -> L1 -> … -> Ln-1 -> Ln.
 * Rearrange the nodes in the list so that the new formed list is : L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 …
 * 
 * You are required do this in-place without altering the nodes' values.
 * Examples:
 * Input:  1 -> 2 -> 3 -> 4
 * Output: 1 -> 4 -> 2 -> 3
 * 
 * Input:  1 -> 2 -> 3 -> 4 -> 5
 * Output: 1 -> 5 -> 2 -> 4 -> 3
 * 
 * http://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Break the list at the middle into two halves
 *            Reverse the second half
 *            Merge the two halves at alternating positions
 *              Have a dummy node
 *              Add a node from first list to dummy
 *              Add a node from second list to dummy
 *              
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class LinkedList_Rearrange extends LinkedList_Utils{

    static Node first1 = null;

    public static void main(String...strings){
        first1 = readListFromString("1 2 3 4 5");
        rearrange();
        displayList(first1);			
    }

    private static void rearrange() {
        Node list1 = first1;
        Node list2 = getMiddle();		
        list2 = reverse(list2);
        alternateMerge(list1, list2);		
    }

    private static void alternateMerge(Node list1, Node list2) {
        if(list1 == null || list2 == null || list1.next == null)
            return;
        Node dummyNode = new Node(-1);
        Node current1  = dummyNode;
        while(list1 != null && list2 != null){
            if(list1 != null) {
                current1.next = list1;
                current1 = current1.next;
                list1 = list1.next;
            }
            if(list2 != null) {
                current1.next = list2;
                current1 = current1.next;
                list2 = list2.next;
            }
        }
    }

    private static Node reverse(Node first) {
        Node previous = null;
        Node current = first;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    private static Node getMiddle() {
        if(first1 == null || first1.next == null)
            return first1;
        Node middle = first1, fast = first1, previous = null;
        while(fast != null && fast.next != null){
            previous = middle;
            middle = middle.next;
            fast = fast.next.next;
        }
        previous.next = null;
        return middle;
    }
}
