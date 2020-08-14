package stack;

import java.util.Scanner;

/**
 * @author 47un
 * 
 * http://quiz.geeksforgeeks.org/stack-set-1/
 * 
 * Stack is a linear data structure which follows a particular order in which the operations are performed.
 * The order may be LIFO(Last In First Out).
 * Mainly the following three basic operations are performed in the stack:
 * Push: Adds an item in the stack. If the stack is full, then it is said to be an Overflow condition.
 * Pop: Removes an item from the stack. The items are popped in the reversed order in which they are pushed. If the stack is empty, then it is said to be an Underflow condition.
 * Peek: Get the topmost item. * 
 * Stack of plates. The plate which is at the top is the first one to be removed, i.e. the plate which has been placed at the bottommost position remains in the stack for the longest period of time.
 * So, it can be simply seen to follow LIFO order.
 * Implementation:
 * There are two ways to implement a stack:
 *  Using array
 *  Using linked list
 */

public class Stack_Implementation {

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            int[] stackUsingArray = new int[100];
            int top = -1;
            LinkedList stackUsingLinkedList = new LinkedList();
            System.out.print("\n"
                    + "Enter operation\n"
                    + "1. push x : Pushes x\n"
                    + "2. pop : Pops\n"
                    + "3. peek : Peeks\n"
                    + "4. quit\n");
            while(true){
                String[] operation = scanner.nextLine().split(" ");
                if(operation[0] == "quit")
                    quit();
                switch(operation[0]){
                case "push" :
                    if(top == 100) {
                        System.out.println("\nOverflow!");
                        continue;
                    }
                    int data = Integer.parseInt(operation[1]);
                    stackUsingLinkedList.push(data);
                    stackUsingArray[++top] = data;
                    break;
                case "pop" :
                    if(stackUsingLinkedList.isEmpty() || top == -1){
                        System.out.println("Empty stacks! Pop failed");
                        continue;
                    }
                    System.out.println("Popped : " + stackUsingLinkedList.pop().data + " " + stackUsingArray[top--]);
                    break;
                case "peek" :
                    if(top == -1 || stackUsingLinkedList.isEmpty())
                        System.out.println("Stack is empty");
                    else
                        System.out.println("Peeking : " + stackUsingLinkedList.peek() + " " + stackUsingArray[top]);
                    break;
                default : quit();
                }
            }
        }
    }

    private static class LinkedList{

        static Node top = null;

        private class Node{
            int data;
            Node link;

            Node(int data){
                this.data = data;
            }
        }

        public Integer peek() {
            if(top != null)
                return top.data;
            return null;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public Node pop() {
            Node popped = top;
            if(top != null)
                top = top.link;
            return popped;
        }

        public void push(int data) {
            Node node = new Node(data);
            if(top == null)
                top = node;
            else{
                node.link = top;
                top = node;
            }
        }
    }

    private static void quit() {
        System.out.println("Quitting..");
        System.exit(0);
    }
}
