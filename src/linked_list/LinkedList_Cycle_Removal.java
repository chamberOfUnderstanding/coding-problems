package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Detect and remove a cycle in a linked list
 * 
 * http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Use Floyd's algorithm to detect a loop
 *            After detecting the loop, set the fast pointer to start of the list and advance both
 *            The point where they meet is the start of the cycle
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * The math behind this method :
 * 
 * http://stackoverflow.com/questions/2936213/explain-how-finding-cycle-start-node-in-cycle-linked-list-work
 * 
 * If the distance to cycle from start is x
 * If the distance from cycle start to meeting point is y
 * If the distance from meeting point, back to cycle start is z, then
 * 
 * Distance traveled by tortoise = x +  y
 * Distance traveled by hare     = x + 2y + z
 * Time is a constant here, and distance / speed = time
 * 2 (x + y) = x + 2y + z
 * => x = z
 * 
 * Hence by moving slowPointer to start of linked list, and making both slowPointer and fastPointer to move one node at a time, they both have same distance to cover.
 * 
 * Since fast moves at twice the speed,
 * 
 * Distance traveled by fast pointer = 2 * (Distance traveled by slow pointer)
 *           (m + n*x + k) = 2*(m + n*y + k)
 *           
 * m = start of the cycle
 * n = length of the cycle
 * k = the point where slow and fast meet during cycle detection test
 * x = Number of complete cyclic rounds made by fast pointer before they meet first time
 * y = Number of complete cyclic rounds made by slow pointer before they meet first time
 * 
 * => m+k = (x-2y) * n or m+k is a multiple of n
 * 
 * When the slow is set back to first, fast is k distance away from start of the loop.
 * After slow travels a distance of m (reaches start of the cycle), fast is at m+k from start of the loop.
 * Since m+k is divisible by n, m+k/n is 0 ergo fast is also at the start of the cycle 
 * 
 * 
 */

public class LinkedList_Cycle_Removal extends LinkedList_Utils{

    public static void main(String...strings){
        readListFromString("1 2 499 -22 59043");
        Node thisNodeLoopsToThirdNode = new Node(1007);
        last.next = thisNodeLoopsToThirdNode;
        thisNodeLoopsToThirdNode.next = first.next.next;
        removeCycle();
    }

    private static void removeCycle() {
        Node slow = first;
        Node fast = first;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
                slow = first;
                Node previous = null;
                while(slow != fast){
                    previous = slow;
                    slow = slow.next;
                    fast = fast.next;
                }
                previous.next = null;
                return;
            }
        }
    }
}
