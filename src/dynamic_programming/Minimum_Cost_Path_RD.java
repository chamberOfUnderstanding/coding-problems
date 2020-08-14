package dynamic_programming;

/**
 * @author 47un
 *
 * Given a cost matrix cost[][] and a position (m, n) in cost[][], find the minimum cost path to reach (m, n) from (0, 0).
 * Each cell of the matrix represents a cost to traverse through that cell.
 * Total cost of a path to reach (m, n) is sum of all the costs on that path (including both source and destination).
 * You can only traverse down, right and diagonally lower cells from a given cell, i.e., from a given cell (i, j), cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed.
 * You may assume that all costs are positive integers.
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Recursive
 * TIME     : O(2^n)
 * SPACE    : O(?)
 *
 * METHOD 2 : Dynamic
 * TIME     : O(mn)
 * SPACE    : O(mn)
 * 
 */
public class Minimum_Cost_Path_RD {

	public static void main(String args[])
	{
		int[][] cost =  {{1, 2, 3},
						 {4, 8, 2},
						 {1, 5, 3}};
		int[] destination = {2, 2}; 
		System.out.println("Minimum cost to reach (" + destination[0] + "," + destination[1] + ") is : ");
		System.out.println("  " + Minimum_Cost_Path_RD.findMinimumCostPathRecursive(cost, destination[0], destination[1]));
		System.out.println("  " + Minimum_Cost_Path_RD.findMinimumCostPathDynamic(cost, destination[0], destination[1]));
	}

	private static int findMinimumCostPathRecursive(int[][] cost, int row, int column) {
		if(row < 0 || column < 0)
			return Integer.MAX_VALUE;
		else if(row == 0 && column == 0)
			return cost[row][column];
		else
			return cost[row][column] + 
					minimum(findMinimumCostPathRecursive(cost, row, column - 1), 
							findMinimumCostPathRecursive(cost, row - 1, column - 1), 
							findMinimumCostPathRecursive(cost, row - 1, column));
	}

	private static int findMinimumCostPathDynamic(int[][] cost, int row, int column) {
		int[][] lookup = new int[row + 1][column + 1];
		lookup[0][0] = cost[0][0];

		for(int i = 1; i <= row; i++)
			lookup[i][0] = lookup[i-1][0] + cost[i][0];

		for(int j = 1; j <= column; j++)
			lookup[0][j] = lookup[0][j-1] + cost[0][j];

		for(int i = 1; i <= row; i++)
			for(int j = 1; j <= column; j++)
				lookup[i][j] = cost[i][j] +
				minimum(lookup[i][j - 1], 
						lookup[i - 1][j - 1], 
						lookup[i - 1][j]);
		return lookup[row][column];
	}

	private static int minimum(int first, int second, int third) {
		return Math.min(first, Math.min(second, third));
	}
}
