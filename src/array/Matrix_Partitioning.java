package array;

/**
 * @author 47un
 *
 *  Given a 2d array with only two elements �#� & �.� . �#� represents cherry and �.� represents nothing. 
 *  Divide the array into 2 halves with equal cherries.
 *  You can only make a single cut either horizontally or vertically.
 *  
 *  e.g    # . . #   => NO
 *         # # # .
 *         
 *         # # # # # => YES  //This is the input in to the code
 *         . . . . .
 *         # . . # .
 *         . . # # #
 *         . . . . .
 *         
 *         # . . #  => NO
 *         # # # .
 *         # # . .
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Maintain two arrays that store the # cherries in a given row (cherriesInRow) & the # cherries in a column (cherriesInColumn)
 *            Scan the cherry matrix and update these arrays. Also calculate the # cherries
 *            If the # cherries is odd, then there is no way to evenly distribute it, so quit
 *            Else see if a horizontal or vertical cut can do the job
 *              For this, scan the cherriesInRow array and add each cherry to the distributedCherries
 *              An even distribution occurs if distributedCherries = half # cherries. Return true if this happens
 *            If the cuts don't do it, return false
 *            
 * TIME     : O(m*n) + O(m) + O(n) => O(m*n)
 * SPACE    : O(m + n)
 *
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

	private static boolean isPartitionable(char[][] matrix, int rows, int columns) {
		int[] cherriesInRow    = new int[rows];
		int[] cherriesInColumn = new int[columns];
		int totalCherries = 0;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++){
				if(matrix[i][j] == '#'){
					cherriesInRow[i]++;
					cherriesInColumn[j]++;
					totalCherries++;
				} 
			}
		return totalCherries % 2 != 0? false : cut(cherriesInRow, totalCherries) || cut(cherriesInColumn, totalCherries);
	}

	private static boolean cut(int[] cherriesInRow, int totalCherries) {
	    int cherries = 0;
		for(int i = 0; i < cherriesInRow.length; i++){
			cherries += cherriesInRow[i];
			if(cherries == totalCherries/2)
				return true;					
		}
		return false;
	}
}
