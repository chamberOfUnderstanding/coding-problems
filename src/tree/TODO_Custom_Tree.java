package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TODO_Custom_Tree {

	static List<Node> forest = new LinkedList<>();
	static List<Node> nodes = new LinkedList<>();

	private static class Node{
		char data;
		Node left;
		Node right;

		Node(char data){
			this.data = data;
		}

		@Override
		public boolean equals(Object object){
			if(object == null || !(object instanceof Node) || ((Node)object).data != this.data)
				return false;
			return true;
		}
		
		@Override
		public int hashCode(){
			return this.data;
		}
	}

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			while(true){
				String input = scanner.nextLine();
				if(input.equals("quit"))
					break;
				String[] edge = input.split("--->");
				char from = edge[0].charAt(0);
				char to = edge[1].charAt(0);
				addEdgeToForest(new Node(from), new Node(to));			
			}	
		}		
	}

	//TODO 
	private static void addEdgeToForest(Node from, Node to) {
//		if()111
	}
}
