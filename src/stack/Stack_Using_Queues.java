package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 47un
 * 
 * Implement a stack using two queues
 *  
 * http://www.geeksforgeeks.org/implement-stack-using-queue/
 * 
 * =======================
 * METHOD 1 : Costly Push
 * ======================
 * Enqueue item onto queue2
 * Empty queue1 onto queue2 (now the queue's contents look like that of a stack)
 * Swap references (to make sure the insertion always happens in the empty queue)
 * 
 * TIME     : O(n), O(1)
 * SPACE    : O(1)
 *
 * ======================
 * METHOD 2 : Costly Pop
 * =====================
 * Enqueue items onto queue1
 * During a pop, copy all but the last element of queue1 onto queue2
 * Swap references of the queues
 * 
 * TIME     : O(1), O(n)
 * SPACE    : O(1)
 * 
 */

public class Stack_Using_Queues {

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("\n"
                    + "Pick a stack : \n"
                    + "1. Costly push\n"
                    + "2. Costly pop");
            int stack = Integer.parseInt(scanner.nextLine());
            if(stack == 1)
                stackWithCostlyPush(scanner);
            else
                stackWithCostlyPop(scanner);
        }
    }

    private static void stackWithCostlyPush(Scanner scanner) {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
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
                queue2.add(Integer.parseInt(operation[1]));
                while(!queue1.isEmpty())
                    queue2.add(queue1.remove());
                Queue<Integer> renamer;
                renamer = queue2;
                queue2  = queue1;
                queue1  = renamer;			
                break;
            case "pop" :
                if(queue1.isEmpty()){
                    System.out.println("Empty stack!");
                    continue;
                }				
                System.out.println("Popped " + queue1.remove());				
                break;
            default :
                quit();
            }
        }
    }

    private static void stackWithCostlyPop(Scanner scanner) {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();		
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
                queue2.add(Integer.parseInt(operation[1]));
                break;
            case "pop" :
                if(queue2.isEmpty()){
                    System.out.println("Empty stack!");
                    continue;
                }
                while(!queue2.isEmpty() && queue2.size() != 1)
                    queue1.add(queue2.remove());
                System.out.println("Popped " + queue2.remove());
                Queue<Integer> renamer;
                renamer = queue2;
                queue2 = queue1;
                queue1 = renamer;
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
