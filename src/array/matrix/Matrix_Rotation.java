package array.matrix;

import java.util.LinkedList;

/**
 * @author 47un
 *
 * Rotate each layer of the given matrix k times
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Peel off a layer, add it to a list, rotate the list, fill the rotated matrix
 *
 * TIME     : O(m*n)
 * SPACE    : O(m+n) // List containing the largest layer
 *
 * 
 */

public class Matrix_Rotation {

	 public static void main(String... strings){				
		int rows = 4;
		int columns = 4;
		int rotations = 5;
	    int[][] matrix = {{10,20,30,40},
	                      {25,35,45,55},
	                      {30,40,50,60},
	                      {35,45,55,65}};		
		rotate(matrix, rows, columns, rotations, 0);
		printMatrix(matrix, rows, columns);
	}

	private static void rotate(int[][] matrix, int rows, int columns, int rotations,int startingIndex){
		int firstRow    = startingIndex;
		int lastRow     = startingIndex + rows;
		int firstColumn = startingIndex;
		int lastColumn  = startingIndex + columns;
		LinkedList<Integer> layer = new LinkedList<Integer>();
		
		for(int columnIndex = firstColumn; columnIndex < lastColumn; columnIndex++)				
			layer.add(matrix[firstRow][columnIndex]);
		
		for(int rowIndex = firstRow + 1; rowIndex < lastRow; rowIndex++)
			layer.add(matrix[rowIndex][lastColumn - 1]);
		
		for(int columnIndex = lastColumn - 2; columnIndex >= firstColumn; columnIndex--)
			layer.add(matrix[lastRow - 1][columnIndex]);
		
		for(int rowIndex = lastRow - 2; rowIndex > firstRow; rowIndex--)
			layer.add(matrix[rowIndex][firstColumn]);
		
		rotateList(layer, rotations);	
		fillRotatedMatrix(layer, rows, columns, startingIndex, matrix);
		if(rows-2 < 2 || columns-2 < 2)
			return;
		else
			rotate(matrix, rows-2, columns-2, rotations, startingIndex+1);
	}
	
    private static void rotateList(LinkedList<Integer> list, int rotations) {
        int rotationsNeeded = rotations%list.size();
        while(rotationsNeeded-- > 0)            
            list.addLast(list.removeFirst());           
    }

	private static void fillRotatedMatrix(LinkedList<Integer> list, int rows, int columns, int startingIndex,int[][] rotatedMatrix) {
		int firstRow    = startingIndex;
		int lastRow     = startingIndex + rows;
		int firstColumn = startingIndex;
		int lastColumn  = startingIndex + columns;
		
		for(int columnIndex = firstColumn; columnIndex < lastColumn; columnIndex++)				
			rotatedMatrix[firstRow][columnIndex] = list.removeFirst();
		
		for(int rowIndex = firstRow+1; rowIndex < lastRow; rowIndex++)
			rotatedMatrix[rowIndex][lastColumn-1] = list.removeFirst();
		
		for(int columnIndex = lastColumn-2; columnIndex >= firstColumn; columnIndex--)
			rotatedMatrix[lastRow-1][columnIndex] = list.removeFirst();
		
		for(int rowIndex = lastRow-2; rowIndex > firstRow; rowIndex--)
			rotatedMatrix[rowIndex][firstColumn] = list.removeFirst();
	}

	private static void printMatrix(int[][] matrix, int rows, int columns) {
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println(" ");	
		}
	}
}
