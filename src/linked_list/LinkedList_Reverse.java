package linked_list;

import java.util.Stack;

/**
 * @author 47un
 *
 * Reverse a linked list
 * 
 * http://www.geeksforgeeks.org/write-a-function-to-reverse-the-nodes-of-a-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Iterative, Recursive
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 * METHOD 2 : Stack
 *            Push all nodes into a stack
 *            Pop them and link them together
 *             
 * TIME     : O(n)
 * SPACE    : O(n)
 */

public class LinkedList_Reverse extends LinkedList_Utils{

    public static void main(String...strings){
        readListFromString("1 2 2 3 4 5 6 7 8 9");
        System.out.println("Reversing..");
//        reverseRecursive(null,first);
//        displayList();
//        System.out.println("\nReversing..");
        reverseIterative2();
        displayList();
//        first = reverseCute(null, first);
//        displayList();
    }

    static void reverseIterative(){
        Node previous = null;
        Node current = first;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        first = previous;
    }
    
    static void reverseIterative2(){
    	Node current, previous, succ;
        current = previous = first;
        current = current.next;
        previous.next = null;
        while(current != null){
            succ = current.next;
            current.next = previous;
            previous = current;
            current = succ;
        }
        first = previous;
    }
    

    static void reverseRecursive(Node previous, Node current){
        Node next = current.next;
        current.next = previous;	    
        if(next == null) {
            first = current;
            return;
        }
        reverseRecursive(current,next);
    }

    @SuppressWarnings("unused")
    private void reverseUsingStack(){
        Node temp = first;
        Node poppedNode;
        Stack<Node> stack = new Stack<>();
        while(null != temp){
            stack.push(temp);
            temp = temp.next;
        }
        temp = stack.pop();
        first = temp;
        while(!stack.isEmpty()){
            poppedNode = stack.pop();
            poppedNode.next = null;
            temp.next = poppedNode;
            temp = temp.next;
        }
    }

    private static Node reverseCute(Node previous, Node current){
        if(current == null)
            return previous;
        Node next = current.next;
        current.next = previous;
        return reverseCute(current, next);
    }
}
