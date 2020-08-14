package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * A number is represented as a linked list such that each digit corresponds to a node in linked list. Add 1 to it.
 * For example 1999 is represented as (1-> 9-> 9 -> 9) and adding 1 to it should change it to (2->0->0->0)
 * MSB is the first node of the list
 * 
 * http://www.geeksforgeeks.org/add-1-number-represented-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Reverse the list
 *            Add 1 to the first item, find carry, add carry to successive items
 *            If carry is 1 after scanning the entire list, add a node with data 1 to the end of the list
 *            Reverse the list again
 *
 * TIME     : O(n) // Involves 2 list reversals!
 * SPACE    : O(1)
 *
 * METHOD 2 : Scan and find the rightmost non nine node
 *            If such a node exists, add 1 to it, set current as next of this node
 *            If such a node does'nt exist (i.e. number is all 9s like 9999)
 *              Then add a new node with data as 1 to the start of the list
 *              current is next of this node
 *            Scan from current to the end of the list and set the data of all items to 0
 *
 * TIME     : O(n) // Faster as no list reversal is involved
 * SPACE    : O(1)
 * 
 */

public class Add_One_To_A_LinkedList extends LinkedList_Utils{

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            readListFromString("3 2 3 3 8 9 9 9 9 9");
            addOneToTheList();
            displayList();
            addOneOptimal();
            displayList();
        }
    }

    private static void addOneOptimal() {
        Node current = first;
        Node nonNineNode = null;
        while(current != null) {
            if(current.data != 9)
                nonNineNode = current;
            current = current.next;                
        }
        if(nonNineNode == null) {
            Node one = new Node(1);
            one.next = first;
            first = one;
            current = first.next;
        }
        else {
            nonNineNode.data += 1;
            current = nonNineNode.next;
        }
        while(current != null) {
            current.data = 0;
            current = current.next;
        }        
    }

    private static void addOneToTheList() {
        reverse(null, first);
        int carry = 1;
        int sum   = 0;
        Node current = first;
        while(null != current && carry == 1){
            sum = current.data + carry;
            current.data = sum % 10;
            carry = sum / 10;
            current = current.next;
        }
        if(carry == 1) {
            Node one = new Node(1);
            last.next = one;
            last = one;
        }
        reverse(null, first);
    }

    private static void reverse(Node previous, Node current){
        Node next = current.next;
        current.next = previous;
        if(previous == null)
            last = current;
        if(next == null)
            first = current;
        else
            reverse(current,next);
    }
}
