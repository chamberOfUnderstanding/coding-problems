package backtracking;

import java.util.Arrays;

/**
 * @author 47un
 *
 * The N Queen is the problem of placing N chess queens on an NxN chess board so that no two queens attack each other
 * Check if there is a solution to this problem for a given value of n
 * 
 * http://www.geeksforgeeks.org/backtracking-set-3-n-queen-problem/
 * 
 * =========
 * METHOD 1
 * ===========
 * For each column
 * Scan each row and check if it is safe to place a queen there
 *   Since we are going column wise, check if there's a queen on the left side of this column, on the selected row
 *   Also check whether the upper left diagonal (ends at i,j) and lower left diagonal (starts at (i,j)) is posing a threat
 *      If it is safe, mark this row and column with a queen
 *        Recurse and verify if this move leads to a solution
 *          No? Backtrack by removing the queen that was placed here  
 *
 * TIME     : O(n ^ n)
 * SPACE    : O(n ^ 2)
 *
 * ===========
 * METHOD 2
 * ===========
 * Here the solution is a 1D array.
 * Each index, i, of the result holds a value j, which means => Place the a queen in the jth spot of the ith row
 * It also generates all possible permutations
 *  
 * http://introcs.cs.princeton.edu/java/23recursion/Queens.java.html
 * 
 */

@SuppressWarnings("unused")
public class N_Queens {

    public static void main(String[] args) {
        int queens = 4;
        placeTheQueensI(queens);
//      placeTheQueensII(queens);
    }

    private static void placeTheQueensI(int queens) {
        int[] row = new int[queens];
        placeTheQueensI(row, 0);
    }

    private static void placeTheQueensI(int[] row, int column) {
        if(column == row.length)
            printBoard(row);
        else {
            for(int i = 0; i < row.length; i++) {
                row[column] =  i;
                if(isConsistent(row, column))
                    placeTheQueensI(row, column + 1);
            }
        }
    }

    private static boolean isConsistent(int[] row, int column) {
        for(int i = 0; i < column; i++)
            if(row[i] == row[column] || Math.abs(row[i] - row[column]) == column - i)
                return false;
        return true;
    }

    private static void printBoard(int[] row) {
        for(int i = 0; i < row.length; i++) {
            for(int j = 0; j < row.length; j++)
                System.out.print(row[i] == j? "Q " : "- ");
            System.out.println();
        }
        System.out.println();
    }

    private static void placeTheQueensII(int queens) {
        char[][] chessBoard = new char[queens][queens];
        for(char[] row : chessBoard)
            Arrays.fill(row, '-');
        if(placeTheQueensII(0, queens, chessBoard))
            printBoard(queens, chessBoard);
        else
            System.out.println("Nope!");
    }

    private static boolean placeTheQueensII(int column, int queens, char[][] chessBoard) {
        if(column >= queens)
            return true;
        for(int row = 0; row < queens; row++)
            if(isItSafe(row, column, queens, chessBoard)) {
                chessBoard[row][column] = 'Q';
                if(placeTheQueensII(column + 1, queens, chessBoard))
                    return true;
                chessBoard[row][column] = '-';
            }
        return false;
    }

    private static boolean isItSafe(int row, int column, int queens, char[][] chessBoard) {
        return isRowSafe(row, column, chessBoard) && isDiagonalSafe(row, column, queens, chessBoard);
    }

    private static boolean isRowSafe(int row, int column, char[][] chessBoard) {
        for(int i = 0; i < column; i++)
            if(chessBoard[row][i] == 'Q')
                return false;
        return true;
    }

    private static boolean isDiagonalSafe(int row, int column, int queens, char[][] chessBoard) {
        for(int i = row, j = column; i >= 0 && j >= 0; i--, j--)
            if(chessBoard[i][j] == 'Q')
                return false;
        for(int i = row, j = column; i < queens && j >= 0; i++, j--)
            if(chessBoard[i][j] == 'Q')
                return false;
        return true;
    }

    private static void printBoard(int queens, char[][] chessBoard) {       
        for(char[] row : chessBoard) {
            for(char cell : row)
                System.out.format("%2s ", cell);
            System.out.println();
        }   
    }
}
