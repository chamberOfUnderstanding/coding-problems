package linked_list;

/**
 * @author 47un
 *
 * Merge sort is often preferred for sorting a linked list.
 * The slow random-access performance of a linked list makes some other algorithms (such as quick sort) perform poorly, and others (such as heap sort) completely impossible.
 * 
 * http://www.geeksforgeeks.org/merge-sort-for-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * Dude, get the middle, sort start to middle and middle's next to end, merge them
 *
 * TIME     : O(nlogn)
 * SPACE    : O(n) // Recursive version
 *
 * 
 */

@SuppressWarnings("unused")
public class LinkedList_Merge_Sort extends LinkedList_Utils{

    static Node first = null;

    public static void main(String...strings){
        displayList(mergeSort(readListFromString("8 2 0 22 41 -444 2 1 4 99220")));			
    }

    private static Node mergeSort(Node head) {
        if(head == null || head.next == null)
            return head;
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        return mergeIterative(mergeSort(head), mergeSort(nextOfMiddle));
    }

    private static Node mergeRecursive(Node left, Node right) {
        if(left == null)
            return right;
        if(right == null)
            return left;		
        Node result = null;
        if(left.data < right.data){
            result = left;
            result.next = mergeRecursive(left.next, right);
        }
        else{
            result = right;
            result.next = mergeRecursive(left, right.next);
        }
        return result;
    }

    private static Node mergeIterative(Node left, Node right) {
        Node dummyHead = new Node(-1);
        Node current   = dummyHead;
        while(left != null && right != null) {
            if(left.data <= right.data) {
                current.next = left;
                left = left.next;
            }
            else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        current.next = left == null? right: left;
        return dummyHead.next;
    }

    private static Node getMiddle(Node head) {
        if(head == null)
            return head;
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
