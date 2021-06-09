package array;

/**
 * @author 47un
 *
 *  Given a 2d array with only two elements # and . where # represents cherry and . represents nothing. 
 *  Divide the array into 2 halves with equal cherries.
 *  You can only make a single cut either horizontally or vertically.
 *  
 *  e.g    # . . #         => NO
 *         # # # .
 *         
 *         # # # # #       => YES
 *         . . . . .
 *         # . . # .
 *         . . # # #
 *         . . . . .
 *         
 *         # . . #         => NO
 *         # # # .
 *         # # . .
 * 
 */

public class Matrix_Partitioning {
	
    public static void main(String...strings){
			char[][] matrix = 
			        {{'#', '#', '#', '#', '#'},
			         {'.', '.', '.', '.', '.'},
			         {'#', '.', '.', '#', '.'},
			         {'.', '.', '#', '#', '#'},
			         {'.', '.', '.', '.', '.'}};
			System.out.print("Can they be divided? " + (isPartitionable(matrix, 5, 5)? "Yes" : "No"));
		}

    // TIME   : O(m*n) + O(m) + O(n) => O(m*n)
    // SPACE  : O(m + n)
	private static boolean isPartitionable(char[][] matrix, int rows, int columns) {
		
		// tracks cherries in row and cherries in column
		int[] cherriesInRow    = new int[rows];
		int[] cherriesInColumn = new int[columns];
		
		// calculate cherries in row and column along with total cherries		
		int totalCherries = 0;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++){
				if(matrix[i][j] == '#'){
					cherriesInRow[i]++;
					cherriesInColumn[j]++;
					totalCherries++;
				} 
			}

		// partitioning is not possible if total cherries is odd
		// else attempt the cut both horizontally and vertically
		return 	totalCherries % 2 == 0 && 
				cut(cherriesInRow, totalCherries) || 
				cut(cherriesInColumn, totalCherries);
	}

	private static boolean cut(int[] cherries, int totalCherries) {
	    int distributed = 0;
		for(int i = 0; i < cherries.length; i++) {
			
			// mark the cherries as distributed
			distributed += cherries[i];
			
			// if distributed reaches half of the total cherries, then a cut is POSSIBLE
			if(distributed == totalCherries/2)
				return true;					
		}
		return false;
	}
}
