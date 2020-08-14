package graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 *
 * Given an MxN maze with 0s and 1s. The 1s are bombs
 * Starting from (0,0) find out if there's a way to dodge the bombs and get to (m-1,n-1)
 * If there's a way, print the number of moves else print -1
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Perform BFS
 *            Push the first spot onto the queue
 *            As long as there are spots to scan (queue is not empty)
 *              Dequeue, scan the spot, mark it as visited, scan the neighboring safe spots (Up, Down, Left, Right)
 *            Check the destination once the queue goes empty
 *
 * TIME     : O(mn) // TODO
 * SPACE    : O(mn) + O(1) // Queue will have at most 5 items? TODO
 *
 * 
 */

public class Maze {

    public static void main(String...strings){
        int maxRows = 5;
        int maxColumns = 5;
        int[][] maze = 
               {{0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {1, 0, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 0, 1, 0}};
        System.out.println(escapeV2(maze, maxRows, maxColumns));
    }

    private static int escapeV2(int[][] maze, int maxRows, int maxColumns) {
        int moves = 0;
        Integer row = 0;
        Integer column = 0;
        boolean[][] visited = new boolean[maxRows][maxColumns];
        @SuppressWarnings("rawtypes")
        Queue<SimpleEntry> queue = new LinkedList<>();                
        queue.add(new SimpleEntry<Integer,Integer>(row,column));
        @SuppressWarnings("rawtypes")
        SimpleEntry cell;
        while(!queue.isEmpty()){
            cell = queue.remove();
            row = (Integer) cell.getKey();
            column = (Integer) cell.getValue();
            int[] neighboringRows    = {-1, 0, 0, 1};
            int[] neighboringColumns = { 0,-1, 1, 0}; 
            for(int i = 0; i < neighboringRows.length; i++)
                if(isItSafe(maze, maxRows, maxColumns, visited, row + neighboringRows[i], column + neighboringColumns[i])) {
                    visited[row + neighboringRows[i]][column + neighboringColumns[i]] = true;
                    queue.add(new SimpleEntry<Integer,Integer>(row + neighboringRows[i], column + neighboringColumns[i]));
                    moves++;     
                }
        }
        return row == maxRows - 1 && column == maxColumns - 1? moves : -1;
    }

    private static boolean isItSafe(int[][] maze, int maxRows, int maxColumns, boolean[][] visited, int row, int column) {
        return row > -1 && column > -1 && row < maxRows && column < maxColumns && !visited[row][column] && maze[row][column] != 1;	            
    }
}
