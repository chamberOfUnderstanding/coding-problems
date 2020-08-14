package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Given a number n. Generate all binary numbers till n.
 * 
 * http://www.geeksforgeeks.org/interesting-method-generate-binary-numbers-1-n/
 * 
 * ======================
 * METHOD 1 : Queue
 * ======================
 * Enqueue 1
 * Repeat n times : 
 *  Dequeue and print the value
 *  Append 0 to the dequeued value and enqueue it
 *  Append 1 to the dequeued value and enqueue it
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Binary_Number_Generator {

    public static void main(String...strings){
        int number = 5;
        System.out.print("\nBinary numbers till " + number + " are:\n");
        generateBinaryNumbers(number);
    }

    private static void generateBinaryNumbers(int number) {
        if(number < 0)
            return;	    
        System.out.print(0 + " ");
        if(number == 0)
            return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while(number-- > 0){
            int current = queue.remove();
            System.out.print(current + " ");
            queue.add(current * 10 + 0);
            queue.add(current * 10 + 1);
        }
    }
}
