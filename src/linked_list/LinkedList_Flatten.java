package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Given a linked list where every node represents a linked list and contains two pointers of its type:
 * (i) Pointer to next node in the main list (we call it ‘right’ pointer in below code)
 * (ii) Pointer to a linked list where this node is head (we call it ‘down’ pointer in below code).
 * 
 * Flatten it and sort it!
 * 
 * All linked lists are sorted. See the following example
 * 
 *        5 -> 10 -> 19 -> 28
 *        |    |     |     |
 *        7    20    22    35
 *        |          |     |
 *        8          50    40
 *        |                |
 *        30               45
 *
 * Output : 5->7->8->10->19->20->22->28->30->30->40->45->50
 * 
 * http://www.geeksforgeeks.org/flattening-a-linked-list/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Flatten last two lists by merging them
 *            Attach the merged list as the right of the third last list
 *            Repeat till you hit the start
 *            
 * TIME     : O(n*m)  // If n = length of horizontal list and m is the cost of the merge procedure 
 * SPACE    : O(1)
 * 
 */

public class LinkedList_Flatten{

	static Nodev2 root = null;
	
	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			prepareTestCase();
			root = flatten(root);
			displayList();
		}
	}

	private static Nodev2 flatten(Nodev2 node) {
		if(node == null || node.right == null)
			return node;
		node.right = flatten(node.right);
		node = merge(node, node.right);
		return node;
	}

	private static Nodev2 merge(Nodev2 left, Nodev2 right) {
        if(left == null)
        	return right;
        if(right == null)
        	return left;
        Nodev2 node = null;
        if(left.data < right.data){
        	node = left;
        	node.down = merge(left.down, right);
        }
        else{
        	node = right;
        	node.down = merge(left, right.down);
        }
		return node;
	}

	private static void prepareTestCase() {
		Nodev2 n30 = new Nodev2(30,null,null);
		Nodev2 n20 = new Nodev2(20,null,null);
		Nodev2 n50 = new Nodev2(50,null,null);
		Nodev2 n45 = new Nodev2(45,null,null);
		Nodev2 n40 = new Nodev2(40,null,n45);
		Nodev2 n35 = new Nodev2(30,null,n40);
		Nodev2 n28 = new Nodev2(28,null,n35);
		Nodev2 n22 = new Nodev2(22,null,n50);
		Nodev2 n19 = new Nodev2(19,n28,n22);
		Nodev2 n10 = new Nodev2(10,n19,n20);
		Nodev2 n8 = new Nodev2(8,null,n30);
		Nodev2 n7 = new Nodev2(7,null,n8);
		Nodev2 n5 = new Nodev2(5,n10,n7);				
		
		root = n5;
	}
	
	static class Nodev2{
		int data;
		Nodev2 right;
		Nodev2 down;
		
		Nodev2(int data, Nodev2 right, Nodev2 down){
			this.data = data;
			this.right = right;
			this.down = down;
		}
	}
	
	static void displayList() {
		Nodev2 current = root;
		while(null != current){
			System.out.print(current.data);
			current = current.down;
			if(null != current)
				System.out.print("->");
		}			
	}
}
