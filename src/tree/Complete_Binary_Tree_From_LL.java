package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Convert a linked list to a complete binary tree
 *  
 * http://www.geeksforgeeks.org/linked-complete-binary-tree-its-creation/
 * 
 * ================
 * METHOD 1 : Queue
 * ================
 * Create a node out of the first item in the LL. This is the root of the tree
 * Enqueue the root
 * Scan the rest of the items
 *  Create a node out of this item
 *  Find a spot for this node in the tree
 *   Peek the queue for the last inserted node
 *    If this has no left child, add the new node as its left child
 *    If this has no right child, add the new node as its right child
 *    If this has both children, then it can no longer have kids, so dequeue it
 *   Enqueue the new node (during this step, it has no children)
 *   
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Complete_Binary_Tree_From_LL {

    public static void main(String...x){	
        int[] data = {1,2,3,4,5,6,7,8,9,10};
        Node root  = completeBinaryTreeFromLL(data);
        levelOrderTraversal(root);
    }

    public static Node completeBinaryTreeFromLL(int[] data) {
        Node root = new Node(data[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        for(int i = 1; i < data.length; i++) {
            Node newNode  = new Node(data[i]);
            Node lastInsertedNode = queue.peek();
            if(lastInsertedNode.left == null)
                lastInsertedNode.left = newNode;
            else if(lastInsertedNode.right == null)
                lastInsertedNode.right = newNode;
            if(lastInsertedNode.left != null && lastInsertedNode.right != null)
                queue.remove();
            queue.add(newNode);
        }
        return root;
    }

    private static void levelOrderTraversal(Node root){
        if(root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node current = queue.remove();			
            System.out.print(current.data + " ");
            if(current.left != null)
                queue.add(current.left);
            if(current.right != null)
                queue.add(current.right);
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