package graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author 47un
 *
 * Given a two dimensional grid, each cell of which contains integer cost which represents a cost to traverse through that cell.
 * Find a path from top left cell to bottom right cell by which total cost incurred is minimum.
 * Note : It is assumed that negative cost cycles do not exist in input matrix.
 * 
 * http://www.geeksforgeeks.org/minimum-cost-path-left-right-bottom-moves-allowed/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Dijkstra's
 *            Use a priority queue.
 *            Get the nearest cell (Initially it is just 0), say (u, v)
 *            Scan the neighbors of this cell (Up, Down, Left, Right) (u-1, v) (u+1, v) (u, v+1) (u, v-1)
 *              Distance to reach the neighbor = Minimum(distance to reach (u, v) and distance to reach neighbor (grid[][]))
 *              If a smaller distance is found, remove the current entry for this cell in the priority queue
 *              Update the distance for the cell
 *              Enqueue the new cell node 
 *
 * TIME     : O(V)  // Priority queue will hold max of 4 items, so log4, a constant
 * SPACE    : O(V)
 *
 * 
 */

public class Minimum_Cost_Path_UDLR {

    public final static int INFINITY = 999999;

    public static void main(String[] args) {
        int maxRows    = 5;
        int maxColumns = 5;
        int[][] grid =
            { {31, 100, 65, 12, 18},
                    {10, 13, 47, 157, 6},
                    {100, 113, 174, 11, 33},
                    {88, 124, 41, 20, 140},
                    {99, 32, 111, 41, 20} };
        System.out.println(minimumCostPathUDLR(maxRows, maxColumns, grid));
    }

    private static int minimumCostPathUDLR(int maxRows, int maxColumns, int[][] grid) {
        int[][] distance = new int[maxRows][maxColumns];
        for(int[] row : distance)
            Arrays.fill(row, INFINITY);
        int[] dx = {-1,  0, 1,  0};
        int[] dy = { 0,  1, 0, -1};
        PriorityQueue<Cell> priorityQueue = new PriorityQueue<>((cell1, cell2) -> cell1.distance < cell2.distance? -1 : 1);
        priorityQueue.add(new Cell(0, 0, 0));
        distance[0][0] = grid[0][0];
        while(!priorityQueue.isEmpty()) {
            Cell nearestCell = priorityQueue.remove();
            for(int i = 0; i < 4; i++) {
                int row    = nearestCell.row    + dx[i];
                int column = nearestCell.column + dy[i];                
                if(isSafe(row, column, maxRows, maxColumns)) {
                    int newDistance = distance[nearestCell.row][nearestCell.column] + grid[row][column];
                    if(newDistance < distance[row][column]) {
                        if(distance[row][column] != INFINITY)
                            priorityQueue.remove(new Cell(row, column, distance[row][column]));
                        distance[row][column] = newDistance;
                        priorityQueue.add(new Cell(row, column, newDistance));
                    }
                }
            }
        }        
        return distance[maxRows - 1][maxColumns - 1];
    }

    private static boolean isSafe(int row, int column, int maxRows, int maxColumns) {
        return row > -1 && row < maxRows && column > -1 && column < maxColumns;
    }

    private static class Cell{
        int row;
        int column;
        int distance;

        public Cell(int row, int column, int distance) {
            this.row = row;
            this.column = column;
            this.distance = distance;
        }

        public boolean equals(Object object) {
            return object != null && object instanceof Cell && ((Cell)object).row == this.row && ((Cell)object).column == this.column; 
        }

        public int hashCode() {
            return row * 7 + column;
        }

        public String toString() {
            return this.row + "," + this.column + " : " + this.distance;
        }
    }
}
