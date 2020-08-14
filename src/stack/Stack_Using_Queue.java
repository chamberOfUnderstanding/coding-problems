package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 47un
 * 
 * Implement a stack using a queue
 * 
 * http://www.geeksforgeeks.org/implement-a-stack-using-single-queue/
 * 
 * =========
 * METHOD 1 
 * =========
 * 
 * Push : Enqueue
 * Pop  : If the queue is not empty/of size = 1, dequeue size-1 items from queue and enqueue them
 *        Dequeue an item and return it
 * 
 * TIME     : O(1), O(n)
 * SPACE    : O(1)
 * 
 */

public class Stack_Using_Queue {

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            stackUsingQueue(scanner);
        }
    }

    private static void stackUsingQueue(Scanner scanner) {
        Queue<Integer> queue = new LinkedList<>();
        System.out.print("\n"
                + "Enter operation\n"
                + "1. push x : Pushes x\n"
                + "2. pop : Pops\n"
                + "3. quit\n");
        while(true){
            String[] operation = scanner.nextLine().split(" ");
            if(operation[0] == "quit")
                quit();
            switch(operation[0]){
            case "push" :
                queue.add(Integer.parseInt(operation[1]));
                break;
            case "pop" :
                if(queue.isEmpty()){
                    System.out.println("Empty stack!");
                    continue;
                }
                else if(queue.size() != 1){
                    int items = queue.size();
                    while(items-- > 1)
                        queue.add(queue.remove());
                }
                System.out.println("Popped " + queue.remove());
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
