package array.matrix;

/**
 * @author 47un
 *
 * Given an n x n matrix, where every row and column is sorted in non-decreasing order. 
 * Find the kth smallest element in the given matrix.
 * For example, consider the following 2D array.
 *      10, 20, 30, 40
 *      15, 25, 35, 45
 *      24, 29, 37, 48
 *      32, 33, 39, 50
   The 3rd smallest element is 20 and 7th smallest element is 30
 * http://www.geeksforgeeks.org/kth-smallest-element-in-a-row-wise-and-column-wise-sorted-2d-array-set-1/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Use a min heap
 *            Populate the min heap with values in the first row.
 *            No need to sink them as they are already sorted
 *            Now repeat the below steps k times :
 *              Get an item from the heap
 *              Increase the row by 1 and get the value at this row (for the same column)
 *              If this goes out of bounds, then use the value Integer.MAX_VALUE (this will make the lower values float up)
 *              Else get the value of the matrix at row+1, column
 *              Now that you have the value, assign this to the min heap's root
 *              Sink the root      
 *            
 *            For this we need a custom class that holds a value and the row and column where this value can be found in the matrix
 *            All the heap operations need to be modified to support this class
 *
 * TIME     : O(klogk)       // Loop around, change the root and sink items, k times  
 * SPACE    : O(columns)     // This is always the size of the heap
 *
 * 
 */

public class Matrix_Kth_Smallest {
    
    public static void main(String[] args) {
        int rows    = 4;
        int columns = 4;
        int[][] matrix = {{10, 20, 30, 40},
                          {25, 35, 45, 55},
                          {30, 40, 50, 60},
                          {35, 45, 55, 65}};
        int k = 16;
        System.out.println(k + " th max is : " + findKthMax(matrix, rows, columns, k));
    }

    private static int findKthMax(int[][] matrix, int rows, int columns, int k) {
        Cell[] minHeap = new Cell[columns];
        for(int i = 0; i < columns; i++)
            minHeap[i] = new Cell(matrix[0][i], 0 , i);
        while(k-- > 1) {
            int minRow    = minHeap[0].row;
            int minColumn = minHeap[0].column;
            int value     = minRow + 1 < rows? matrix[minRow + 1][minColumn] : Integer.MAX_VALUE;            
            minHeap[0]    = new Cell(value, minRow + 1, minColumn);
            sink(minHeap, 0, rows - 1);            
        }
        return minHeap[0].value;
    }
   
    private static void sink(Cell[] minHeap, int parent, int heapSize) {
        while(2 * parent <= heapSize) {
            int child = 2 * parent;
            if(child < heapSize && minHeap[child + 1].value < minHeap[child].value)
                child++;
            if(minHeap[parent].value <= minHeap[child].value)
                return;
            swap(minHeap, parent, child);
            parent = child;
        }
    }
    
    private static class Cell{
        int value;
        int row;
        int column;
        
        Cell(int value, int row, int column) {
            this.value  = value;
            this.row    = row;
            this.column = column;
        }
    }
    
    private static void swap(Cell[] minHeap, int i, int j) {
        int value  = minHeap[i].value;
        int row    = minHeap[i].row;
        int column = minHeap[i].column;
        minHeap[i].value  = minHeap[j].value;         
        minHeap[i].row    = minHeap[j].row;
        minHeap[i].column = minHeap[j].column;
        minHeap[j].value  = value;         
        minHeap[j].row    = row;
        minHeap[j].column = column;
    }
}
