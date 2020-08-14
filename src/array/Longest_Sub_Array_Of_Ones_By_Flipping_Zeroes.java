package array;

/**
 * @author 47un
 *
 * Given a stream of ones and zeroes and a number k.
 * Find out the longest streak of ones obtainable by flipping k zeroes to 1 * 
 * e.g. : 110110011101111
 * if k = 1 => Flipping the 0 at index 11 will give a streak of 8 ones
 * if k = 3 => Flipping the last 3 zeroes gives you a streak of 12 ones 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan the stream
 *            Since we can flip k zeroes and if the window has <= k zeroes, increase zeroes in window count and stretch the window
 *            If there are more than k zeroes, and the first index in the window is a 0, shrink the window to exclude this index
 *            If there are <= k zeroes in the window and the window size is better than the maximum, update maximum
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Longest_Sub_Array_Of_Ones_By_Flipping_Zeroes {
    
    public static void main(String...strings){
        int size = 15;
        int k    = 4;
        int[] stream = {1,1,0,1,1,0,0,1,1,1,0,1,1,1,1};		
        getStreak(stream, size, k);
    }

    private static void getStreak(int[] stream, int size, int k) {
        int windowStart     = 0;
        int windowEnd       = 0;
        int bestWindowStart = 0;
        int bestWindow      = Integer.MIN_VALUE;
        int zeroesInWindow  = 0;
        while(windowEnd != size){
            if(zeroesInWindow <= k){
                if(stream[windowEnd] == 0)
                    zeroesInWindow++;
                windowEnd++;
            }
            else if(zeroesInWindow > k){
                if(stream[windowStart] == 0)
                    zeroesInWindow--;
                windowStart++;
            }
            if(zeroesInWindow <= k && windowEnd - windowStart > bestWindow){
                bestWindowStart = windowStart;
                bestWindow      = windowEnd - windowStart;
            }
        }
        System.out.print("\nFlip the zeroes at indices :");
        for(int i = bestWindowStart; i < windowEnd; i++)
            if(stream[i] == 0)
                System.out.print(i + " ");
        System.out.println("to get a maximum streak of " + bestWindow);
    }
}
