package dynamic_programming;

/**
 * @author 47un
 *
 * A mapping of alphabets to numbers is as follows:
 * 1 = A
 * 2 = B
 * 3 = C
 * ...
 * 26 = Z
 * 
 * Given a sequence of numbers, return the number of combinations in which the input numbers can be decoded to strings. 
 * e.g.
 * "111" -> "AAA", "AK", "KA" -> 3
 * Valid combinations are ({1,1,1}, {1,11},{11,1}) = 3
 * 
 * "11" -> "AA", "K" -> 2
 * Valid combinations are ({1,1},{11}) = 2
 * 
 * "123" -> "ABC", "LC", "AW" -> 3
 * Valid combinations are ({1,2,3},{1,23},{12,3}) = 3
 * 
 * http://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
 * https://www.careercup.com/question?id=5705619461898240
 * 
 * =========
 * METHOD 1 : DP
 * ===========
 * Maintain a 'combinations' array.
 *  The value stored in 'combinations' array at any index 'i' is the total number of combinations possible for the numbers till 'i'
 * For the first number, there's only 1 possible combination.
 *  i.e. just like that, as it has no number in front, it cannot be combined with anything
 *  If this number happens to be 0, then it has 0 combinations as there's no mapping for 0
 * For the second number,
 *  Depending on whether it can form a valid number (i.e. has an alphabet mapping) when combined with its previous number there can be 2 combinations or 1 combination
 * For the rest of the numbers,
 *  By default the number of combinations = number of combinations till this character (combinations[i-1])
 *   This is because only if we are able to combine i with i - 1, the number combinations for i increases, else it is just the same as combinations[i - 1]
 *  Check if i can be combined with i - 1
 *   If so the combinations increase by combinations[i - 2], i.e number of combinations till the character right before this combination
 *   Else the number of combinations for i remains same as combinations[i - 1]  
 *               
 * For all numbers after the 2nd index, there is already combinations[i-1] combinations and just gotta check how many more can be added to it
 * If i can be combined with the character i - 1, then this combination adds to the number of combinations seen till i - 2!
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Alphabet_To_Number_Mapping {

    public static void main(String...strings){				
        int[] numbers = {1, 1, 1, 2, 3, 4};
        System.out.println(findNumberOfStrings(numbers));
    }

    private static int findNumberOfStrings(int[] numbers) {
        int[] combinations = new int[numbers.length];
        combinations[0]    = numbers[0] != 0? 1 : 0;
        combinations[1]    = (numbers[0] * 10 + numbers[1] < 27)? 2 : 1;
        for(int i = 2; i < numbers.length; i++){
            combinations[i] = combinations[i - 1];
            combinations[i] += (numbers[i - 1] != 0 && numbers[i - 1] * 10 + numbers[i] < 27)? combinations[i - 2] : 0;
        }
        return combinations[combinations.length-1];
    }
}
