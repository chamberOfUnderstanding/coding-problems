package stack;

import java.util.Stack;

/**
 * @author 47un
 *
 * In a party of N people, only one person is known to everyone.
 * Such a person may be present in the party, if yes, (s)he doesn’t know anyone in the party.
 * We can only ask questions like “does A know B? “.
 * Find the stranger (celebrity) in minimum number of questions.
 * We can describe the problem input as an array of numbers/characters representing persons in the party.
 * We also have a hypothetical function HaveAcquaintance(A, B) which returns true if A knows B, false otherwise.
 * The celebrity does not know anyone (Like you!)
 * All the others know the celebrity and may know each other
 * 
 * http://www.geeksforgeeks.org/the-celebrity-problem/
 * 
 * =======================
 * METHOD 1 : Stack
 * =======================
 * Iterate until there's one person left in the stack
 * Pop two people out of the stack, x, y
 *    If x knows y then y can be a celebrity, so push y back in
 *    Else x can be a celebrity, so push x back in
 * The person left behind in the stack may be a celebrity
 * Scan the input again and check if this person is the celebrity
 *    If this person knows someone or if this person is not known by someone, then return -1 (no celebrities in the mix)
 *    Else yes, he's the one
 *            
 * TIME    : O(n)
 * SPACE   : O(n)
 * 
 * ==============================
 * METHOD 2 : Scan from both ends
 * ==============================
 * Scan from both ends until they are the same person
 *    If x knows y, then y can be a celebrity and x cant be one, so advance x
 *    Else advance y
 * After the scan, verify if x is the celebrity (Same as above)
 * 
 * TIME    : O(n)
 * SPACE   : O(1)
 * 
 * The method 'knows' returns true if x knows y
 * 
 */

public class Celebrity_Problem {
    
    public static void main(String...strings){
        int[][] acquaintedTo = 
                    {{0, 0, 1, 0},
                     {0, 0, 1, 0},
                     {0, 0, 0, 0},
                     {0, 0, 1, 0}};
        System.out.println("Method I : Stack");
        System.out.println("The celebrity is: " + findCelebrity(acquaintedTo));
        System.out.println("Method II : Scan from both ends");
        System.out.println("The celebrity is: " + findCelebrityIterative(acquaintedTo));
    }

    private static Integer findCelebrity(int[][] acquaintedTo) {
        int person1 = 0;
        int person2 = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < acquaintedTo.length; i++)
            stack.push(i);
        while(stack.size() > 1){
            person1 = stack.pop();
            person2 = stack.pop();
            stack.push((knows(person1, person2, acquaintedTo) ? person2 : person1));
        }
        int possibleCelebrity = stack.pop();
        for(int i = 0 ; i < acquaintedTo.length; i++)
            if(i != possibleCelebrity && 
                (knows(possibleCelebrity, i, acquaintedTo) || 
                        !knows(i, possibleCelebrity, acquaintedTo)))
                return -1;
        return possibleCelebrity + 1;
    }

    private static Integer findCelebrityIterative(int[][] acquaintedTo) {
        int person1 = 0;
        int person2 = acquaintedTo.length - 1;
        while(person1 < person2)
            if(knows(person1, person2, acquaintedTo))
                person1++;
            else
                person2--;
        for(int i = 0; i < acquaintedTo.length; i++)
            if(i != person1 &&
                (knows(person1,i,acquaintedTo) ||
                        !knows(i,person1,acquaintedTo)))
                return -1;
        return person1 + 1;
    }

    private static boolean knows(int person1, int person2, int[][] acquaintedTo){
        return acquaintedTo[person1][person2] == 1;
    }
}