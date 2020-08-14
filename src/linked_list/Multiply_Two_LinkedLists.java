package linked_list;

/**
 * @author 47un
 *
 * Given two numbers represented as linked lists. Multiply them
 * 
 * TODO
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Reverse both the numbers
 *            Set zero count as 0
 *            Scan second list
 *              Multiply the first list with each scanned digit of the second
 *              After each multiplication append 'zero count' number of zeroes to the product list's front
 *              Add this value to the final result list (Yes list addition)
 *              Increase zero count
 *            Reverse the final product
 *
 * TIME     : O(m*n) // m and n are the number of digits in the numbers respectively
 * SPACE    : O(1)
 *
 * 
 */

public class Multiply_Two_LinkedLists {

    public static void main(String[] args) {
        int number1 = 16634;
        int number2 = 22;    
        Node list1 = makeAList(number1);
        Node list2 = makeAList(number2);
        Node product = multiply(list1, list2);
        while(product != null) {
            System.out.print(product.data);
            product = product.link;
        }
    }

    // Multiplies two linked lists
    private static Node multiply(Node list1, Node list2) {
        int zeroes = 0;
        Node partialProduct = null;
        Node product = null;
        Node list1Reversed = reverseTheList(null, list1);
        Node list2Reversed = reverseTheList(null, list2);
        Node current = list2Reversed;
        while(current != null) {
            partialProduct = padZeroes(zeroes, multiply(list1Reversed, current.data));
            product = add(partialProduct, product);
            current = current.link;
            zeroes++;
        }
        return reverseTheList(null, product);
    }

    private static Node padZeroes(int zeroes, Node partialProduct) {
        for(int i = 0; i < zeroes; i++) {
            Node zeroNode = new Node(0);
            zeroNode.link = partialProduct;
            partialProduct = zeroNode;
        }
        return partialProduct;
    }

    // Multiplies a linked list with a digit
    private static Node multiply(Node reversedNumber, int digit) {
        Node current = reversedNumber, lastNode = null, result = null;
        int carry = 0;
        while(current != null) {
            int value = current.data * digit + carry;
            carry = value/10;
            Node newNode = new Node(value%10);
            if(result == null) {
                result = newNode;
                lastNode = newNode;
            }
            else {
                lastNode.link = newNode;
                lastNode = newNode;
            }
            current = current.link;     
        }
        if(carry != 0) {
            Node carryNode = new Node(carry);
            lastNode.link = carryNode;
        }
        return result;
    }

    // Adds two linked lists
    private static Node add(Node reversedList1, Node reversedList2) {
        if(reversedList2 == null)
            return reversedList1;
        Node temp1 = reversedList1;
        Node temp2 = reversedList2;
        Node resultStart = null, resultEnd = null;
        int carry = 0;
        while(temp1 != null && temp2 != null) {
            int sum = temp1.data + temp2.data + carry; 
            carry = sum / 10;
            Node newNode = new Node(sum % 10);
            if(resultStart == null) {
                resultStart = newNode;
                resultEnd = newNode;
            }
            else {
                resultEnd.link = newNode;
                resultEnd = newNode;
            }
            temp1 = temp1.link;
            temp2 = temp2.link;
        }
        while(temp1 != null) {
            int data = temp1.data + carry;
            carry = data / 10;
            Node newNode = new Node(data % 10);
            resultEnd.link = newNode;
            resultEnd = newNode;
            temp1 = temp1.link;
        }
        while(temp2 != null) {
            int data = temp2.data + carry;
            carry = data / 10;
            Node newNode = new Node(data % 10);
            resultEnd.link = newNode;
            resultEnd = newNode;
            temp2 = temp2.link;
        }
        if(temp1 == null && temp2 == null && carry != 0) {
            Node carryNode = new Node(carry);
            resultEnd.link = carryNode;
            resultEnd = carryNode;
        }            
        return resultStart;
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

    private static Node reverseTheList(Node previous, Node current) {
        if(current == null)
            return previous;
        Node next = current.link;
        current.link = previous;
        return reverseTheList(current, next);
    }

    private static class Node{
        int data;
        Node link;

        Node(int data){
            this.data = data;
        }
    }
}
