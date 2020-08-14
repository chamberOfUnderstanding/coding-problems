package linked_list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 *
 * Flatten a linked list with next and child pointers
 * 
 * http://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan horizontally till a null is seen
 *              If a node has a child (down pointer) enqueue it
 *            Once the list has no more right nodes, connect this node to a dequeued node
 *            Repeat till the queue is empty
 *
 * TIME     : O(n)
 * SPACE    : O(n)  // If all nodes have a down pointer
 *
 * METHOD 2 : Find the last horizontal node of the first level
 *            Scan from first and if a node has a child (down)
 *              Connect the child to the last node
 *              Update the last node to the last horizontal node of the child
 *            Repeat until current reaches last 
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 * 
 */

public class Flatten_MultilLevelList {

    static Nodev2 root = null;

    public static void main(String...strings){
        prepareTestCase();
        flattenWithoutUsingQueue(root);
        displayList();
    }

    @SuppressWarnings("unused")
    private static void flattenUsingQueue(Nodev2 first) {
        Nodev2 current      = first;
        Queue<Nodev2> queue = new LinkedList<>();
        while(true){
            while(current.right != null) {
                if(current.down != null)
                    queue.add(current.down);
                current = current.right;			            
            }
            if(queue.isEmpty())
                break;
            current.right = queue.remove();
            current = current.right;			
        }
    }

    private static void flattenWithoutUsingQueue(Nodev2 first) {
        if(first == null)
            return;
        Nodev2 last    = first;	    
        Nodev2 current = first;
        while(current != null) {
            while(last.right != null)
                last = last.right;
            if(current.down != null)
                last.right = current.down;
            current = current.right;
        }	    
    }

    static void displayList() {
        Nodev2 current = root;
        while(null != current){
            System.out.print(current.data);
            current = current.right;
            if(null != current)
                System.out.print("->");
        }			
    }

    private static void prepareTestCase() {
        Nodev2 n15 = new Nodev2(15, null, null);
        Nodev2 n19 = new Nodev2(19, n15, null);
        Nodev2 n6  = new Nodev2(6, null, null);
        Nodev2 n3  = new Nodev2(3, null, null);
        Nodev2 n16 = new Nodev2(16, null, n3);
        Nodev2 n2  = new Nodev2(2, null, null);
        Nodev2 n8  = new Nodev2(8, null, null);
        Nodev2 n9  = new Nodev2(9, n8, n19);
        Nodev2 n17 = new Nodev2(17, n6, n9);
        Nodev2 n13 = new Nodev2(13, null, n16);
        Nodev2 n20 = new Nodev2(20, n13, n2);
        Nodev2 n11 = new Nodev2(11, null, null);
        Nodev2 n7  = new Nodev2(7, n11, n17);
        Nodev2 n12 = new Nodev2(12, n7, null);
        Nodev2 n5  = new Nodev2(5, n12, null);
        Nodev2 n4  = new Nodev2(4, n20, null);
        Nodev2 n10 = new Nodev2(10, n5, n4);
        root = n10;
    }
    
    static class Nodev2{
        int data;
        Nodev2 right;
        Nodev2 down;
        
        Nodev2(int data, Nodev2 right, Nodev2 down){
            this.data = data;
            this.right = right;
            this.down = down;
        }
    }
}
