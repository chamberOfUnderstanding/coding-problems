package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
 * 
 * Write a SortedMerge() function that takes two lists, each of which is sorted in increasing order, and merges the two together into one list which is in increasing order.
 * SortedMerge() should return the new list. The new list should be made by splicing together the nodes of the first two lists.
 * e.g.If the first linked list a is 5->10->15 and the other linked list b is 2->3->20, then
 *     SortedMerge() should return a pointer to the head node of the merged list 2->3->5->10->15->20.
 *
 */

public class SortedList_Merge extends LinkedList_Utils{

    static Node first1 = null;
    static Node first2 = null;

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            first1 = readListFromString("1 2 3 4 5 6");
            first2 = readListFromString("4 5 6 8 9 10");
            Node first3 = sortedMergeAscending(first1, first2);
            Node first4 = mergeIterative(first1, first2);
            displayList(first3);
            System.out.println();
            displayList(first4);
        }
    }

    // O(n) O(n)
    private static Node sortedMergeAscending(Node node1, Node node2) {
        if(node1 == null)
            return node2;
        if(node2 == null)
            return node1;
        Node node3 = null;
        if(node1.data <= node2.data) {
            node3 = new Node(node1.data);
            node3.next = sortedMergeAscending(node1.next, node2);
        }
        else if(node2.data < node1.data) {
            node3 = new Node(node2.data);
            node3.next = sortedMergeAscending(node1, node2.next);
        }
        return node3;
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

}
