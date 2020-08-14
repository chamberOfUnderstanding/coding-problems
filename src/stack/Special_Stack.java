package stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author 47un
 * 
 * Design a Special Stack that supports all the stack operations :
 *  - push(), pop(), isEmpty(), isFull() and an additional operation getMin() which should return minimum element from the SpecialStack.
 * All these operations of SpecialStack must be O(1). 
 * To implement SpecialStack, you should only use standard Stack data structure and no other data structure like arrays, list, .. etc.
 * 
 * http://www.geeksforgeeks.org/design-and-implement-special-stack-data-structure/
 * 
 * ============
 * METHOD 1 : 
 * ============
 * Since you're allowed to use a Stack, tracking the minimum values is a cinch
 * All you gotta do is:
 *  During each push, check if the incoming value is less than the top of the minimum stack, yes? push it onto the minimum stack
 *  During each pop, check if the popped value is the one on minimum stack's top, yes? pop it too. Coz, since it was removed from the
 *   actual stack, it's no longer the minimum (no longer available itself)
 *   
 * If the items are entered in descending order, the auxiliary space usage can grow like anything O(n) 
 * 
 * TIME     : O()
 * SPACE    : O()
 *
 * 
 */

public class Special_Stack {

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            int[] stack   = new int[100];
            Stack<Integer> minStack = new Stack<>();
            int top = -1;
            System.out.print("\n"
                    + "Enter operation\n"
                    + "1. push x : Pushes x\n"
                    + "2. pop : Pops\n"
                    + "3. peek : Peeks\n"
                    + "4. isFull : Yes if full\n"
                    + "5. isEmpty : Yes if empty\n"
                    + "6. getMin : Returns minimum value on stack\n"
                    + "7. quit\n");
            while(true){
                String[] operation = scanner.nextLine().split(" ");
                if(operation[0] == "quit")
                    quit();
                switch(operation[0]){
                case "push" :
                    if(top == 100){
                        System.out.println("\nCapacity reached. Exiting..");
                        return;
                    }
                    int data = Integer.parseInt(operation[1]);
                    stack[++top] = data;
                    if(minStack.isEmpty() || data < minStack.peek())
                        minStack.push(data);
                    break;
                case "pop" :
                    if(top == -1){
                        System.out.println("Empty stack! Pop failed");
                        continue;
                    }
                    int popped = stack[top--];
                    if(popped == minStack.peek())
                        minStack.pop();
                    System.out.println("Popped : " + popped);
                    break;
                case "peek" :
                    if(top == -1)
                        System.out.println("Stack is empty");
                    else
                        System.out.println("Peeking : " + stack[top]);
                    break;
                case "isFull" :
                    System.out.println(top == 100? "Yes" : "No");
                    break;
                case "isEmpty" :
                    System.out.println(top == -1? "Yes" : "No");
                    break;
                case "getMin" :
                    if(top == -1)
                        System.out.println("Stack is empty");
                    else
                        System.out.println("Minimum is : " + minStack.peek());
                    break;
                default :
                    quit();
                }
            }
        }
    }

    private static void quit() {
        System.out.println("Quitting..");
        System.exit(0);
    }
}
