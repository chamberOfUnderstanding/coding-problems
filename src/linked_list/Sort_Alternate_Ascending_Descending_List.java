package linked_list;

/**
 * @author 47un
 *
 * Given a Linked List. The Linked List is in alternating ascending and descending order.
 * Sort the list efficiently.
 * 
 * Example:
 * 
 * Input List:   10->40->53->30->67->12->89->NULL
 * Output List:  10->12->30->43->53->67->89->NULL
 * 
 * http://www.geeksforgeeks.org/how-to-sort-a-linked-list-that-is-sorted-alternating-ascending-and-descending-orders/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Split the list into two lists, one is ascending, the other is descending
 *            Reverse the descending list
 *            Merge these two lists
 *
 * TIME     : O(n)   // Split
 * SPACE    : O(n)
 *
 * 
 */

public class Sort_Alternate_Ascending_Descending_List extends LinkedList_Utils{

    static Node first1 = null;
    static Node ascendingList = null;
    static Node descendingList = null;

    public static void main(String...strings){
        first1 = readListFromString("10 40 53 30 67 12 89");
        sortAlternateAscendingDescendingList();
        displayList(first1);
    }

    private static void sortAlternateAscendingDescendingList() {
        alternatingSplit(first1);
        descendingList = reverse(descendingList);
        first1 = merge(ascendingList, descendingList);
    }

    private static void alternatingSplit(Node first) {
        if(first == null || first.next == null){
            System.out.println("\nNeed more than one node to do a split!");
            return;
        }
        Node current1  = first;
        Node current2  = first.next;
        ascendingList  = current1;
        descendingList = current2;
        while(true){
            if(current2 == null)
                return;
            current1.next = current2.next;
            current1 = current1.next;			
            if(current1 == null)
                return;
            current2.next = current1.next;
            current2 = current2.next;
        }
    }

    private static Node merge(Node list1, Node list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        Node result = null;
        if(list1.data < list2.data){
            result = list1;
            result.next = merge(list1.next, list2);
        }
        else{
            result = list2;
            result.next = merge(list1, list2.next);
        }
        return result;
    }

    private static Node reverse(Node first) {
        Node previous = null;
        Node current  = first;
        while(current != null){
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
}
