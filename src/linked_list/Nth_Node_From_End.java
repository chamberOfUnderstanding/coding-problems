package linked_list;

import linked_list.LinkedList.Node;

/**
 * @author 47un
 *
 * Given a Linked List and a number n.Find the value at the n'th node from end of the Linked List
 * 
 * e.g. 20->4->15->35 
 *      N = 4 => 20
 *      N = 3 => 4
 * 
 * http://www.geeksforgeeks.org/nth-node-from-the-end-of-a-linked-list/
 *  
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Get the nth node from first
 *            Have another node initialized to head
 *            After nth from first is found, update both nodes until first node hits end of the list
 *            Now the second node has the required node
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * METHOD 2 : Get the (length - n)th node from first
 *            Find length, find length - n, find that node
 *            
 * TIME     : O(n)   // Does two scans of the list
 * SPACE    : O(1)
 * 
 */

public class Nth_Node_From_End{

    static LinkedList linkedList = new LinkedList();

    public static void main(String...strings){
        String input   = "1->2->3->4->5->6->7";
        String[] nodes = input.split("->");
        for(String node : nodes)
            linkedList.insertNode(Integer.parseInt(node),1);
        int n = 4;
        Integer nThNode =  nThNodeFromLastI(LinkedList.first, n);
        if(null != nThNode)
            System.out.println("\nNth node from the end is : " + nThNode);
        nThNode = nThNodeFromLastII(LinkedList.first, n);
        if(null != nThNode)
            System.out.println("\nNth node from the end is : " + nThNode);
    }

    private static Integer nThNodeFromLastI(Node first, int n) {
        if(null == first)
            return null;
        Node nThFromFirst = first, nThFromLast = first;
        while(null != nThFromFirst && n-- > 0)
            nThFromFirst = nThFromFirst.link;
        if(null == nThFromFirst && n!= 0){
            System.out.println("There are fewer than N nodes!");
            return null;
        }
        while(null != nThFromFirst){
            nThFromFirst = nThFromFirst.link;
            nThFromLast = nThFromLast.link;
        }
        return nThFromLast.data;
    }

    private static Integer nThNodeFromLastII(Node first, int n) {
        if(null == first)
            return null;
        int length = 0;
        Node temp = first;
        while(null != temp){
            temp = temp.link;
            length++;
        }
        if(n > length){
            System.out.println("There are fewer than N nodes!");
            return null;
        }
        int nThNode = length - n;
        temp = first;		
        while(nThNode-- > 0)
            temp = temp.link;
        return temp.data;
    }
}
