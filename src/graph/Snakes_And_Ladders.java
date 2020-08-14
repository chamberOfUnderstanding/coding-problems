package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 *
 * Given a snake and ladder board, find the minimum number of dice throws required to reach the destination or last cell from source or 1st cell.
 * The player has total control over outcome of dice throw and wants to find out minimum number of throws required to reach last cell.
 * If the player reaches a cell which is base of a ladder, the player has to climb up that ladder and if reaches a cell is mouth of the snake, has to go down to the tail of snake without a dice throw.
 * 
 * http://www.geeksforgeeks.org/snake-ladder-problem-2/
 * 
 * ===============
 * METHOD 1 : BFS
 * ===============
 * The input is 1 index based
 * So while preparing the board
 *  Initially fill all places with -1
 *  For each obstacle, i
 *   Add an entry in the board at index obstacle[i] - 1 with value = next obstacle - 1
 *   Say if it is a ladder from 13 to 77, then board[12] = 76
 *   Remember, the board is 0 index based, so all obstacles land in an index that's 1 less than the obstacle value
 *   
 * Since the graph is unweighted (having equal weights, BFS will give the shortest path)
 * Level by level scanning is required here, hence BFS
 * Enqueue the first cell, 0 and it takes 0 rolls to get here
 * As long as the queue is not empty
 *  Dequeue a cell
 *  If this cell is the last one, exit
 *  Scan the neighbors that are reachable through dice rolls. i.e. if i is the current cell, then scan from i + 1 to i + 6
 *   Make sure i doesnt cross the last cell
 *   For each unvisited neighbor
 *    Mark the neighbor as visited
 *    If this neighbor has a ladder or a snake (board[i] != -1) include this in the cell data
 *    Else if this neighbor has no paths to anywhere (board[i] = -1), just add i to the cell data
 *    Number of rolls needed to get here = current rolls + 1
 *    Enqueue this cell data                    
 *              
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Snakes_And_Ladders {

    public static void main(String[] args) {
        int boardSize  = 30;
        int[] obstacle = {2, 30};
        System.out.println("Minimum rolls needed :" + findMinimumRolls(obstacle, boardSize));
    }

    private static int findMinimumRolls(int[] obstacle, int boardSize) {
        int[] board = prepareBoard(obstacle, boardSize);
        boolean[] visited = new boolean[boardSize];
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(0, 0));
        Cell cell = null;
        while(!queue.isEmpty()) {
            cell = queue.remove();
            if(cell.data == boardSize - 1)
                break;
            for(int i = cell.data + 1; i <= cell.data + 6 && i < boardSize; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    queue.add(new Cell(board[i] != -1? board[i] : i, cell.rolls + 1));
                }
            }
        }
        return cell.rolls;
    }

    private static int[] prepareBoard(int[] obstacle, int boardSize) {
        int[] board = new int[boardSize];
        Arrays.fill(board, -1);
        for(int i = 0; i < obstacle.length - 1; i += 2)
            board[obstacle[i] - 1] = obstacle[i + 1] - 1;
        return board;
    }

    private static class Cell{
        int data;
        int rolls;

        public Cell(int data, int rolls) {
            this.data = data;
            this.rolls = rolls;
        }
    }
}
