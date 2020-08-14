package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given two linked lists, insert nodes of second list into first list at alternate positions of first list.
 * The nodes of second list should only be inserted when there are positions available.
 * Use of extra space is not allowed (Not allowed to create additional nodes), i.e., insertion must be done in-place.
 * Expected time complexity is O(n) where n is number of nodes in first list.
 * 
 * Input : 5->7->17->13->11 , 12->10->2->4->6 =>>> Output : 5->12->7->10->17->2->13->4->11->6 , null
 * Input : 1->2->3, 4->5->6->7->8 =>>> Output : 1->4->2->5->3->6 , 7->8.
 * 
 * http://www.geeksforgeeks.org/merge-a-linked-list-into-another-linked-list-at-alternate-positions/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Backup nexts of both lists
 *            Make the connections
 *            Update first of second to new current node
              This MUST be done to track the nodes remaining in the second list
 *            
 * TIME     : O(Min(m,n))  // m is the length of th first list, n is that of second list
 * SPACE    : O(1)
 *
 * 
 */

public class Merge_At_Alternate_Positions extends LinkedList_Utils{	
    static Node first1 = null;
    static Node first2 = null;
    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            first2 = readListFromString("4 4 5 6 7");
            first1 = readListFromString("7 1 4 66 90 -2");
            mergeAtAlternatePositions(first1, first2);
            displayList(first1);
            displayList(first2);
        }
    }

    private static void mergeAtAlternatePositions(Node list1, Node list2) {
        if(list1 == null || list2 == null || list1.next == null)
            return;
        Node current1 = list1;
        Node current2 = list2;
        Node next1;
        Node next2;
        while(current1.next != null && current2.next != null) {
            next1 = current1.next;
            next2 = current2.next;
            current1.next = current2;
            current2.next = next1;
            current1 = next1;
            current2 = next2;
        }
        first2 = current2;
    }
}
