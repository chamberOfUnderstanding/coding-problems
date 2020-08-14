package stack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 47un
 * 
 * Implement k stacks using a single array
 * 
 * http://www.geeksforgeeks.org/efficiently-implement-k-stacks-single-array/
 * 
 * =========
 * METHOD 1 
 * =========
 * An array of size k tracks the tops of all k stacks
 * An array of size n tracks the next item in the array. Initially every index points to the next index, except the last which holds -1 
 *  
 * Push
 *  Get the current free slot
 *  Update the free slot to the next one
 *  Next free slot gets the current stack top
 *  Update top of the stack to the newly added element
 *  Place the data at the current free slot (this operation makes it no longer free)
 *
 * Pop
 *  Get the top
 *  Next item on the stack becomes the new top
 *  The next slot of soon to be free is now free and updated to the current free slot
 *  Update the free slot to the slot from where the item was just removed 
 * 
 * TIME     : O(1)  // Push and pop
 * SPACE    : O(n)
 *
 */

public class K_Stacks {

    static final int SIZE = 100;
    int[] array = new int[SIZE];

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            K_Stacks kStacks = new K_Stacks();
            kStacks.operate(scanner);
        }
    }

    private void operate(Scanner scanner) {
        System.out.print("\nEnter number of stacks ");
        int k = Integer.parseInt(scanner.nextLine());
        int[] top = new int[k];
        Arrays.fill(top, -1);
        int[] next = new int[SIZE];
        for(int i = 0; i < SIZE; i++)
            next[i] = i + 1;
        next[SIZE - 1] = -1;
        int free = 0;
        int stackNumber = 0;
        System.out.print("\n"
                + "Enter operation\n"
                + "1. push Y x : Pushes x onto stack Y\n"
                + "2. pop Y    : Pops stack Y\n"
                + "3. peek Y   : Peeks stack Y\n"
                + "4. quit\n"
                + "Y can be a value between 1 and " + SIZE);
        while(true){
            String[] operation = scanner.nextLine().split(" ");
            if(operation[0] == "quit")
                quit();
            stackNumber = Integer.parseInt(operation[1]) - 1;
            switch(operation[0]){
            case "push" :
                int data = Integer.parseInt(operation[2]);
                if(free == -1){
                    System.out.println("Overflow!");
                    continue;
                }
                int currentFreeSlot = free;
                free = next[currentFreeSlot];
                next[currentFreeSlot] = top[stackNumber];
                top[stackNumber]      = currentFreeSlot;
                array[currentFreeSlot] = data;
                break;
            case "pop" :					
                if(top[stackNumber] == -1) {
                    System.out.println("Underflow!");
                    continue;
                }
                int soonToBeFreeSlot = top[stackNumber];
                top[stackNumber]     = next[soonToBeFreeSlot];
                next[soonToBeFreeSlot] = free;
                free = soonToBeFreeSlot;
                System.out.println("Popped "+ array[soonToBeFreeSlot]);
                break;
            case "peek" :					
                if(top[stackNumber] == -1) {
                    System.out.println("Empty stack");
                    continue;
                }
                System.out.println("Peeked : " + array[top[stackNumber]]);
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
