package linked_list;

/**
 * @author 47un
 *
 * Given a linked list, check if the the linked list has cycle or not
 * 
 * http://www.geeksforgeeks.org/write-a-c-function-to-detect-loop-in-a-linked-list/
 * 
 * =========
 * METHOD 1 Hashing
 * ===========
 * 
 * Traverse the list and keep adding the nodes into a hash set
 * If NULL is seen, there is no cycle
 * If the node is already present in the hash set, there is a cycle

 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * =========
 * METHOD 2 : Floyd’s Cycle-Finding Algorithm
 * ==================
 * 
 * Use a slow pointer (jumps to the next node) and a fast pointer (skips a node)
 * Scan till a null is seen (no cycles) or these pointers meet (there is a cycle)            
 * 
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Cycle_Detection extends LinkedList_Utils{

    public static void main(String...strings){
        readListFromString("1 2 4 5 6 7 7 8 10");
        Node thisNodeLoopsToThirdNode = new Node(1007);
        last.next = thisNodeLoopsToThirdNode;
        thisNodeLoopsToThirdNode.next = first.next.next;
        System.out.println(detectCycle()? "There's a loop!" : "This guy is clean");			
    }

    private static boolean detectCycle() {
        if(first == null || first.next == null)
            return false;
        if(first.next == first)
            return true;
        Node slow = first;
        Node fast = first;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;		
            if(slow == fast)
                return true;
        }
        return false;
    }
}
