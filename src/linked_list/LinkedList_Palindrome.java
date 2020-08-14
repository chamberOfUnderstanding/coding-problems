package linked_list;

import java.util.Stack;

/**
 * @author 47un
 *
 * Check if a list is palindrome
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Use a stack
 *
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * METHOD 2 : Break the list into two
 *             If the list has ODD length, then the middle element has to be ignored from palindrome check, so list2 starts at next of middle 
 *             If the list has EVEN length, list2 starts at the middle
 *            Disconnect
 *            Reverse the second half
 *            Compare the reversed second half with the first
 *            Reverse the second half again
 *            Re-attach
 *
 * To check if a list has odd length or even length, scan it using a pointer that jumps two nodes (temp.next.next)
 * Scan the list until temp = null or temp.next = null
 * If the list is of odd length, temp will be null at the end, else it will not be null
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

@SuppressWarnings("unused")
public class LinkedList_Palindrome extends LinkedList_Utils{
   
    public static void main(String...strings){
        readListFromString("1 2 3 4 5 5 4 3 2 1");
        System.out.println("\nPalindrome? " + (isPalindromeII()? "Yes" : "No"));
        displayList();
    }

    private static boolean isPalindromeI() {
        if(first == null)
            return false;
        Stack<Integer> stack = new Stack<>();
        Node current = first;
        while(current != null){
            stack.push(current.data);
            current = current.next;
        }
        current = first;
        while(!stack.isEmpty()){
            if(stack.pop() != current.data)
                return false;
            current = current.next;
        }			
        return true;
    }

    private static boolean isPalindromeII(){
        if(first == null)
            return false;
        Node temp   = first;
        Node middle = first;
        Node previousOfMiddle = null;
        while(temp != null && temp.next != null){
            previousOfMiddle = middle;
            middle = middle.next;
            temp   = temp.next.next;
        }
        boolean isOddLength = temp != null;
        boolean palindrome  = true;
        Node list2 = isOddLength? middle.next : middle;
        previousOfMiddle.next = null;
        Node current1 = first;
        Node current2 = list2 = reverse(list2);
        while(current1 != null && current2 != null){
            if(current1.data != current2.data){
                palindrome = false;
                break;
            }				
            current1 = current1.next;
            current2 = current2.next;
        }
        list2 = reverse(list2);
        if(isOddLength){
            previousOfMiddle.next = middle;
            middle.next = list2;
        }
        else
            previousOfMiddle.next = list2;
        return palindrome;
    }

    private static Node reverse(Node first) {		
        Node previous  = null;
        Node current = first;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
}
