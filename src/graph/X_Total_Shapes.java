package graph;

/**
 * @author 47un
 * 
 * Given N * M string array of O's and X's.
 * Find the number of 'X' total shapes. 'X' shape consists of one or more adjacent X's (DIAGONALS NOT INCLUDED).
 * 
 *  OOOXOOO
 *  OXXXXXO
 *  OXOOOXO
 * 
 * Number of shapes = 1
 * 
 *      X
 *  X X X X
 *  X     X
 *  
 * =========
 * METHOD 1
 * =========
 * Same as Island_Visitor.
 * Tweak dx[] and dy[] to ignore diagonal scans
 * 
 * TIME     : O(n * m)
 * SPACE    : O(n * m)
 *
 * 
 */

public class X_Total_Shapes {

    public static void main(String[] args) {
        int rows    = 3;
        int columns = 3;
        char[][] grid = 
            {{'x','x','x'},
             {'x','o','o'},
             {'x','x','x'}};        
        System.out.println(findTotalShapes(grid, rows, columns));
    }

    private static int findTotalShapes(char[][] grid, int rows, int columns) {
        int totalShapes     = 0;
        boolean[][] visited = new boolean[rows][columns];
        
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                if(grid[i][j] == 'x' && !visited[i][j]) {
                    visit(i, j, grid, rows, columns, visited);
                    totalShapes++;
                }
        return totalShapes;
    }

    private static void visit(int r, int c, char[][] grid, int rows, int columns, boolean[][] visited) {
        int[] dx = { -1,  0,  0,  1};
        int[] dy = {  0, -1,  1,  0};
        visited[r][c] = true;
        
        for(int i = 0; i < 4; i++)
            if(isSafeToVisit(r + dx[i], c + dy[i], grid, rows, columns, visited))
                visit(r + dx[i], c + dy[i], grid, rows, columns, visited);
    }

    private static boolean isSafeToVisit(int r, int c, char[][] grid, int rows, int columns,
            boolean[][] visited) {
        return r > -1 && r < rows && c > -1 && c < columns && !visited[r][c] && grid[r][c] == 'x';
    }
}
