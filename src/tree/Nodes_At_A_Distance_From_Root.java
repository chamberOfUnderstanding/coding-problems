package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 47un
 * 
 * Display all nodes at a distance of k from the root
 * 
 * http://www.geeksforgeeks.org/print-nodes-at-k-distance-from-root/
 * 
 * ================================
 * METHOD 1 : Level order traversal
 * ================================
 * Level order traverse the tree
 * Keep track of the level that is being traversed
 *  If this level is k, print the contents of the queue and quit
 *  Else resume LOT
 * If all levels have been scanned and nothing was printed, there are fewer than k levels
 * 
 * TIME     : O(n)
 * SPACE    : O(n)
 *
 * 
 */

public class Nodes_At_A_Distance_From_Root {

    public static void main(String...strings){
        Node node9 = new Node(9);
        Node node8 = new Node(8, node9);
        Node node7 = new Node(7, node8);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);

        int k = 2;
        System.out.print("Nodes at a distance of " + k + " from the root : ");
        findNodes(node1, k);
    }

    private static void findNodes(Node root, int k) {
        if(root != null){
            int level = -1;
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            while(!queue.isEmpty()){
                level++;
                int nodeCount = queue.size();
                if(level == k){
                    while(!queue.isEmpty())
                        System.out.print(queue.remove().data + " ");
                    break;
                }
                while(nodeCount-- > 0){
                    Node node = queue.remove();
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }
            }
            if(level != k)
                System.out.print("The tree has fewer than " + k + " levels");
        }		
    }

    @SuppressWarnings("unused")
    private static class Node{
        int data;
        Node left;
        Node right;

        Node(Node left, int data, Node right){          
            this.left = left;
            this.data = data;
            this.right = right;         
        }

        Node(Node left, int data){
            this.left = left;
            this.data = data;
        }

        Node(int data, Node right){
            this.data = data;
            this.right = right;
        }

        Node(int data){
            this.data = data;
        }
    }
}
