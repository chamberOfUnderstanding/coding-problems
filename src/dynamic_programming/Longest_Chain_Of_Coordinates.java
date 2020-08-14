package dynamic_programming;

import java.util.Arrays;

/**
 * @author 47un
 * 
 * You are given N pairs of numbers.
 * In every pair, the first number is always smaller than the second number.
 * A pair (c, d) can follow another pair (a, b) if b < c.
 * Chain of pairs can be formed in this fashion. 
 * Find the longest chain which can be formed from a given set of pairs. 
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-20-maximum-length-chain-of-pairs/
 * 
 * METHOD 1 : DP solution similar to LIS 
 *
 * TIME     : O(n^2)
 * SPACE    : O(n)
 *
 * 
 */

public class Longest_Chain_Of_Coordinates {

    public static void main(String[] args) {
        //int[][] coordinates = {{1,2},{3,4},{-1,4},{6,7},{99,3},{4,5},{10,11},{22,43},{1,5}};
        int[][] coordinates = { {5, 24}, {15, 25},  {27, 40}, {50, 60} };
        getLIS(coordinates);
    }

    private static void getLIS(int[][] coordinates) {
        int[] lis = new int[coordinates.length];
        int[] parent = new int[coordinates.length];
        Arrays.fill(lis, 1);
        Arrays.fill(parent, -1);
        for(int i = 0; i < coordinates.length; i++)
            for(int j = i + 1; j < coordinates.length; j++)
                if(coordinates[j][0] > coordinates[i][1]) {
                    lis[j] = Math.max(lis[j], lis[i] + 1);
                    parent[j] = i;
                }
        int largest = lis[0];
        int parentOfLargest = parent[0];
        for(int i = 1; i < lis.length; i++) {
            if(lis[i] > largest) {
                largest = lis[i];
                parentOfLargest = i;
            }
        }
        System.out.println("LIS " + largest);
        while(parentOfLargest != -1) {
            System.out.print("{" + coordinates[parentOfLargest][0] + "," + coordinates[parentOfLargest][1] + "} ");
            parentOfLargest = parent[parentOfLargest];
        }
    }
}
