package linked_list;

/**
 * @author 47un
 *
 * Delete nodes with larger values to the right
 * Input  : 1->66->2->634->88->78->56->59
 * Output : 634->88->78->59
 * 
 * http://www.geeksforgeeks.org/delete-nodes-which-have-a-greater-value-on-right-side/ 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Reverse the list
 *            Save the next node and disconnect the current node (This breaks links to smaller nodes)
 *            If this node is > maximum value
 *              Update maximum and add this node to the end of the list
 *            Else advance
 *            Reverse again
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Delete_Nodes_With_Large_Values_To_The_Right extends LinkedList_Utils{

    static Node first = null;

    public static void main(String...strings){
        first = readListFromString("1 2 41232 25 6 7 7898 34 122 1023");
        delete();
        System.out.print("\nList after deletion : ");
        displayList(first);
    }

    private static void delete() {
        reverse(null, first);		
        int maximum   = Integer.MIN_VALUE;
        Node current  = first;
        Node lastNode = null;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = null;
            if(current.data >= maximum){
                maximum = current.data;
                if(lastNode != null)
                    lastNode.next = current;
                lastNode = current;
            }
            current = next;
        }
        reverse(null, first);
    }

    private static void reverse(Node previous, Node current) {
        if(current == null)
            first = previous;
        else{
            Node next = current.next;
            current.next = previous;
            reverse(current,next);
        }
    }
}
