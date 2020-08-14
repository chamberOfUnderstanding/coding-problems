package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Serialization is to store tree in a file so that it can be later restored.
 * The structure of tree must be maintained.
 * Deserialization is reading tree back from file
 * 
 * http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 * 
 * ============================================
 * METHOD 1 : Preorder Traversal Serialization
 * ============================================
 * 
 * Append X if the node is null (Two Xs get added for a leaf node)
 * 1 2 4 5 3 6 7 8 after serialization becomes 1 2 4 X X 5 X X 3 6 X X 7 X 8 X X
 * 
 * Serialize
 * 	If the node is null, append X to the string and return (Termination clause)
 * 	Else append the node's data
 * 	Serialize the node's LST and RST
 *  
 * Deserialize
 * 	Maintain an index that's common to all recursive function calls (Hence static)(Or you can use dequeue that's shared between the methods as a parameter)
 * 	If the index is out of bounds or value at this index is X, return null (Termination clause)
 * 	Prepare a node with data = value at this index
 * 	Increment index
 * 	This node's left child is the result of recursively deserializing the LST
 * 	Increment index
 * 	This node's right child is the result of recursively deserializing the RST
 * 
 * ! Leaf nodes get two Xs, leading to a longer serialized string.
 *   Since the # leaves of a tree are 1 greater than number of internal nodes, these extra Xs can be removed
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * This method can be optimized to produce a shorter string as follows : 
 *  Append Y if the node is an internal node, append X if the node is null
 *  1 2 4 5 3 6 7 8 after serialization becomes 1 Y 2 Y 4 5 3 Y 6 7 Y X 8 (Shorter than above method's string)
 * 
 * ============================================
 * METHOD 2 : Level order Traversal Serialization
 * ============================================
 * 
 * Serialize
 *  Just a normal level order traversal
 *  If the dequeued node is not null
 *   Append the node's data to serialized string
 *   Enqueue children (Yes, even the NULL children)
 *  If the dequeued node is null
 *   Append end marker (# here) to the serialized string
 *  
 * Deserialize
 *  Need a queue for this to work. Load it with a node made out of the first value of the serialized string.
 *  Scan the serialized data until the data finishes or queue goes empty
 *   Since data at index 0 has already become the root, scanning starts with index 1
 *   Grab a node from the queue
 *    If the data at this index is not an end marker (#)
 *     Make a node out of the data at this index and set it as this node's left child.
 *     Enqueue the new born
 *    Advance the index
 *    If the data at index is not an end marker
 *     Make a node out of the data at this index and set it as this node's right child.
 *     Enqueue the new born
 *    Advance the index
 *  
 * TIME     : O(n)
 * SPACE    : O(n)
 * 
 */

public class Serialize_Deserialize {

    public static void main(String[] args) {
        Node node1 = prepareTree();

        System.out.println("Initial traversals");
        traverse(node1);

        System.out.println("\n\nSerializing using preorder traversal......");        
        StringBuilder serializedTreeV1 = new StringBuilder();
        serializePreorderTraversal(node1, serializedTreeV1);
        System.out.println("\nSerialized tree : " + serializedTreeV1);
        Node rootV1 = deserializePreorderTraversal(serializedTreeV1.toString().split(" "));
        System.out.println("Deserializing....");
        System.out.println("Traversals after deserialization : ");

        traverse(rootV1);

        System.out.println("\n\nSerializing using level order traversal.......");
        String serializedTreeLOT = serializeLevelOrderTraversal(node1);
        System.out.println("\nSerialized tree : " + serializedTreeLOT);
        Node rootV2 = deserializeLevelOrderTraversal(serializedTreeLOT.split(" "));
        System.out.println("Deserializing....");
        System.out.println("Traversals after deserialization : ");

        traverse(rootV2);
    }

    private static String serializeLevelOrderTraversal(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder levelOrderTraversal = new StringBuilder();
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            if(node != null) {
                levelOrderTraversal.append(node.data).append(" ");
                queue.add(node.left);
                queue.add(node.right);
            }
            else
                levelOrderTraversal.append("#").append(" ");
        }
        return levelOrderTraversal.deleteCharAt(levelOrderTraversal.length() - 1).toString();
    }

    private static Node deserializeLevelOrderTraversal(String[] split) {
        Node root = new Node(Integer.parseInt(split[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        for(int i = 1; i < split.length && !queue.isEmpty(); ) {
            Node node = queue.remove();
            if(!split[i].equalsIgnoreCase("#")) {
                node.left = new Node(Integer.parseInt(split[i]));
                queue.add(node.left);
            }
            i++;
            if(i != split.length && !split[i].equalsIgnoreCase("#")) {
                node.right = new Node(Integer.parseInt(split[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    private static void serializePreorderTraversal(Node node, StringBuilder serializedTreeV1) {
        if(node == null) {
            serializedTreeV1.append("X ");
            return;
        }
        serializedTreeV1.append(node.data).append(" ");
        serializePreorderTraversal(node.left, serializedTreeV1);
        serializePreorderTraversal(node.right, serializedTreeV1);        
    }

    static int indexV1 = 0;

    private static Node deserializePreorderTraversal(String[] data) {
        if(indexV1 >= data.length || data[indexV1].equals("X"))
            return null;
        Node newNode = new Node(Integer.parseInt(data[indexV1]));
        indexV1++;
        newNode.left  = deserializePreorderTraversal(data);
        indexV1++;
        newNode.right = deserializePreorderTraversal(data);
        return newNode;
    }

    private static void traverse(Node node1) {
        System.out.print("Inorder : ");
        inorderTraverse(node1);
        System.out.print("\nPreorder : ");
        preorderTraverse(node1);
        System.out.print("\nPostorder : ");
        postorderTraverse(node1);
    }

    private static void preorderTraverse(Node node) {
        if(node != null) {
            System.out.print(node.data + " ");
            preorderTraverse(node.left);
            preorderTraverse(node.right);
        }
    }

    private static void postorderTraverse(Node node) {
        if(node != null) {
            postorderTraverse(node.left);
            postorderTraverse(node.right);
            System.out.print(node.data + " ");
        }
    }

    private static void inorderTraverse(Node node) {
        if(node != null) {
            inorderTraverse(node.left);
            System.out.print(node.data + " ");
            inorderTraverse(node.right);
        }
    }

    private static Node prepareTree() {
        Node node9 = new Node(null, 9, null);
        Node node8 = new Node(node9, 8, null);
        Node node7 = new Node(null, 7, node8);
        Node node6 = new Node(null, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node4 = new Node(null, 4, null);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        return node1;
    }
    
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node(Node left, int data, Node right) {
            this.left = left;
            this.data = data;
            this.right = right;
        }

        @Override
        public String toString() {
            return "" + this.data + " " + (this.left == null? "null" : this.left.data) + " " + (this.right == null? "null" : this.right.data);
        }
    }
}