package linked_list;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 47un
 *
 * Given two Linked Lists, create union and intersection lists that contain union and intersection of the elements present in the given lists.
 * Order of elements in output lists doesn’t matter.
 * Example:
 * 
 * Input: List1: 10->15->4->20
 *        List2:  8->4->2->10
 *        
 * Output:Intersection List: 4->10
 *        Union List:        2->8->20->4->15->10
 * 
 * 
 * http://www.geeksforgeeks.org/union-and-intersection-of-two-linked-lists/
 *
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Hashing
 *            Scan list1, add all unique nodes to the hash set, keep an eye on the last node (union) 
 *            Scan list2
 *             Get next node, disconnect it
 *             If the hash set does not contain this, stick it at the end of the union list
 *             Else stick it at the end of the intersection list
 *             
 * TIME     : O(m + n)
 * SPACE    : O(m + n)
 *
 * 
 */

public class Union_And_Intersection extends LinkedList_Utils{

	static Node first1 = null;
	static Node first2 = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first1 = readList(scanner);
			first2 = readList(scanner);			
			unionAndIntersection(first1,first2);			
		}
	}

	private static void unionAndIntersection(Node first1, Node first2) {
		if(first1 == null || first2 == null)
			return;
		Node current = first1;
		Node unionFirst = first1;
		Node intersectionFirst = null;
		Node intersectionLast = null;
		Node unionLast = null;
		Node next = null;
		Set<Integer> hashSet = new HashSet<>();
		while(current != null){
			if(!hashSet.contains(current.data))
				hashSet.add(current.data);
			unionLast = current;
			current = current.next;
		}
		current = first2;
		while(current != null){
			next = current.next;
			current.next = null;
			if(!hashSet.contains(current.data)){
				unionLast.next = current;
				unionLast = unionLast.next;
			}
			else{				
				if(intersectionFirst == null){
					intersectionFirst = current;
					intersectionLast = intersectionFirst;
				}
				else{
					intersectionLast.next = current;
					intersectionLast = intersectionLast.next;
				}				
			}
			current = next;
		}
		System.out.print("\nUnion        : ");
		displayList(unionFirst);
		System.out.print("\nIntersection : ");
		displayList(intersectionFirst);
	}
}
