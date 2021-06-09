package array;

/**
 * @author 47un
 *
 * Print the given matrix in spiral form.
 * 
 * Input:  1    2   3   4
 *         5    6   7   8
 *         9   10  11  12
 *         13  14  15  16
 *         
 * Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10 
 * 
 * Input:  1   2   3   4  5   6
 *         7   8   9  10  11  12
 *         13  14  15 16  17  18
 *         
 * Output: 1 2 3 4 5 6 12 18 17 16 15 14 13 7 8 9 10 11
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Maintain variables to track where the row/column start and ends
 *            Scan from starting column to ending column and print the elements
 *            Update the starting row
 *             Since the next item to be printed is on this row, at the last column
 *            Scan from starting row to ending row and print the elements
 *            Update the ending column
 *             Since the next item to be printed is on this column, at the last row
 *            Since the starting row value was updated, make sure that it is still < endingRow
 *            If there are any rows left, scan from ending column to starting column and print the elements
 *            Update ending row
 *            Since the ending row value was updated, make sure that it is still > startingCol
 *            If there are any columns left, scan from ending row to starting row and print the elements
 *            Update ending column
 *            
 * TIME     : O(m * n)
 * SPACE    : O(1)
 *
 * 
 */

public class Matrix_In_Spiral_Form {

    public static void main(String...strings){
        int[][] matrix = 
            {{ 1,  2,  3,  4,  5,  6},
             { 7,  8,  9, 10, 11, 12},
             {13, 14, 15, 16, 17, 18}};
        int rows    = 3;
        int columns = 6;
        System.out.print("\nBehold the spiral form : ");
        printMatrixInSpiralForm(matrix, rows, columns);
    }

    private static void printMatrixInSpiralForm(int[][] matrix, int rows, int columns) {
        // maintain variables to track where the row/column start and ends
        int startingRow    = 0;
        int endingRow      = rows - 1;
        int startingColumn = 0;
        int endingColumn   = columns - 1;
        
        while(startingRow <= endingRow && startingColumn <= endingColumn) {
            
        	// print left to right
            for(int i = startingColumn; i <= endingColumn; i++)
                System.out.print(matrix[startingRow][i] + " ");		
            
            // go down
            startingRow++;
            
            // print top to bottom
            for(int i = startingRow; i <= endingRow; i++)
                System.out.print(matrix[i][endingColumn] + " ");
            
            // go left
            endingColumn--;
            
            // if there are any rows left
            if(startingRow < endingRow){
            	
            	// print in right to left
                for(int i = endingColumn; i >= startingColumn; i--)
                    System.out.print(matrix[endingRow][i] + " ");
                
                // go up
                endingRow--;
            }
            
            // if there are any columns left
            if(startingColumn < endingColumn){
            	
            	// print bottom to top
                for(int i = endingRow; i >= startingRow; i--)
                    System.out.print(matrix[i][startingColumn] + " ");
                
                // go right
                startingColumn++;
            }
        }
    }
}
