package linked_list;

/**
 * @author 47un
 *
 * Given the reference to a node, delete the node. The head of the list is not given
 * 
 * http://www.geeksforgeeks.org/given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : A simple solution is to traverse the linked list until you find the node you want to delete. 
 *            But this solution requires pointer to the head node which contradicts the problem statement.
 *            So copy the data of the next node to this node and delete the next node
 *            In case the node to be deleted is the last node, replace it with a dummy
 *            
 * TIME     : O(1)
 * SPACE    : O(1)
 *
 * 
 */

public class Delete_Node_Given_The_Reference extends LinkedList_Utils{

    public static void main(String...strings){
        readListFromString("1 2 4 5 6 7 78 34 122 1023");
        deleteNodeGivenTheReference(first);
        displayList();
    }

    private static void deleteNodeGivenTheReference(Node node) {
        Node next = node.next;
        if(next == null)
            node.data = Integer.MIN_VALUE;
        else{
            node.data = next.data;
            node.next = next.next;
        }
    }
}
