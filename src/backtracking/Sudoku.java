package backtracking;

/**
 * @author 47un
 *
 * Solve the sudoku
 * 
 * http://www.geeksforgeeks.org/backtracking-set-7-suduku/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Find an empty cell
 *            Iterate and place numbers (starting from 1 to 9), check if it is safe to place that number in the cell
 *            If yes, place it and solve the rest
 *            If there is no solution, backtrack by resetting this cell to 0
 *
 * TIME     : O(n^(n^2))    // 9 ^ 81 (As there are 81 cells and each can take 9 values) 
 * SPACE    : O(1)
 *
 * 
 */

public class Sudoku {
    public static void main(String[] args) {
        int[][] sudoku = 
               {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
        if(solve(sudoku))
            printBoard(8, sudoku);
        else
            System.out.println("Nope!");
    }
    
    private static boolean solve(int[][] sudoku) {
        int[] cell = findEmptyCell(sudoku);
        if(cell[0] == -1 && cell[1] == -1)
            return true;
        for(int i = 1; i <= 9; i++) {
            if(isItSafe(i, cell[0], cell[1], sudoku)) {
                sudoku[cell[0]][cell[1]] = i;
                if(solve(sudoku))
                    return true;
                sudoku[cell[0]][cell[1]] = 0;
            }                       
        }
        return false;
    }

    private static int[] findEmptyCell(int[][] sudoku) {
        int[] cell = new int[2];
        cell[0] = -1;
        cell[1] = -1;
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(sudoku[i][j] == 0) {
                    cell[0] = i;
                    cell[1] = j;
                    break;
                }
        return cell;                    
    }

    private static boolean isItSafe(int number, int row, int column, int[][] sudoku) {
        return isThereRowCollision(number, row, column, sudoku) && 
                 isThereColumnCollision(number, row, column, sudoku) && 
                    isThereGridCollision(number, row, column, sudoku);
    }

    private static boolean isThereRowCollision(int number, int row, int column, int[][] sudoku) {
        for(int i = 0; i < 9; i++)
            if(sudoku[row][i] == number)
                return false;
        return true;
    }

    private static boolean isThereColumnCollision(int number, int row, int column, int[][] sudoku) {
        for(int i = 0; i < 9; i++)
            if(sudoku[i][column] == number)
                return false;
        return true;
    }

    private static boolean isThereGridCollision(int number, int row, int column, int[][] sudoku) {
        int gridRow = row - row % 3;
        int gridCol = column - column % 3;
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(sudoku[gridRow + i][gridCol + j] == number)
                    return false;
        return true;
    }

    private static void printBoard(int rows, int[][] board) {
        for(int[] row : board) {
            for(int cell : row)
                System.out.format("%2s ", cell);
            System.out.println();
        }   
    }
}
