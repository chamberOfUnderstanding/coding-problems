package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given a linked list of 0s, 1s and 2s, sort it.
 * 
 * Input  : 2 2 2 2 1 1 1 1 0 0 0 0 0 0
 * Output : 0->0->0->0->0->0->1->1->1->1->2->2->2->2
 * 
 * http://www.geeksforgeeks.org/sort-a-linked-list-of-0s-1s-or-2s/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Maintain list for ones, list for zeroes and list for twos
 *            Scan the main list and add them to one of the three lists
 *              If it's 0, stick it at the beginning of the zero list.
 *              So, the 0 that's discovered first becomes the last zero in the zero list. Keep an eye on this zero (lastZero)
 *              Follow the same procedure for 1 and 2
 *              For 2, no need to track the last node
 *            Connect the lists together
 *              The first node of the sorted list is -> first of zero list (if it's not null) else first of one list (again, shouldn't be null)
 *              else first of two list
 *              If there are some zeroes, attach first of one to this, if first of one is not null, else attach first of two
 *              If there are some ones, attach first of two to this
 *              
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * METHOD 2 : Count the 0,1,2         
 *            Scan the list once, count the number of 0,1,2 and scan the list again, but this time,
 *            replace the nodes with 0,1 or 2, sequentially until their counts hit 0
 *            This approach takes two scans of the list
 *            
 * TIME     : O(n)  // Two scans
 * SPACE    : O(1)
 * 
 */

public class Sort_Three_Itemed_List extends LinkedList_Utils{

    static Node first = null;

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            first = readList(scanner);
            sort012v2();
            System.out.print("\nSorted list : ");
            displayList(first);
        }
    }

    private static void sort012v2() {
        int zeroes   = 0;
        int ones     = 0;
        Node current = first;
        while(current != null) {
            switch(current.data) {
            case 0  : zeroes++; break;
            case 1  : ones++;   break;
            case 2  : break;
            default : System.out.println("Weird character detected!"); System.exit(0); 
            };
            current = current.next;
        }
        current = first;
        while(current != null) {
            current.data = zeroes-- > 0? 0 : ones-- > 0? 1 : 2;
            current = current.next;
        }
    }

    @SuppressWarnings("unused")
    private static void sort012() {
        if(first == null || first.next == null)
            return;
        Node current = first;
        Node firstZero = null;
        Node firstOne = null;
        Node firstTwo = null;
        Node lastZero = null;
        Node lastOne = null;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = null;
            switch(current.data){

            case 0 : 
                if(firstZero == null){
                    firstZero = current;
                    lastZero = current;
                }
                else{
                    current.next = firstZero;
                    firstZero = current;
                }
                break;
            case 1 :
                if(firstOne == null){
                    firstOne = current;
                    lastOne = current;
                }
                else{
                    current.next = firstOne;
                    firstOne = current;
                }
                break;
            case 2 :
                if(firstTwo == null)
                    firstTwo = current;				
                else{
                    current.next = firstTwo;
                    firstTwo = current;	
                }
                break;
            }
            current = next;
        }
        first = firstZero != null? firstZero : firstOne != null? firstOne : firstTwo;
        if(lastZero != null)
            lastZero.next = firstOne != null? firstOne : firstTwo;
        if(lastOne != null)
            lastOne.next = firstTwo;
    }
}
