package linked_list;

import java.util.Scanner;

public class CircularLinkedList_Utils {

	static void displayList(Node last) {
		if(last == null){
			System.out.println("Empty list!");
			return;
		}
		Node current = last.link;		
		while(current != last){
			System.out.print(current.data+"->");
			current = current.link;
		}
		System.out.print(current.data);
	}

	static Node readList(Scanner scanner) {
		System.out.print("\nEnter list : ");
		String input = scanner.nextLine();
		String[] nodes = input.split("->");
		Node newNode, first = null, last = null;
		for(String node:nodes){
			newNode = new Node(Integer.parseInt(node));
			if(first == null){
				first = newNode;
				last = newNode;
			}
			else{
				last.link = newNode;
				last = last.link;
			}
		}
		last.link = first;
		return last;		
	}

	static class Node {
		int data;
		Node link;

		Node(int data){
			this.data = data;
		}
	}
}
