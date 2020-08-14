package queue;

import java.util.Scanner;

/**
 * @author 47un
 * 
 * Implement a circular queue using an array
 * 
 * Enqueue
 *  If rear + 1 = front or front = 0 and rear = SIZE, there is overflow
 *  If rear = SIZE, wrap around and set rear as 0
 *  Insert item at rear
 * 
 * Dequeue
 *  If rear = front, there is underflow, cant dequeue
 *  If front = SIZE, wrap around and set it as 0
 *  Delete the item at front
 * 
 * TIME     : O(1)
 * SPACE    : O(1)
 *
 * 
 */

public class Circular_Queue {

    public static final int SIZE = 5;

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            int[] circularQueue = new int[SIZE];
            int front = 0;
            int rear  = 0;
            System.out.print(""
                    + "\nEnter operation"
                    + "\n1. n x : Enqueues x"
                    + "\n2. d : Dequeues"
                    + "\n5. q : Quit"
                    + "\n");
            while(true){
                String operation = scanner.nextLine();
                if(operation.charAt(0) == 'q')
                    return;
                switch(operation.charAt(0)){
                case 'n' :
                    if(overflow(front, rear)){
                        System.out.println("Capacity reached. Dequeue some items!");
                        continue;
                    }
                    int data = Integer.parseInt(operation.substring(2));
                    rear = wrapAround(rear);
                    circularQueue[rear++] = data;
                    break;
                case 'd' :
                    if(underFlow(front, rear)){
                        System.out.println("Queue is empty");
                        continue;
                    }
                    front = wrapAround(front);
                    System.out.println("Dequeued : " + " " + circularQueue[front++]);
                    break;
                }
            }
        }
    }

    private static int wrapAround(int index) {        
        return index == SIZE? 0 : index;
    }

    private static boolean overflow(int front, int rear) {
        return (rear + 1 == front) || (front == 0 && rear == SIZE);
    }

    private static boolean underFlow(int front, int rear) {
        return rear == front;
    }
}
