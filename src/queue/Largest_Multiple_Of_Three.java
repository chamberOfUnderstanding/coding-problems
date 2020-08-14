package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Given an array of non-negative integers.
 * Find the largest multiple of 3 that can be formed from array elements.
 * For example, if the input array is {8, 1, 9}, the output should be "9 8 1".
 * If the input array is {8, 1, 7, 6, 0}, output should be "8 7 6 0".
 * 
 * http://www.geeksforgeeks.org/find-the-largest-number-multiple-of-3/
 * 
 * ================
 * METHOD 1 : Queue
 * ================
 * The sum of digits of a number when divided by 3 gives a remainder same as the one obtained when the actual number is divided by 3
 *  
 * Sort the array        
 * Have queues for each remainder
 * Scan the numbers
 *  Update the sum
 *  Do number % 3 and add the number to the appropriate remainder queue
 * If the sum is divisible by 3, then we have the number 
 * Else if the remainder is 1, remove a remainder 1 number or two remainder 2 numbers from the queues
 * Else if the remainder is 2, remove a remainder 2 number or two remainder 1 numbers from the queues
 * Scan each remainder queue and the contents to a list
 * Sort the list
 * Since the sort was ascending, print it in reverse
 * 
 * TIME     : O(nlogn)
 * SPACE    : O(n)
 *
 * 
 */

public class Largest_Multiple_Of_Three {

    public static void main(String...x){
        int[] numbers = {8, 1, 7, 6, 0, 9};
        findLargestMultipleOfThree(numbers);
    }

    private static void findLargestMultipleOfThree(int[] numbers) {
        int size = numbers.length;
        Arrays.sort(numbers);
        int sum = 0;
        Queue<Integer> remainder0 = new LinkedList<>();
        Queue<Integer> remainder1 = new LinkedList<>();
        Queue<Integer> remainder2 = new LinkedList<>();
        for(int number : numbers){
            sum += number;
            switch(number % 3){
            case 0 : remainder0.add(number); break;
            case 1 : remainder1.add(number); break;
            case 2 : remainder2.add(number); break;
            }
        }
        boolean found = true;
        switch(sum % 3){
        case 0 : break;
        case 1 : found = removeNumbersFromQueue(remainder1, remainder2); break;
        case 2 : found = removeNumbersFromQueue(remainder2, remainder1); break;
        }
        Integer[] digits = getDigits(remainder0, remainder1, remainder2, size);
        System.out.print("Largest multiple of 3 " + (!found || digits.length == 0? "doesnt exist" : "is "));
        Arrays.sort(digits);
        for(int i = digits.length - 1; i >= 0; i--)
            System.out.print(digits[i]);
    }

    private static boolean removeNumbersFromQueue(Queue<Integer> remainder1, Queue<Integer> remainder2) {
        if(!remainder1.isEmpty()) {
            remainder1.remove();
            return true;
        }
        else if(remainder2.size() >= 2){
            remainder2.remove(); 
            remainder2.remove();
            return true;
        }
        return false;
    }

    private static Integer[] getDigits(Queue<Integer> queue0, Queue<Integer> queue1, Queue<Integer> queue2, int size) {
        List<Integer> digits = new ArrayList<>();
        emptyQueueIntoList(queue0, digits);
        emptyQueueIntoList(queue1, digits);
        emptyQueueIntoList(queue2, digits);
        Integer[] digitsArray = new Integer[digits.size()];
        return digits.toArray(digitsArray);
    }

    private static void emptyQueueIntoList(Queue<Integer> queue0, List<Integer> digits) {
        while(!queue0.isEmpty())
            digits.add(queue0.remove());
    }	
}