package graph;

/**
 * @author 47un
 *
 * Given pieces of land (1) surrounded by water (0). Find the number of islands, the largest island and the smallest island
 * 
 * Similar to finding out the number of connected components
 * 
 * http://www.geeksforgeeks.org/find-number-of-islands/
 * 
 * =========
 * METHOD 1
 * ===========
 * BFS or DFS can also be used 
 * Scan the data
 *  If a 1 is seen, check if any of its 8 neighbors can be visited as well, if so visit them and mark all of as them visited
 *  Also for each 1 that's visited, increase the size of the island
 *    Increase island count
 *    Update largest island and smallest island
 *                  
 * TIME     : O(rows * columns)
 * SPACE    : O(rows * columns) // visited array
 * 
 */

public class Island_Visitor {
    
    public static void main(String[] args) {
        int rows     = 5;
        int columns  = 5;
        int[][] grid = {{1, 0, 1, 0, 1},
                        {1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 0},
                        {1, 0, 0, 0, 1},
                        {0, 1, 0, 1, 0}};
        discover(grid, rows, columns);
    }

    private static void discover(int[][] grid, int rows, int columns) {
        int islandCount    = 0;
        int size           = 0;
        int largestIsland  = Integer.MIN_VALUE;
        int smallestIsland = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[rows][columns];
        
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                if(grid[i][j] == 1 && !visited[i][j]) {
                    size = visit(i, j, grid, rows, columns, visited);
                    islandCount++;
                    largestIsland  = Math.max(largestIsland, size);
                    smallestIsland = Math.min(smallestIsland, size);
                }
        
        System.out.println("Island count : " + islandCount + "\nLargest island : " + largestIsland + "\nSmallest island : " + smallestIsland);
    }

    private static int visit(int row, int column, int[][] grid, int rows, int columns, boolean[][] visited) {
        int size  = 1;
        int[] dx  = {-1,-1,-1, 0, 0, 1, 1, 1};
        int[] dy  = {-1, 0, 1,-1, 1,-1, 0, 1};
        visited[row][column] = true;
        
        for(int i = 0; i < 8; i++)
            if(isItSafeToVisit(row + dx[i], column + dy[i], grid, rows, columns, visited))                
                size += visit(row + dx[i], column + dy[i], grid, rows, columns, visited);
        
        return size;
    }

    private static boolean isItSafeToVisit(int row, int column, int[][] grid, int maxRows, int maxColumns, boolean[][] visited) {
       return row > -1 && column > -1 && row < maxRows && column < maxColumns && !visited[row][column] && grid[row][column] == 1;
    }    
}
