package linked_list;

import linked_list.LinkedList.Node;

/**
 * @author 47un
 *
 * Swap nodes in a linked list, without swapping data
 * Input:  10->15->12->13->20->14,  x = 12, y = 20
 * Output: 10->15->20->13->12->14
 * 
 * http://www.geeksforgeeks.org/swap-nodes-in-a-linked-list-without-swapping-data/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : If x and y are equal, nothing's there to be done
 *            Get the previous node of x and y
 *            If either nodes are not present, exit
 *            If x is the first node, update first, else connect y to x
 *             Same goes for y
 *            Swap next nodes of x and y
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Swap_Nodes {

    static LinkedList linkedList = new LinkedList();

    public static void main(String...strings){
        String input = "1 2 3 4 5 6 7 8";
        int x = 4;
        int y = 5;
        String[] nodes = input.split(" ");
        for(String node : nodes)
            linkedList.insertNode(Integer.parseInt(node), 1);
        System.out.print("\nEnter nodes to be swapped : ");			
        swapNodes(x, y);
        linkedList.displayList();
    }

    public static void swapNodes(int x, int y){
        if(x == y)
            return;        
        Node previousOfX = null;
        Node previousOfY = null;
        Node currentOfX = LinkedList.first;
        Node currentOfY = LinkedList.first; 
        while(null != currentOfX && currentOfX.data != x){
            previousOfX = currentOfX;
            currentOfX = currentOfX.link;
        }
        while(null != currentOfY && currentOfY.data != y){
            previousOfY = currentOfY;
            currentOfY = currentOfY.link;
        } 
        if(currentOfX == null || currentOfY == null){
            System.out.println("\nEither nodes are absent");
            return;
        }
        if(previousOfX == null)
            LinkedList.first = currentOfY;
        else
            previousOfX.link = currentOfY;
        if(previousOfY == null)
            LinkedList.first = currentOfX;
        else
            previousOfY.link = currentOfX;
        Node temp = currentOfX.link;
        currentOfX.link = currentOfY.link;
        currentOfY.link = temp;
    }
}
