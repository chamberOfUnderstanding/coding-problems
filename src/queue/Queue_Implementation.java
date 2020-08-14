package queue;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Implement a queue using arrays and linked lists
 * 
 * Enqueue
 *  Array implementation
 *   If rear = SIZE, then there's overflow
 *   Else add the item at rear and update rear
 *  List implementation
 *   No overflow issues here
 *   Stick the new node at the end of the list
 * 
 * Dequeue
 *  Array implementation
 *   If front = 0 and rear = 0 (The initial values), then queue is empty, so underflow
 *   Delete the item at front and update front
 *  List implementation
 *   If the list is empty, underflow
 *   Remove the first node of the list
 *   
 */

public class Queue_Implementation {

	public static final int SIZE = 100;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			int[] queueUsingArray = new int[SIZE];
			int front = 0;
			int rear  = 0;
			LinkedList queueUsingLinkedList = new LinkedList();
			System.out.print(""
			        + "\nEnter operation\n"
			        + "1. n x : Enqueues x\n"
			        + "2. d : Dequeues\n"
			        + "3. f : Prints front\n"
			        + "4. r : Prints rear\n"
			        + "5. q : Quit\n");
			while(true){
				String operation = scanner.nextLine();
				if(operation.charAt(0) == 'q')
				    return;
				switch(operation.charAt(0)){
				case 'n' :
					if(rear == SIZE){
						System.out.println("\nCapacity reached. Dequeue some items!");
						continue;
					}
					int data = Integer.parseInt(operation.substring(2));
					queueUsingLinkedList.enqueue(data);
					queueUsingArray[rear++] = data;
					break;
				case 'd' :
					if(queueUsingLinkedList.isEmpty() || underFlowCheckOnQueueUsingArray(front, rear)){
						System.out.println("Queue is empty");
						continue;
					}
					System.out.println("Dequeued : " + queueUsingLinkedList.dequeue().data + " " + queueUsingArray[front++]);
					break;
				case 'f' :
					if(underFlowCheckOnQueueUsingArray(front, rear) || queueUsingLinkedList.isEmpty())
						System.out.println("Queue is empty");
					else
						System.out.println("Front  : " + queueUsingLinkedList.front() + " " + queueUsingArray[front]);
					break;
				case 'r' :
					if(underFlowCheckOnQueueUsingArray(front, rear) || queueUsingLinkedList.isEmpty())
						System.out.println("Queue is empty");
					else
						System.out.println("Rear  : " + queueUsingLinkedList.rear() + " " + queueUsingArray[rear - 1]);
					break;
				}
			}
		}
	}
	
	private static boolean underFlowCheckOnQueueUsingArray(int front, int rear) {
	    return rear == 0 && front == 0 || front > rear;    
	}
	
	private static class LinkedList{

		static Node front = null;
		static Node rear = null;

		private class Node{
			int data;
			Node link;

			Node(int data){
				this.data = data;
			}
		}

		public Integer front() {
			return front.data;
		}

		public Integer rear() {
			return rear.data;
		}

		public boolean isEmpty() {
			return front == null;
		}

		public void enqueue(int data) {			
			Node node = new Node(data);
			if(rear == null){
				rear = node;
				front = rear;
			}
			else{
				rear.link = node;
				rear = rear.link;
			}
		}
		
		public Node dequeue() {
			Node dequeued = front;
			front = front.link;
			return dequeued;
		}		
	}
}
