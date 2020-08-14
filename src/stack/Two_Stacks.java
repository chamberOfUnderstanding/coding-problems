package stack;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Create a data structure twoStacks that represents two stacks.
 * Implementation of twoStacks should use only one array, i.e., both stacks should use the same array for storing elements
 * 
 * http://www.geeksforgeeks.org/implement-two-stacks-in-an-array/
 * 
 * ===========
 * METHOD 1
 * =========== 
 * Divide the array into half.
 * Each half needs to behave like a stack
 * + Easy to do.
 * - Inefficient use of space. Stack 1 might be reported as full while stack 2 is totally empty (meaning there's space left out).
 *   
 * ===========
 * METHOD 2
 * ===========
 * Start from either ends of the array.
 * One stack has top at 0 and other has it from SIZE-1.
 * +Better space utilization than method 1.   
 * CAUTION : This bad boy has enough corner cases to leave you bleeding for a week
 * 
 * Push Operation
 *  Check for overflow on the stack that is being pushed
 *   There is overflow if the top crosses the top of the other stack
 *  Add the item at the top if there is no overflow 
 * Pop Operation
 *  Check for underflow on the stack that is being popped
 *   There is underflow if the top goes below 0 or goes above SIZE
 *  Remove the item at the top if there is no underflow
 */

public class Two_Stacks {

    static final int SIZE = 20;

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            Two_Stacks twoStacks = new Two_Stacks();
            twoStacks.operate(scanner);
        }
    }

    private void operate(Scanner scanner){
        int[] twoStacks = new int[SIZE];
        int top1 = -1;
        int top2 = SIZE;
        int stackNumber = 0;
        System.out.print("\n"
                + "Enter operation\n"
                + "1. push Y x : Pushes x onto stack Y\n"
                + "2. pop Y    : Pops stack Y\n"
                + "3. peek Y   : Peeks stack Y\n"
                + "4. quit\n"
                + "Y can be either 1 or 2\n");
        while(true){
            String[] operation = scanner.nextLine().split(" ");
            if(operation[0] == "quit")
                quit();
            stackNumber	= Integer.parseInt(operation[1]);
            switch(operation[0]){
            case "push" :
                int data       = Integer.parseInt(operation[2]);
                boolean pushed = false;
                if(stackNumber == 1 && top1 + 1 < top2) {
                    twoStacks[++top1] = data;
                    pushed = true;
                }
                else if(stackNumber == 2 && top2 - 1 > top1) {
                    twoStacks[--top2] = data;
                    pushed = true;
                }
                if(!pushed)
                    System.out.println("Overflow!");
                break;
            case "pop" :					
                boolean popped = false;
                if(stackNumber == 1 && top1 != -1) {
                    System.out.println("Popped : " + twoStacks[top1--]);
                    popped = true;
                }
                else if(stackNumber == 2 && top2 != SIZE) {
                    System.out.println("Popped : " + twoStacks[top2++]);
                    popped = true;
                }
                if(!popped)
                    System.out.println("Underflow!");
                break;
            case "peek" :					
                boolean peeked = false; 
                if(stackNumber == 1 && top1 != -1) {
                    System.out.println("Peeked : " + twoStacks[top1]);
                    peeked = true;
                }
                else if(stackNumber == 2 && top2 != SIZE) {
                    System.out.println("Peeked : " + twoStacks[top2]);
                    peeked = true;
                }
                if(!peeked)
                    System.out.println("Empty stack!");
                break;
            default : 
                quit();
            }
        }
    }

    private void quit() {
        System.out.println("Quitting..");
        System.exit(0);
    }
}
