package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Construct a complete binary tree from a list of items
 *  OR
 * Given the level order traversal of a binary tree, construct the tree 
 * 
 * http://www.geeksforgeeks.org/given-linked-list-representation-of-complete-tree-convert-it-to-linked-representation/
 * 
 * ================
 * METHOD 1 : Queue
 * ================
 * The first item on the list is the root node, so prepare it and enqueue it
 * If the queue is empty, quit
 * Dequeue a node
 * Get the next item on the list, make a node out of it, set it as the left child, enqueue it
 * Get another item on the list, make a node out of it, set it as the right child, enqueue it
 * Advance the list by 2
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Complete_Binary_Tree_From_List {

    public static void main(String...strings){
        int[] list = {1, 2, 4, 6, 7, 8, 9, 10, 12};			
        Node root = prepareCompleteTreeFromList(list);
        levelorderTraverse(root);
    }

    private static Node prepareCompleteTreeFromList(int[] list) {
        int numberOfNodes = list.length;
        Queue<Node> queue = new LinkedList<>();
        int i = 0;		
        Node root = new Node(list[i]);
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            if(i < numberOfNodes - 1)
                queue.add(node.left = new Node(list[i + 1]));
            if(i < numberOfNodes - 2)
                queue.add(node.right = new Node(list[i + 2]));
            i += 2;			
        }
        return root;
    }

    private static void levelorderTraverse(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data + " ");
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }

    private static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){			
            this.data = data;
        }
    }
}