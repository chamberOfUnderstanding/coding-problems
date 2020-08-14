package linked_list;

import java.util.Scanner;

public abstract class LinkedList_Utils {

	static Node first = null;
	static Node last = null;

	static void displayList() {
		if(first == null){
			System.out.println("Empty list!");
			return;
		}
		Node current = first;
		while(null != current){
			System.out.print(current.data);
			current = current.next;
			if(null != current)
				System.out.print("->");
		}
		System.out.println();
	}

	static void displayList(Node node) {
		if(node == null){
			System.out.println("Empty list!");
			return;
		}
		Node current = node;
		while(null != current){
			System.out.print(current.data);
			current = current.next;
			if(null != current)
				System.out.print(" -> ");
		}
		System.out.println();
	}

	static Node readList(Scanner scanner) {
		System.out.print("\nEnter list : ");
		String input = scanner.nextLine();
		String[] nodes = input.split(" ");
		Node newNode, first2 = null;
		for(String node:nodes){
			newNode = new Node(Integer.parseInt(node));
			if(first2 == null){
				first2 = newNode;
				last = newNode;
			}
			else{
				last.next = newNode;
				last = last.next;
			}
		}
		first = first2;
		return first2;		
	}

	static Node readListFromString(String input) {
		String[] nodes = input.split(" ");
		Node newNode, first2 = null;
		for(String node:nodes){
			newNode = new Node(Integer.parseInt(node));
			if(first2 == null){
				first2 = newNode;
				last = newNode;
			}
			else{
				last.next = newNode;
				last = last.next;
			}
		}
		first = first2;
		return first2;		
	}
	
	static class Node {
		int data;
		Node next;

		Node(int data){
			this.data = data;
		}
		
		@Override
		public String toString() {
		    return this.data + " ";
		}
	}
}
