package linked_list;

/**
 * @author 47un
 *
 * Check if two lists are identical
 * Two Linked Lists are identical when they have same data and arrangement of data is also same.
 * For example Linked lists a (1->2->3) and b(1->2->3) are identical.
 * 
 * http://www.geeksforgeeks.org/identical-linked-lists/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan both the lists simultaneously
 *            Check if their data is the same
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Identical_Lists extends LinkedList_Utils{

    public static void main(String...strings){
        Node first1 = readListFromString("1 2 4 5 6 7 88 99");
        Node first2 = readListFromString("1 2 4 5 6 7 88 9");
        System.out.println("Are they identical? : " + areTheyIdentical(first1, first2));
    }

    private static boolean areTheyIdentical(Node first1, Node first2) {
        Node current1 = first1;
        Node current2 = first2;
        while(current1 != null && current2 != null){
            if(current1.data != current2.data)
                return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        return current1 == null && current2 == null;
    }
}
