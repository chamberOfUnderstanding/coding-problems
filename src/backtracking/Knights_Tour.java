package backtracking;

/**
 * @author 47un
 *
 * The knight is placed on block (x,y) of an empty chess board.
 * Moving according to the rules of chess, the knight must visit each square exactly once.
 * 
 * http://www.geeksforgeeks.org/backtracking-set-1-the-knights-tour-problem/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : For each possible move
 *             Check if this move is safe (within bounds and the destination cell has not been visited before)
 *             If yes, update this cell with the new move number and try to solve the rest
 *             If no solution exists, revert this move (backtrack) by setting the value at that cell back to 0
 *
 * TIME     : O(8^number of cells) => 8^(n^2-1) for a chess board of order n x n
 * SPACE    : O(1)
 *
 * NOTE : The board is initially filled with -1, except the spot where the knight starts, which holds a 0
 * 
 */

public class Knights_Tour {

    public static void main(String[] args) {
        int row    = 0;
        int column = 0;     
        int[][] chessBoard = findSolution(row, column);
        if(chessBoard != null)
            printBoard(chessBoard, 8);
        else
            System.out.println("Nope nope nope!");
    }

    private static int[][] findSolution(int row, int column) {
        int[][] chessBoard = new int[8][8];
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                chessBoard[i][j] = -1;
        chessBoard[row][column] = 0;
        return tour(row, column, chessBoard, 1)? chessBoard : null;
    }

    private static boolean tour(int row, int column, int[][] chessBoard, int moves) {      
        if(moves == 64)
            return true;
        int[] destRows = { -2, -2, -1, -1,  1,  1,  2,  2 }; 
        int[] destCols = { -1,  1, -2,  2, -2,  2, -1,  1 };
        for(int i = 0; i < 8; i++) {
            int nextRow = row    + destRows[i];
            int nextCol = column + destCols[i];
            if(isSafeToMove(nextRow, nextCol, chessBoard)) {
                chessBoard[nextRow][nextCol] = moves;
                if(tour(nextRow, nextCol, chessBoard, moves + 1))
                    return true;
                else
                    chessBoard[nextRow][nextCol] = -1;
            }
        }
        return false;
    }

    private static boolean isSafeToMove(int row, int column, int[][] chessBoard) {
        return row > -1 && row < 8 && column > -1 && column < 8 && chessBoard[row][column] == -1;
    }

    private static void printBoard(int[][] board, int rows) {
        printRowSeparator(rows);
        for(int[] row : board) {
            for(int cell : row)
                System.out.format("%2s |", cell);
            printRowSeparator(rows);
        }   
    }

    private static void printRowSeparator(int rows) {
        rows *= 4.7;
        System.out.println();
        while(rows-- > 0)
            System.out.print("-");
        if(rows != 1)
            System.out.print("\n|");
    }
}
