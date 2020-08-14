package array;

/**
 * @author 47un
 *
 * A Tic-Tac-Toe board is given after some moves are played.
 * Find out if the given board is valid, i.e., is it possible to reach this board position after some moves or not.
 * Given that X makes the first move.
 * 
 * http://www.geeksforgeeks.org/validity-of-a-given-tic-tac-toe-board-configuration/
 * 
 * ==========
 * METHOD 1  
 * ==========
 * Count the number of Xs and Os
 * Since X makes the first move, the # X will always be either equal to # O or, 1 + # O
 * If neither of the above conditions are met, the board is invalid
 * Now check who all won the board, X or O or both
 *  To assist with this, indexes of all the winning combinations are stored in a 2D array (8 of them : 3 rows, 3 columns and 2 diagonals)
 *  For each winning combination,
 *   Check if the board holds the same value for the indices, if yes return true
 * If X and O won the board, the board is invalid
 * If X won the board and the number of Xs is not 1 + number of Os, the board is invalid
 * If O won the board and the number of Os is not equal to number of Xs, the board is invalid
 * If the board survives till here, it's a valid one
 *             
 * TIME    : O(1) 
 * SPACE   : O(1)
 * 
 * 
 */

public class Validate_Tic_Tac_Toe {

	public static void main(String[] args) {
		char board[] = 
		    {'O', 'X', 'X',
		     'X', 'O', 'X',
		     'O', 'O', 'X'};
		System.out.println(isValid(board));
	}

	private static boolean isValid(char[] board) {
		int xCount = 0;
		int oCount = 0;
		for(int i = 0; i < 9; i++)
			if(board[i] == 'X')
				xCount++;
			else if(board[i] == 'O')
				oCount++;
		boolean areCountsEqual       = xCount == oCount;
		boolean isXCountOneMoreThanO = xCount == oCount + 1;
		if(!areCountsEqual && !isXCountOneMoreThanO)
			return false;
		boolean xWins = isWinner(board, 'X');
		boolean oWins = isWinner(board, 'O');
		if(xWins && (oWins || !isXCountOneMoreThanO))
			return false;
		if(oWins && !areCountsEqual)
			return false;
		return true;
	}

	private static boolean isWinner(char[] board, char marker) {
		int[][] winningCombinations =  
				   {{0, 1, 2},  
					{3, 4, 5},
					{6, 7, 8},
					{0, 3, 6},
					{1, 4, 7},
					{2, 5, 8},
					{0, 4, 8},
					{2, 4, 6}};
		for(int i = 0; i < 8; i++)
			if(board[winningCombinations[i][0]] == marker &&
			   board[winningCombinations[i][1]] == marker &&
			   board[winningCombinations[i][2]] == marker)
				return true;
		return false;
	}
}
