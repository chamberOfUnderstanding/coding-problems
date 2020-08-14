package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Select a random node from a linked list
 * The probability of picking a node should be 1/N if there are N nodes in list.
 * You are given a random number generator.
 * 
 * http://www.geeksforgeeks.org/select-a-random-node-from-a-singly-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Scan the list
 *             Generate a random number and % it by n, if it's divisible, set current data as random data
 *             Advance the list and increment n
 *
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Random_Node extends LinkedList_Utils{

	static Node first = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readList(scanner);
			System.out.println("\nRandom node : " + getRandomNode(first));			
		}
	}

	private static int getRandomNode(Node first) {
		Node current = first;
		int n = 2;
		int randomData = first.data;
		while(current != null){
			if(Math.random() % n == 0)
				randomData = current.data;
			current = current.next;
			n++;
		}
		return randomData;
	}

}
