package queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 47un
 *
 * http://www.geeksforgeeks.org/efficiently-implement-k-queues-single-array/
 */

public class K_Queues {
	static final int SIZE = 100;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			K_Queues kQueues = new K_Queues();
			kQueues.operate(scanner);
		}
	}

	private void operate(Scanner scanner) {
		int[] array = new int[SIZE];
		System.out.print("\nEnter number of queues ");
		int k = scanner.nextInt();
		scanner.nextLine();
		
		// Here we need two extra arrays to store the fronts and rears of the queues
		int[] front = new int[k];
		int[] rear = new int[k];
		Arrays.fill(front, -1);
		Arrays.fill(rear, -1);
		
		// The next array stores the next items
		// The last item's next is -1
		int[] next = new int[SIZE];
		for(int i=0; i<SIZE ; i++)
			next[i] = i+1;
		next[SIZE-1] = -1;		
		
		int free = 0;
		int queueNumber = 0;
		System.out.print(""
				+ "\nEnter operation"
				+ "\n1. nq Y x : Enqueues x onto queue Y"
				+ "\n2. dq Y : Dequeues Y"
				+ "\n4. quit"
				+ "\n");
		while(true){
			String operation = scanner.nextLine();
			switch(operation.substring(0,2)){			
			// Enqueue			
			case "nq" :
				int data = Integer.parseInt(operation.substring(5));
				queueNumber	= Character.getNumericValue(operation.charAt(3));
				queueNumber--;
				
				// If free = -1, the array is full
				if(free == -1){
					System.out.println("Overflow! Dequeue some elements or quit");
					continue;
				}
				
				// Get the current free slot
				int currentFreeSlot = free;
				
				// Update the free slot to the next one
				free = next[currentFreeSlot];
				
				// See if the queue is empty, yes? currentFreeSlot is the front of the queue now
				if(front[queueNumber] == -1)
					front[queueNumber] = currentFreeSlot;
				
				// If the queue is not empty, next of the queue's rear is the current free slot
				else
					next[rear[queueNumber]] = currentFreeSlot;
				
				// Next of current is non existent now
				next[currentFreeSlot] = -1;

				// New rear is now current free slot
				rear[queueNumber] = currentFreeSlot;
				
				// Place the data at the current free slot (this operation makes it no longer free)
				array[currentFreeSlot] = data;
				break;
				
			// Dequeue
			case "dq" :					
				queueNumber	= Character.getNumericValue(operation.charAt(3));
				queueNumber--;
				if(front[queueNumber] == -1)
					System.out.println("Underflow! Dequeue failed");
				else{
					
					// Get the the item to be removed (front of queue)
					int soonToBeFreeSlot = front[queueNumber];
					
					// New front is the next
					front[queueNumber] = next[soonToBeFreeSlot];
					
					// The next slot of soon to be free is now free and updated to the current free slot
					next[soonToBeFreeSlot] = free;
					
					// Update the free slot to the slot from where the item was just removed
					free = soonToBeFreeSlot;
					System.out.println("Dequeued "+array[soonToBeFreeSlot]);
				}
				break;
			case "qu" :
				return;
			}
		}
	}	
}