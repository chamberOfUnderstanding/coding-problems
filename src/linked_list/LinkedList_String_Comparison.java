package linked_list;

/**
 * @author 47un
 *
 * Compare two strings represented as linked lists
 * Return 0 if both strings are same
 * Return 1 if first linked list is lexicographically greater
 * Return -1 if second string is lexicographically greater.
 *       
 * Input:  list1 = g->e->e->k->s->a
 *         list2 = g->e->e->k->s->b
 * Output: -1
 *  
 * Input:  list1 = g->e->e->k->s->a
 *         list2 = g->e->e->k->s
 * Output: 1 
 * 
 * Input:  list1 = g->e->e->k->s
 *         list2 = g->e->e->k->s
 * Output: 0
 * 
 * http://www.geeksforgeeks.org/compare-two-strings-represented-as-linked-lists/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Simultaneously scan both lists
 *             Return 1 if left is greater
 *             Return -1 if right is greater
 *            If both lists have been scanned, output 0
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class LinkedList_String_Comparison{

    static Node first1 = null;
    static Node first2 = null;

    public static void main(String...strings){		
        first1 = readListOfCharacters("g e e k s");
        first2 = readListOfCharacters("g e e k s");
        System.out.print("\nComparison results : " + compare(first1, first2));
    }

    private static int compare(Node first1, Node first2) {
        Node current1 = first1;
        Node current2 = first2;
        while(current1 != null && current2 != null){
            if(current1.data > current2.data)
                return 1;
            else if(current1.data < current2.data)
                return -1;
            current1 = current1.link;
            current2 = current2.link;				
        }
        return current1 == null? current2 == null? 0 : -1 : 1;
    }

    static Node readListOfCharacters(String input) {
        char[] nodes = input.replaceAll(" ", "").toCharArray();
        Node newNode, first2 = null, last = null;
        for(char node:nodes){
            newNode = new Node(node);
            if(first2 == null){
                first2 = newNode;
                last = newNode;
            }
            else{
                last.link = newNode;
                last = last.link;
            }
        }
        return first2;		
    }

    static class Node{
        char data;
        Node link;

        Node(char data){
            this.data = data;			
        }
    }
}
