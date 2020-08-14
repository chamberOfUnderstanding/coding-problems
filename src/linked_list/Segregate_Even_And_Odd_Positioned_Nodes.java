package linked_list;

/**
 * @author 47un
 *
 * Rearrange a linked list in such a way that all odd position nodes are together and all even position node are together.
 * 
 * Input  : 10 22 30 43 56 70 99
 * Output : 10->30->56->99->22->43->70
 * 
 * http://www.geeksforgeeks.org/rearrange-a-linked-list-such-that-all-even-and-odd-positioned-nodes-are-together/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Quit if the list is 0/1/2 noded list
 *            Scan the list while counting the nodes
 *             Backup next node and disconnect
 *             Even positioned node? add it to the even list
 *             Odd positioned node? add it to the odd list
 *             Advance the list and increase count
 *            Connect odd list to even list
 *             
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Segregate_Even_And_Odd_Positioned_Nodes extends LinkedList_Utils{

    static Node first = null;

    public static void main(String...strings){
        first = readListFromString("10 22 30 43 56 70 99");
        segregateEvenAndOddPositionedNodes();
        System.out.print("\nSegregated List : ");
        displayList(first);			
    }

    private static void segregateEvenAndOddPositionedNodes() {
        if(first == null || first.next == null || first.next.next == null)
            return;
        Node firstEven = null;
        Node firstOdd = null;
        Node even = null;
        Node odd = null;
        Node current = first;
        Node next;
        int count = 1;
        while(current != null){
            next = current.next;
            current.next = null;
            if(count%2 == 0)
                if(firstEven == null){
                    firstEven = current;
                    even = current;
                }
                else{
                    even.next = current;
                    even = even.next;
                }
            else
                if(firstOdd == null){
                    firstOdd = current;
                    odd = current;
                }
                else{
                    odd.next = current;
                    odd = odd.next;
                }
            count++;
            current = next;
        }
        first = firstOdd;
        odd.next = firstEven;
    }
}