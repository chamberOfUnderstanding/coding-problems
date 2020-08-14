package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
 * 
 */

public class Rotting_Oranges {
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			System.out.print("\nEnter rows ");
			int rows = scanner.nextInt();
			System.out.print("\nEnter columns ");
			int columns = scanner.nextInt();
			System.out.println("\nEnter oranges ");
			int[][] oranges = new int[rows][columns];
			
			// As the oranges are getting read, add the rotten ones onto the queue
			Queue<RottenOrange> queue = new LinkedList<>();
			for(int i=0; i<rows; i++){
				for(int j=0; j<columns; j++){
					oranges[i][j] = scanner.nextInt();
					if(oranges[i][j] == 2)
						queue.add(new RottenOrange(i,j));
				}
			}
			Rotting_Oranges rottingOranges = new Rotting_Oranges();
			System.out.println(rottingOranges.getTime(oranges, rows, columns, queue));
		}
	}

	// m = rows and n = columns
	
	// O(mn) , O(mn)
	private String getTime(int[][] oranges, int rows, int columns, Queue<RottenOrange> queue) {
		int time = 0;		
		
		// Add a delimiter. When this delimiter is encountered again, it means we have completed one time frame.	
		RottenOrange delimiter = new RottenOrange(-1,-1);
		queue.add(delimiter);
		
		RottenOrange rottenOrange;
		
		// This flag makes sure that the time is increased only when some fresh orange went stale
		boolean moreOrangesRotted = false;
		
		// Until the queue goes empty
		while(!queue.isEmpty()){
			
			// If the item isnt a delimiter
			while(queue.peek().row != -1){
				
				// Get a rotten orange
				rottenOrange = queue.remove();
				
				// Rot the adjacent oranges
				// Update the value of the flag based on whether more oranges rotted
				// !!!!!!!!!! Notice that short circuit OR is not used here !!!!!!!!!!!!
				moreOrangesRotted = moreOrangesRotted | rotAdjacentOranges(rottenOrange.row, rottenOrange.column, queue, oranges, rows, columns);				
			}
			
			// Now that we have seen the delimiter, that means one time frame has ended
			// Check the flag for any new rotten oranges during that time frame
			// If yes, increase the time and default the flag
			if(moreOrangesRotted){
				time++;
				moreOrangesRotted = false;
			}
			
			// Remove the delimiter and enqueue it back in
			queue.remove();
			if(!queue.isEmpty())
				queue.add(delimiter);
		}
		
		// Now that all rotten oranges have been scanned and fresh ones have been rotted, verify if any are still fresh
		return anyFreshOranges(oranges, rows, columns)? "\nIt's not possible for all the oranges to rot" : "\nTime required for all the oranges to rot is " + Integer.toString(time);
	}

	private boolean anyFreshOranges(int[][] oranges, int rows, int columns) {
		for(int i=0; i<rows; i++)
			for(int j=0; j<columns; j++)
				if(oranges[i][j] == 1)
					return true;
		return false;
	}

	private boolean rotAdjacentOranges(int row, int column, Queue<RottenOrange> queue, int[][] oranges, int rows, int columns) {
		
		// Hoist flags if you see a fresh orange on top, bottom, left or right of the current rotten orange
		boolean hasFreshTop    = row-1 < 0 ? false : oranges[row-1][column] == 1 ? true : false;
		boolean hasFreshBottom = row+1 >= rows ? false : oranges[row+1][column] == 1 ? true : false;
		boolean hasFreshLeft   = column-1 < 0 ? false : oranges[row][column-1] == 1 ? true : false;
		boolean hasFreshRight  = column+1 >= columns ? false : oranges[row][column+1] == 1 ? true : false;

		// If none of the surrounding oranges are fresh, nothing is left to be done, so exit
		if(!(hasFreshTop || hasFreshBottom || hasFreshLeft || hasFreshRight))
			return false;

		// This part is reached if there's some neighboring fresh orange
		// Find it, rot it and enqueue it
		if(hasFreshTop){
			oranges[row-1][column] = 2;
			queue.add(new RottenOrange(row-1,column));
		}

		if(hasFreshBottom){
			oranges[row+1][column] = 2;
			queue.add(new RottenOrange(row+1,column));
		}

		if(hasFreshLeft){
			oranges[row][column-1] = 2;
			queue.add(new RottenOrange(row,column-1));
		}

		if(hasFreshRight){
			oranges[row][column+1] = 2;
			queue.add(new RottenOrange(row,column+1));
		}

		// Since the rotten orange murdered one or more neighbors, return true
		return true;
	}

	private static class RottenOrange{
		int row;
		int column;

		RottenOrange(int row, int column){
			this.row = row;
			this.column = column;
		}
	}
}
