package linked_list;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 47un
 *
 * There are two singly linked lists in a system.
 * By some programming error the end node of one of the linked list got linked into the second list, forming a inverted Y shaped list.
 * Write a program to get the point where two linked list merge.
 * 
 * e.g.  3
 *       |
 *       6
 *       |
 *       9  10
 *       \  /
 *        15
 *         |
 *         30
 *  
 * Intersection point is 15 
 * 
 * http://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan both lists, if either reaches the end, route it back to first
 *            This method is bound to give the intersection point in 2-3 passes (Why?)
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 * METHOD 2 :  Creating a cycle
 *             Y SHAPED LISTS
 *             Scan one list and get the last node. Also get the length of this list
 *             Connect this last node to the first of the other and create a loop
 *             Scan the second list until length of first
 *             Scan with another pointer and advance both until they meet
 *             This point is the intersection
 *  
 * TIME     : O(m + n)
 * SPACE    : O(1)
 * 
 * METHOD 3 : Length of the lists
 *             Y SHAPED LISTS
 *             Since this is a Y shaped list, the shorter list must have a connection towards the right end of the longer list,
 *             Hence, when we scan 'difference' number of lists of first, we will reach a point from where the intersections are possible
 *             If list 1 is of length 5 and list 2 is 3, then the intersection can be at 3,4,5 nodes of list 1 (Remember Y shaped list) 
 *             Get the length of both the lists, and calculate their difference
 *             Scan the longer list until the node 'difference'
 *             Start scanning the shorter list and the longer list simultaneously here onwards
 *             They meet at the intersection
 *             
 * TIME     : O(m + n)
 * SPACE    : O(1)
 * 
 * METHOD 4  : Hashing
 *             X SHAPED LISTS
 *             Y SHAPED LISTS
 *             Add hash codes of all nodes of one list onto a hash set
 *             Scan the second list and see if any of the node's hash codes are already in the set

 * TIME     : O(m + n)
 * SPACE    : O(Max of m,n)
 * 
 * METHOD 5 :  Y SHAPED LISTS  
 *             This method DETECTS an intersection
 *             Scan both lists till the end
 *             Check if their last nodes are the same
 *             If yes, then there's an intersection
 *             
 * TIME     : O(m + n)
 * SPACE    : O(1)
 *       
 */

public class Intersection_Point extends LinkedList_Utils{

    static Node first1 = null;
    static Node first2 = null;

    public static void main(String...strings){
        prepareTestCase();
        System.out.println("Do they intersect? " + (detectIntersection()? "YES" : "NO"));	
        System.out.println("Method 0 : Intersection at " + findIntersectionPointI());
        System.out.println("Method 1 : Intersection at " + findIntersectionPointI());
        System.out.println("Method 2 : Intersection at " + findIntersectionPointII());
        System.out.println("Method 3 : Intersection at " + findIntersectionPointIII());
    }

    private static int findIntersectionPointI() {
        Node list1 = first1;
        Node list2 = first2;
        if(list1 == null || list2 == null){
            System.out.println("Either list is empty!\nQuitting....");
            System.exit(0);
        }
        int allowedPasses = 5;
        while(allowedPasses-- > 0){
            if(list1 == list2)
                return list1.data;
            if(list1.next == null)
                list1 = first1;
            if(list2.next == null)
                list2 = first2;            
            list1 = list1.next;
            list2 = list2.next;
        }
        return -1;
    }

    private static int findIntersectionPointII() {
        Node current = first1;
        Node last = null;
        int length1 = 0;
        while(current != null){
            last = current;
            current = current.next;
            length1++;
        }
        last.next = first1;
        Node current1 = first2;
        Node current2 = first2;
        while(length1-- > 0)
            current1 = current1.next;
        while(current1 != current2){
            current1 = current1.next;
            current2 = current2.next;
        }
        last.next = null;
        return (current1 == null || current2 == null)? Integer.MAX_VALUE : current1.data;		
    }

    @SuppressWarnings("unused")
    private static int findIntersectionPointIV() {
        Node current = first1;
        Set<Integer> set = new HashSet<>();
        while(current != null){
            if(!set.contains(current.hashCode()))
                set.add(current.hashCode());
            current = current.next;
        }
        current = first2;
        while(current != null){
            if(set.contains(current.hashCode()))
                return current.data;
            current = current.next;
        }
        return Integer.MAX_VALUE;
    }
    

    private static int findIntersectionPointIII(){
        Node current = first1;
        int lengthOfList1 = 0, lengthOfList2 = 0, difference = 0;
        while(current != null){
            current = current.next;
            lengthOfList1++;
        }
        current = first2;
        while(current != null){
            current = current.next;
            lengthOfList2++;
        }
        difference = Math.abs(lengthOfList1 - lengthOfList2);
        if(difference == 0)
            current = first1;
        else
            current = lengthOfList1 > lengthOfList2 ? first1 : first2;
            while(current != null && difference-- > 0)
                current = current.next;
            Node current1 = first2;
            while(current != null && current1 != null){
                if(current == current1)
                    return current.data;
                current = current.next;
                current1 = current1.next;
            }
            return Integer.MAX_VALUE;
    }

 
    private static boolean detectIntersection(){
        Node current1 = first1, current2 = first2;
        while(current1.next != null)
            current1 = current1.next;
        while(current2.next != null)
            current2 = current2.next;
        return current1 == current2? true : false;
    }

    private static void prepareTestCase() {
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        Node node9 = new Node(9);
        Node node15 = new Node(15);
        Node node30 = new Node(30);
        Node node10 = new Node(10);
        node3.next = node6;
        node6.next = node9;
        node9.next = node15;
        node15.next = node30;
        node10.next = node15;
        first1 = node3;
        first2 = node10;
    }
}
