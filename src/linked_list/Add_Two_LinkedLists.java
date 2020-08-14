package linked_list;

/**
 * @author 47un
 *
 * Add two numbers represented as linked lists
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Reverse both lists
 *            Scan both simultaneously and add corresponding items, maintain a carry as well
 *            Scan the lists individually to add missing items to the sum
 *            Reverse the sum
 *            
 *            The last reversal can be avoided by adding items to the front of the sum list
 *            
 * TIME     : O(Max(m, n)) // m and n are the number of digits in the numbers that need to be added
 * SPACE    : O(1)
 *
 * 
 */

public class Add_Two_LinkedLists {

    public static void main(String[] args) {
        int number1 = 16634;
        int number2 =  9379;    
        Node sum = add(makeAList(number1), makeAList(number2));
        while(sum != null) {
            System.out.print(sum.data);
            sum = sum.link;
        }
    }

    private static Node add(Node list1, Node list2) {
        Node temp1 = reverseTheList(null, list1);
        Node temp2 = reverseTheList(null, list2);
        Node resultStart = null;
        int carry = 0;
        while(temp1 != null || temp2 != null) {
            int digit1 = temp1 != null? temp1.data : 0;
            int digit2 = temp2 != null? temp2.data : 0;
            int sum = digit1 + digit2 + carry; 
            carry   = sum / 10;
            Node newNode = new Node(sum % 10);
            newNode.link = resultStart;
            resultStart  = newNode;
            if(temp1 != null)
                temp1 = temp1.link;
            if(temp2 != null)
                temp2 = temp2.link;
        }
        if(carry != 0) {
            Node carryNode = new Node(carry);
            carryNode.link = resultStart;
            resultStart    = carryNode;
        }            
        return resultStart;
    }

    private static Node reverseTheList(Node previous, Node current) {
        Node next = current.link;
        current.link = previous;
        if(next == null)
            return current;
        return reverseTheList(current, next);
    }

    // Give a number, this will make a list out of it
    private static Node makeAList(int number) {
        Node first = null;
        while(number != 0) {
            Node newNode = new Node(number % 10);
            if(first == null) 
                first = newNode;
            else {
                newNode.link = first;
                first = newNode;
            }
            number /= 10;
        }
        return first;
    }

    private static class Node{
        int data;
        Node link;

        Node(int data){
            this.data = data;
        }
    }
}
