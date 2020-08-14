package math;

/**
 * 
 * @author 47un
 * 
 * In the Josephus problem from antiquity, n people are in dire straits and agree to the following strategy to reduce the population.
 * They arrange themselves in a circle (at positions numbered from 0 to n - 1) & proceed around the circle, eliminating every mth person until only one person is left.
 * Legend has it that Josephus figured out where to sit to avoid being eliminated.
 * Given m and n, calculate the order of death and where Mistah J has to sit
 * 
 * http://www.geeksforgeeks.org/josephus-problem-set-1-a-on-solution/
 * 
 * ========
 * METHOD 1
 * ========
 * http://stackoverflow.com/questions/31775604/explanation-for-recursive-implementation-of-josephus-prob
 * 
 * Recursive solution is of the form : (k + josephus(n - 1, k) - 1) % n + 1
 * 
 * Right after killing the person k, who is indexed at k - 1
 *  The problem reduces to doing the same thing with n - 1 people but while starting from index k 
 *   (Remember that k - 1 was just killed)
 *  This can be done by calling josephus(n - 1, k). The solution to this will be 1 indexed. We need 0 index, so subtract a 1
 *  This can go out of bounds, so % n gives a wrap around
 * 
 * Finally the answer is obtained by adding 1 to the 0 index
 * 
 * TIME  : O(n)
 * SPACE : O(1)
 * 
 * To make things more clear, it's better to have a wrapper that adds 1 to the 0 index based solution.
 * The solution for the 0 index is => (k + josephus(n - 1, k)) % n 
 * 
 */

public class Josephus_Problem {

    public static void main(String...strings){
        int people = 100;
        int eliminator = 2;
        System.out.print("The safe spot is : ");
        System.out.println(genocideWrapper(people, eliminator));
    }

    private static int genocideWrapper(int n, int k) {
        return 1 + genocide(n, k);
    }

    private static int genocide(int n, int k) {
        return n == 0? 0 : (k + genocide(n - 1, k)) % n;
    }
}
