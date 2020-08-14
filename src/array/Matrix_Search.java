package array;

/**
 * @author 47un
 *
 * Given an n x n matrix, where every row and column is sorted in increasing order.
 * Given a number x, how to decide whether this x is in the matrix.
 * The designed algorithm should have linear time complexity.
 * 
 * e.g. 
 *      10 20 30 40
 *      15 25 35 45
 *      27 29 37 48
 *      32 33 39 50
 *      
 * If you search for 40, bingo!
 * If you search for -293, not so lucky..
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Start from element at TOP RIGHT!
 *            Return true if the element has been found
 *            If the current element is less than the target, go down
 *            If the current element is greater than the target, go left
 *            
 * TIME     : 
 * SPACE    : 
 *
 * 
 */

public class Matrix_Search {
   
    public static void main(String...strings){
        int rows    = 4;
        int columns = 4;
        int[][] matrix =
            {{10, 20, 30, 40},
             {15, 25, 35, 45},
             {27, 29, 37, 48},
             {32, 33, 39, 50}};	 
        int target = 32;
        System.out.print(search(matrix, rows, columns, target)? 
                "\nItem found!" : 
                "\nSometimes you never get what you want in life");
    }

	private static boolean search(int[][] matrix, int rows, int columns, int target) {
	    int rowIndex       = 0;
	    int columnIndex    = columns - 1;
	    int currentElement = 0;
		while(rowIndex < rows && columnIndex < columns){			
			currentElement = matrix[rowIndex][columnIndex];
			if(currentElement == target)
				return true;
			else if(target > currentElement)
				rowIndex++;
			else if(target < currentElement)
				columnIndex--;
		}
		return false;
	}
}