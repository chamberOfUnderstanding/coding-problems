package stack;

import java.util.Scanner;

/**
 * @author 47un
 * 
 * Design a Special Stack that supports all the stack operations : push(), pop(), isEmpty(), isFull() and an additional operation getMin() which should return minimum element from the SpecialStack.
 * All these operations of SpecialStack must be O(1). No other data structures can be used.
 * 
 * http://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/
 * 
 * ============
 * METHOD 1 : 
 * ============
 * 
 * Push(x) : Inserts x at the top of stack.
 *   <> If stack is empty, insert x into the stack and make minimumElement equal to x.
 *   <> If stack is not empty, compare x with minimumElement. Two cases arise:
 *   <> If x is greater than or equal to minimumElement, simply insert x.
 *   <> If x is less than minimumElement, insert (2*x – minimumElement) into the stack and make minimumElement equal to x. For example, let previous minimumElement was 3. Now we want to insert 2. We update minimumElement as 2 and insert 2*2 – 3 = 1 into the stack.
 *   
 * During push, if the data to be inserted is < minimumElement, we generate a fake data y using x and the current minimum element,
 *  and insert this fake data into the stack.
 * The generated fake data is such that it is always less than the minimum element or the 'x' (the real data) that was
 *  inserted. The faker is generated using 2*x - minimumElement
 * Now x becomes the minimumElement
 * 
 * So
 *  x < minimumElement
 *  x - minimumElement < 0 // Adding x on both sides
 *  2x - minimumElement < x
 *  y < x
 *
 * Pop() : Removes an element from top of stack.
 *   <> Remove element from top. Let the removed element be y. Two cases arise:
 *   <> If y is greater than or equal to minimumElement, the minimum element in the stack is still minimumElement.
 *   <> If y is less than minimumElement, the minimum element now becomes (2*minimumElement – y), so update (minimumElement = 2*minimumElement – y). This is where we retrieve previous minimum from current minimum and its value in stack. For example, let the element to be removed be 1 and minimumElement be 2. We remove 1 and update minimumElement as 2*2 – 1 = 3.
 * 
 * During pop, if the popped element (y) is less than the minimum element, then the popped data is the fake one we generated.
 * The actual data to be returned at this point is the minimum element. Do that, then restore the previous minimum element.
 * The formula used to find the fake data y is 2*x - minimumElement and after y is calculated, minimumElement is updated to x
 * 
 *  So,
 *   y = 2x - oldMinimumElement &  newMinimumElement = x // Done during push of x
 *        or 
 *   oldMinimumElement = 2x - y
 *   But newMinimumElement = x
 *    => oldMinimumElement = 2newMinimumElement - y        
 * 
 * Important Points:
 *   <> Stack doesn’t hold actual value of an element if it is minimum so far.
 *   <> Actual minimum element is always stored in minimumElement
 *
 * TIME     : O(1)
 * SPACE    : O(1)
 *
 */

public class Special_Stackv2 {

    public static void main(String...strings){
        try(Scanner scanner = new Scanner(System.in)){
            int[] stackUsingArray = new int[100];
            int top = -1;
            int minimumElement = Integer.MAX_VALUE;
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
                    if(top == -1){
                        stackUsingArray[++top] = data;
                        minimumElement = data;
                    }
                    else if(data < minimumElement){
                        stackUsingArray[++top] = 2 * data - minimumElement;
                        minimumElement = data;
                    }
                    else
                        stackUsingArray[++top] = data;
                    break;
                case "pop" :
                    if(top == -1){
                        System.out.println("Empty stacks! Pop failed");
                        continue;
                    }
                    int popped = stackUsingArray[top--];
                    if(popped < minimumElement){
                        System.out.println("Popped : " + minimumElement);
                        minimumElement = 2 * minimumElement - popped;
                    }
                    else
                        System.out.println("Popped : " + popped);
                    break;
                case "peek" :
                    if(top == -1)
                        System.out.println("Stack is empty");
                    else
                        System.out.println("Peeking : " + stackUsingArray[top]);
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
                        System.out.println("Minimum is : " + minimumElement);
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