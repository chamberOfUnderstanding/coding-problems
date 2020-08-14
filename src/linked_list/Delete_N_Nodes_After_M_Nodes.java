package linked_list;

/**
 * @author 47un
 *
 * Given a linked list and two integers M and N.
 * Traverse the linked list such that you retain M nodes then delete next N nodes, continue the same till end of the linked list.
 * 
 * Input  : 1 2 3 4 5 6 7 8 9 10 11 
 *          m = 5, n = 6 Output : 1->2->3->4->5
 *          m = 3, n = 2 Output : 1->2->3->6->7->8->11
 * 
 * http://www.geeksforgeeks.org/delete-n-nodes-after-m-nodes-of-a-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Advance M nodes, keep an eye on the Mth node (previous does the job here)
 *            If there're no more nodes, return
 *            Advance current, reset the count
 *            Advance N nodes, the Mth node connects to the N+1 th node upon deleting N nodes
 *            Connect Mth and N+1 th nodes
 *            
 * TIME     : O(n)
 * SPACE    : O(1) 
 *
 * 
 */

public class Delete_N_Nodes_After_M_Nodes extends LinkedList_Utils{

    static Node first = null;

    public static void main(String...strings){
        first = readListFromString("1 2 4 5 6 7 78 34 122 1023");
        int m = 2;
        int n = 3;
        deleteNNodesAfterM(m, n);
        displayList();
    }

    private static void deleteNNodesAfterM(int m, int n) {		
        if(first == null || first.next == null)
            return;
        Node previous = null;
        Node current = first;
        while(current != null){
            int count = 1;
            while(current != null && count++ < m)
                current = current.next;
            previous = current;
            if(current == null)
                return;
            count = 1;
            current = current.next;
            while(current != null && count++ <= n)
                current = current.next;
            previous.next = current;
            if(current == null)
                return;
        }
    }
}