package dynamic_programming;

/**
 * 
 * @author 47un
 * 
 *         Given an MxN maze with 0 and -1. Starting from index 0,0 count all
 *         possible paths to index m,n. From each cell you can move to the next
 *         right/bottom cell, if that cell is NOT a -1. 
 * 
 *
 *         https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 *         
 *         RECURSIVE
 *         *********
 *         Reached bottom right? There's a path! return 1
 *         Current spot is a wall? No more path ahead, return 0
 *          * If there is a next column (right move), move right and calculate all paths to the destination from the new spot
 *          * If there is a next row (down move), move down and calculate all paths to the destination from the new spot
 *         Sum the above two to get the total paths, return it
 *         
 *         TIME  : (m * n) ^ 2 (From each cell, two possibilities arise) 
 *         SPACE : (m * n) ^ 2 (?)
 *         
 *         DP
 *         ***
 *         Scan the first row and update the lookup to 1 UNTIL A WALL IS SEEN (Since there's no path ahead, no need to scan further)
 *         Scan the first column and update the lookup to 1 UNTIL A WALL IS SEEN
 *         Generate the lookups for the rest of indices :
 *          * i,j can be reached from i-1,j and i, j-1. So the lookup of i,j is the sum of number of paths from those two spots,
 *            IFF i-1,j and i,j-1 ARE NOT WALLS. If they were walls, they cant contribute to the path
 *         Result is the last value in the lookups, return it
 *         
 *         TIME  : m * n 
 *         SPACE : m * n
 *         
 */

public class Maximum_Possible_Paths_In_A_Maze {

	public static void main(String[] args) {
		int totalRows = 10;
		int totalColumns = 5;
		int[][] maze = { 
				{ 0, 0, 0, 0, 0}, 
				{ 0, 0, 0, 0, 0}, 
				{ 0, 0, 0, 0, -1}, 
				{ 0, -1, 0, 0, 0},
				{ 0, 0, 0, 0, 0}, 
				{ 0, 0, 0, 0, 0}, 
				{ 0, 0, -1, 0, 0}, 
				{ 0, 0, 0, 0, 0},
				{ 0, -1, 0, 0, 0}, 
				{ 0, 0, 0, -1, 0} };
		System.out.println("Recursion " + numberOfPaths_Recursive(0, 0, totalRows, totalColumns, maze));
		System.out.println("DP " + numberOfPaths_DynamicProgramming(maze, totalRows, totalColumns));
	}
	

	private static int numberOfPaths_Recursive(int i, int j, int totalRows, int totalColumns, int[][] maze) {
		if (i == totalRows - 1 && j == totalColumns - 1)
			return 1;
		if (maze[i][j] == -1)
			return 0;
		return (j + 1 < totalColumns ? numberOfPaths_Recursive(i, j + 1, totalRows, totalColumns, maze) : 0) +
			   (i + 1 < totalRows ? numberOfPaths_Recursive(i + 1, j, totalRows, totalColumns, maze) : 0);
	}

	private static int numberOfPaths_DynamicProgramming(int[][] maze, int totalRows, int totalColumns) {
		int[][] lookup = new int[totalRows][totalColumns];
		lookup[0][0] = 0;
		for (int i = 0; i < totalColumns; i++)
			if(maze[0][i] == -1)
				break;
			else
				lookup[0][i] = 1;
		for (int j = 0; j < totalRows; j++)
			if(maze[j][0] == -1)
				break;
			else
				lookup[j][0] = 1;
		for (int i = 1; i < totalRows; i++)
			for (int j = 1; j < totalColumns; j++)
				lookup[i][j] = 
				(maze[i][j - 1] == 0 ? lookup[i][j - 1] : 0) + 
				(maze[i - 1][j] == 0 ? lookup[i - 1][j] : 0);
		return lookup[totalRows - 1][totalColumns - 1];
	}

	@SuppressWarnings("unused")
	private static void displayMaze(int[][] maze, int totalRows, int totalColumns) {
		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalColumns; j++)
				System.out.print(maze[i][j] + " ");
			System.out.println();
		}
	}
}
