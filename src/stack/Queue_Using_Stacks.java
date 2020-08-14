package stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author 47un
 * 
 * Given two stacks, implement a queue
 * 
 * http://www.geeksforgeeks.org/queue-using-stacks/
 * 
 * Here's the thing. There are two ways to do this.
 * 
 * =========================
 * METHOD 1 : Costly enqueue
 * =========================
 * Enqueue : Copy all items from the stack1 to stack2
 *           Push the item to stack1
 *           Copy all the items back to stack1 from stack2.
 *           -  But enqueue is too costly here. For,say, 100000 items, enqueue costs O(100000) + O(100000), coz the copy happens twice
 * Dequeue : Spit out the top of stack1 (8 gets returned in the above e.g.)
 * 
 * e.g.
 * Enqueue 8
 * S1        8
 * S2        
 * Q1 8
 *    
 * Enqueue 9
 * S1       8     S1       9     S1       8 9
 * S2          => S2       8  => S2          
 * Q 8 9          Q 8 9          Q  8 9
 * 
 * TIME  : O(n) O(1)
 * SPACE : O(1)
 * 
 * =========================   
 * METHOD 2 : Costly dequeue
 * =========================
 * Enqueue : Push the item onto stack1 and lol away
 * Dequeue : If both stacks are empty, then the queue is empty.
 *           If stack2 is empty, copy all items from stack1 to stack2 and pop stack2.
 * e.g.
 * Enqueue 8
 * S1      8
 * S2        
 * Q  8
 *    
 * Enqueue 9
 * S1      9 8
 * S2                   
 * Q  8 9      
 *    
 * Enqueue 10
 * S1    10 9 8
 * S2                   
 * Q  8 9 10
 *    
 * Dequeue : 8 should be returned, which is at the bottom of stack1, so empty it onto stack2 and pop stack2
 * S1    10 9 8      S1              S1
 * S2            =>  S2    8 9 10 => S2    9 10                 
 * Q  8 9 10         Q  8 9 10       Q  9 10    
 *    
 * Dequeue : If stack2 is not empty, just pop it (If you see the above example, stack2 clearly mimics the queue
 * S1                S1          
 * S2     9 10   =>  S2    10                 
 * Q  9 10           Q  10
 * 
 * TIME  : O(1) O(n)
 * SPACE : O(1)
 * 
 */

public class Queue_Using_Stacks {

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("\n"
                    + "Pick a queue :\n"
                    + "1. Costly enqueue\n"
                    + "2. Costly dequeue");
            int queue = Integer.parseInt(scanner.nextLine());
            if(queue == 1)
                queueWithCostlyEnqueue(scanner);
            else
                queueWithCostlyDequeue(scanner);
        }
    }

    private static void queueWithCostlyEnqueue(Scanner scanner) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        System.out.print("\n"
                + "Enter operation\n"
                + "1. enqueue x : Enqueues x\n"
                + "2. dequeue : Dequeues\n"
                + "3. quit\n");
        while(true){
            String[] operation = scanner.nextLine().split(" ");
            if(operation[0] == "quit")
                quit();
            switch(operation[0]){
            case "enqueue" :
                int data = Integer.parseInt(operation[1]);
                while(!stack1.isEmpty())
                    stack2.push(stack1.pop());
                stack1.push(data);
                while(!stack2.isEmpty())
                    stack1.push(stack2.pop());
                break;
            case "dequeue" :
                if(stack1.isEmpty()){
                    System.out.println("Empty queue!");
                    continue;
                }
                System.out.println("Dequeued " + stack1.pop());
                break;
            default :
                quit();
            }
        }
    }

    private static void queueWithCostlyDequeue(Scanner scanner) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        System.out.print("\n"
                + "Enter operation\n"
                + "1. enqueue x : Enqueues x\n"
                + "2. dequeue : Dequeues\n"
                + "3. quit\n");
        while(true){
            String[] operation = scanner.nextLine().split(" ");
            if(operation[0] == "quit")
                quit();
            switch(operation[0]){
            case "enqueue" :
                stack1.push(Integer.parseInt(operation[1]));
                break;
            case "dequeue" :
                if(stack1.isEmpty() && stack2.isEmpty()){
                    System.out.println("Empty queue!");
                    continue;
                }
                if(stack2.isEmpty())
                    while(!stack1.isEmpty())
                        stack2.push(stack1.pop());
                System.out.println("Dequeued " + stack2.pop());
                break;
            default :
                quit();
            }
        }
    }

    private static void quit() {
        System.out.println("Quitting..");
        System.exit(0);
    }
}
