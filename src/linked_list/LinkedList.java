package linked_list;

import java.util.Scanner;

public class LinkedList {

	static Node first = null;

	class Node {
		int data;
		Node link;

		Node(int data){
			this.data = data;
		}
	}

	public static void main(String...x){
		try(Scanner scanner = new Scanner(System.in)){
			System.out.print("\nEnter size ");
			int size = scanner.nextInt();
			LinkedList linkedList = new LinkedList();
			System.out.println("\nEnter operations ");
			while(size-- > 0){
				int data = scanner.nextInt();
				int position = scanner.nextInt();
				linkedList.insertNode(data,position);
			}
			linkedList.displayList();
			moveLastElementToFront();
			linkedList.displayList();
		}
	}

	@SuppressWarnings("unused")
    private int lengthIterative(){
		int length = 0;
		Node temp = first;
		while(null != temp){
			length++;
			temp = temp.link;
		}
		return length;
	}

	@SuppressWarnings("unused")
	private int lengthRecursive(Node node){
		if(null == node)
			return 0;
		return 1 + lengthRecursive(node.link);
	}

	void displayList() {
		if(null == first)
			System.out.println("\nList is empty!");
		Node temp = first;
		while(null != temp){
			System.out.print(temp.data);
			temp = temp.link;
			if(null != temp)
				System.out.print(" -> ");
		}
	}

	/*Inserts a node at 'position'
	- 1 to insert at front, x to insert after x, 1 to insert at end*/
	void insertNode(int data,int position){
		Node newNode = new Node(data);
		if(null == first)
			first = newNode;
		else if(position == -1)
			insertInFront(data);
		else if(position == 1)
			insertAtEnd(data);
		else
			insertAfterNodeX(data,position);
	}

	private void insertInFront(int data){
		Node newNode = new Node(data);
		newNode.link = first;
		first = newNode;
	}

	private void insertAtEnd(int data){
		Node newNode = new Node(data);
		Node last = getLastNode();
		last.link = newNode;
	}

	private void insertAfterNodeX(int data,int x){
		Node newNode = new Node(data);
		Node nodeX = getNodeXIterative(x);
		if(null == nodeX){
			System.out.print("\n Node not found!");
			return;
		}
		Node temp = nodeX.link;
		nodeX.link = newNode;
		newNode.link = temp;
	}

	private void deleteFirstNode(){
		first = first.link;	
	}
	
	@SuppressWarnings("unused")
	private void deleteNodeX(int data){
		Node previousOfX = getPreviousOfNodeX(data);
		previousOfX.link = previousOfX.link.link;		
	}
	
	@SuppressWarnings("unused")
	private void deleteNodeAtX(int position){
		if(position == 0)
			deleteFirstNode();
		Node nodeAtX = getNodeAtX(position);
		if(null == nodeAtX){
			System.out.println("\nPosition out of bounds!");
			return;
		}
		Node previousOfNodeAtX = getPreviousOfNodeX(nodeAtX.data);
		Node nextOfNodeAtX = nodeAtX.link;
		previousOfNodeAtX.link = nextOfNodeAtX;
	}

	Node getPreviousOfNodeX(int data) {
		if(first.data == data)
			return null;
		Node previous = null;
		Node current = first;		
		while(null != current && current.data != data){
			previous = current;
			current = current.link;
		}			
		return previous;			
	}

	private Node getLastNode() {
		Node temp = first;
		if(null == first)
			return null;
		while(null != temp.link)
			temp = temp.link;
		return temp;
	}

	Node getNodeXIterative(int data){
		Node temp = first;
		while(null != temp.link && temp.data != data)
			temp = temp.link;
		return temp == null? null : temp;
	}

	boolean findNodeXWrapper(int data){
		return findNodeXRecursive(first, data);
	}

	private boolean findNodeXRecursive(Node node, int data){
		if(null == node)
			return false;
		return node.data == data || findNodeXRecursive(node.link, data);
	}	

	private Node getNodeAtX(int position){
		int count = 0;
		Node temp = first;
		while(null != temp && ++count != position)
			temp = temp.link;
		return temp == null? null : temp;
	}

	public boolean isFirst(int data) {
		if(null == first)
			return false;
		return first.data == data? true : false;
	}

	public boolean isLast(int data) {
		Node lastNode = getLastNode();
		if(null == lastNode)
			return false;
		return lastNode.data == data? true : false;
	}	

	public void deleteList(){
		first = null;
	}

	@SuppressWarnings("unused")
	private int numberOfOcurrences(int data){
		if(isEmpty())
			return -1;
		Node temp = first;
		int count = 0;
		while(null != temp){
			if(temp.data == data)
				count++;
			temp = temp.link;
		}
		return count;
	}

	private boolean isEmpty(){
		if(null == first){
			System.out.print("\n Empty list");
			return true;
		}
		return false;
	}

	public Node getFirst(){
		return first;
	}

	public void setFirst(Node node){
		first = node;
	}

	public void printReverse(Node node){
		if(node == null)
			return;
		printReverse(node.link);
		System.out.print(node.data+"->");		
	}

	public static void moveLastElementToFront(){
		if(first == null || first.link == null)
			return;
		Node current = first, previous = null;
		while(current.link != null){
			previous = current;
			current = current.link;
		}
		previous.link = null;
		current.link = first;
		first = current;
	}
}
