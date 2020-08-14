package array;

/**
 * @author 47un
 *
 * Rotate the given matrix by 90 degrees, clock wise and anti clockwise, without using any extra space.
 * 
 * Input:
 * 
 *   1  2  3  4
 *   5  6  7  8
 *   9 10 11 12
 *  13 14 15 16 
 *  
 * Output:
 * 
 *   4  8 12 16
 *   3  7 11 15
 *   2  6 10 14
 *   1  5  9 13
 * 
 * http://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
 * 
 * ================
 * METHOD 1 : Swaps
 * ================
 * This is ripped from the gfg link above. This explains it well enough.
 * Also this is for CCW rotation.
 * For CW, copy the items from left instead of right
 * 
 * First row of source –> First column of destination, elements filled in opposite order
 * Second row of source –> Second column of destination, elements filled in opposite order
 * so … on
 * Last row of source –> Last column of destination, elements filled in opposite order.
 * An N x N matrix will have floor(N/2) square cycles. For example, a 4 X 4 matrix will have 2 cycles. The first cycle is formed by its 1st row, last column, last row and 1st column.
 * The second cycle is formed by 2nd row, second-last column, second-last row and 2nd column.
 * The idea is for each square cycle, we swap the elements involved with the corresponding cell in the matrix in anti-clockwise direction i.e. from top to left, left to bottom, bottom to right and from right to top one at a time. We use nothing but a temporary variable to achieve this.
 * 
 * Below steps demonstrate the idea
 * 
 * First Cycle (Involves Red Elements)
 *  1  2  3  4 
 *  5  6  7  8 
 *  9 10 11 12 
 * 13 14 15 16
 * 
 * Moving first group of four elements (First elements of 1st row, last row, 1st column and last column) of first cycle in counter
 * clockwise. 
 *  4  2  3 16
 *  5  6  7 8 
 *  9 10 11 12 
 *  1 14  15 13 
 * 
 * Moving next group of four elements of first cycle in counter clockwise 
 *  4  8  3 16 
 *  5  6  7  15  
 *  2  10 11 12 
 *  1  14  9 13 
 * 
 * Moving final group of four elements of first cycle in counter clockwise 
 *  4  8 12 16 
 *  3  6  7 15 
 *  2 10 11 14 
 *  1  5  9 13 
 * 
 * Second Cycle (Involves Blue Elements)
 *  4  8 12 16 
 *  3  6 7  15 
 *  2  10 11 14 
 *  1  5  9 13 
 * 
 * Fixing second cycle
 *  4  8 12 16 
 *  3  7 11 15 
 *  2  6 10 14 
 *  1  5  9 13
 *             
 * TIME    : O(n^2)
 * SPACE   : O(1)
 * 
 */

public class Rotate_Square_Matrix_90 {

	public static void main(String[] args) {
		int[][] matrix = 
			  {{ 1,  2,  3,  4},
		       { 5,  6,  7,  8},
			   { 9, 10, 11, 12},
			   {13, 14, 15, 16}};
		rotateMatrix90CW(matrix);
		rotateMatrix90CCW(matrix);
	}

	private static void rotateMatrix90CCW(int[][] matrix) {
		System.out.println("Rotating 90 degrees, CCW\n");
		int size = matrix.length;
		for(int i = 0; i < size/2; i++)
			for(int j = i; j < size - i - 1; j++) {
				int rotationStarter = matrix[i][j];
				matrix[i][j]    = matrix[j][size - i - 1];
				matrix[j][size - i - 1] = matrix[size - i - 1][size - j - 1];
				matrix[size - i - 1][size - j - 1] = matrix[size - j - 1][i];
				matrix[size - j - 1][i] = rotationStarter;
			}
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
	}

	private static void rotateMatrix90CW(int[][] matrix) {
		System.out.println("Rotating 90 degrees, CW\n");
		int size = matrix.length;
		for(int i = 0; i < size/2; i++)
			for(int j = i; j < size - i - 1; j++) {
				int rotationStarter = matrix[i][j];
				matrix[i][j]    = matrix[size - j - 1][i];
				matrix[size - j - 1][i] = matrix[size - i - 1][size - j - 1];
				matrix[size - i - 1][size - j - 1] = matrix[j][size - i - 1];
				matrix[j][size - i - 1] = rotationStarter;
			}
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
	}
}
