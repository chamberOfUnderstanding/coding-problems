package linked_list;

import java.util.Scanner;

public class DoublyLinkedList_Utils {
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
				System.out.print("=>");
		}			
	}

	static void displayList(Node node) {
		Node current = node;
		while(null != current){
			System.out.print(current.data);
			current = current.next;
			if(null != current)
				System.out.print("=>");
		}			
	}

	static Node readList(Scanner scanner) {
		System.out.print("\nEnter list : ");
		String input = scanner.nextLine();
		String[] nodes = input.split("=>");
		Node newNode, first2 = null;
		for(String node:nodes){
			newNode = new Node(Integer.parseInt(node));
			if(first2 == null){
				first2 = newNode;
				last = newNode;
			}
			else{
				last.next = newNode;
				newNode.previous = last;
				last = last.next;
			}
		}
		first = first2;
		return first2;
	}

	static class Node {
		int data;
		Node previous;
		Node next;

		Node(int data){
			this.data = data;
		}
	}
}
