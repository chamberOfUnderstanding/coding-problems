package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 47un
 * 
 * Get the distance between two nodes of a binary tree
 *  
 * http://www.geeksforgeeks.org/find-distance-two-given-nodes/
 * 
 * ================
 * METHOD 1 : LCA 
 * ================
 * Find LCA of the nodes
 * Find intermediate nodes between LCA and node1 + intermediate nodes between LCA and node2
 *  If node is null, return 0 (It wont add to the intermediate nodes)
 *  If node has the data, return nodeCount (# nodes scanned so far. Shared between methods as parameter)
 *  Else recurse for that child after increasing nodeCount by 1
 *  
 * + No need to form path lists
 *
 * TIME     : O(n)  // Finding LCA, finding distance from LCA to node1, finding distance to LCA to node2
 * SPACE    : O(n)  // Stack space
 * 
 * =======================
 * METHOD 2 : Using Paths
 * =======================
 * Scan the tree and find the path from root the key1 and path from root to key2
 *  The findPaths() is an extension of the Root_To_Key_Path.findPath()
 *  Here the paths to both nodes are recorded in one scan of the tree
 *  The method takes in a node (the current one getting scanned), the two keys to be searched for, two flags that are hoisted if either data is found,
 *   two lists to record the paths and returns a boolean[] which shows the found status of key1 and key2.
 *   i.e. a return value of {true, true} means key1 was found and key2 was found
 *  If the node is null, then neither data was found, so return {false, false}
 *  If the node = key1 and key2 has not been found so far (means we gotta go deeper)
 *    Scan LST and RST, and update the foundKey2 with the OR of the boolean[1] values returned   
 *    Also for the above calls, since key1 was found and key2 was not found, set the values of the flag arguments as true, false
 *    Now return {true, foundKey2}. First value is true, as the key1 was found (this node), second value is the result of the above recursive calls
 *  Else if the node = key2 and key1 has not been found so far (again we gotta go deeper)
 *    Do the same as above but for boolean[0], foundKey1 and set the arguments as false, true
 *  Else recurse the LST and RST, get the search results
 *  If either search results are true for key1, add this node to path1 and set status as true for key1
 *  If either search results are true for key2, add this node to path2 and set status as true for key2
 *  Return the search status  
 * Scan the paths until an uncommon node is detected. If 'i' nodes exist before the uncommon node, there are i nodes in common.
 * Since the loop breaks if an uncommon node is detected, the last common node was 1 node before this one, so subtract 1 from the i
 * Sum the path lengths and subtract the common part (2*i - 1)
 * 
 * TIME     : O(n)  // Finding the paths, seeking out the common nodes
 * SPACE    : O(n)  // Stack space, Path lists
 * 
 *
 */

public class Distance_Between_Nodes {

    public static void main(String...strings){
        Node node13 = new Node(null, 13, null);
        Node node12 = new Node(null, 12, node13);
        Node node11 = new Node(node12, 11, null);
        Node node10 = new Node(node11, 10, null);
        Node node6 = new Node(node10, 6, null);
        Node node5 = new Node(null, 5, null);
        Node node4 = new Node(null, 4, null);
        Node node9 = new Node(null, 9, null);
        Node node8 = new Node(null, 8, node9); 
        Node node7 = new Node(null, 7, node8);
        Node node3 = new Node(node6, 3, node7);
        Node node2 = new Node(node4, 2, node5);
        Node node1 = new Node(node2, 1, node3);
        
        System.out.print("Using LCA Method : ");
        findDistanceUsingLCA(node1, 9, 13);
        System.out.print("Using paths Method : ");
        findDistanceUsingPaths(node1, 9, 13);
    }

    private static void findDistanceUsingLCA(Node node, int key1, int key2) {
        Node lca = findLCA(node, key1, key2);
        System.out.print("Distance between " + key1 + " " + key2 + " is ");
        System.out.println(intermediateNodeCount(lca, key1, 0) + intermediateNodeCount(lca, key2, 0) - 1);
    }
    
    private static Node findLCA(Node node, int key1, int key2) {
        if(node == null)
            return null;
        if(node.data == key1 || node.data == key2)
            return node;
        Node lcaLST = findLCA(node.left, key1, key2);
        Node lcaRST = findLCA(node.right, key1, key2);
        if(lcaLST != null && lcaRST != null)
            return node;
        return lcaLST == null? lcaRST : lcaLST;
    }
    
    private static int intermediateNodeCount(Node node, int key, int nodeCount) {
        if(node == null)
            return 0;
        if(node.data == key)
            return nodeCount;
        return  intermediateNodeCount(node.left,  key, nodeCount + 1) +
                intermediateNodeCount(node.right, key, nodeCount + 1);
    }
    
    private static void findDistanceUsingPaths(Node root, int key1, int key2) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();
        findPaths(root, key1, key2, false, false, path1, path2);
        Collections.reverse(path1);
        Collections.reverse(path2);
        int i;
        for(i = 0; i < path1.size() && i < path2.size() && path1.get(i) == path2.get(i); i++);
        i--;
        System.out.println("Distance between " + key1 + " " + key2 + " is " + (path1.size() + path2.size() - 2*i - 1));
    }

    private static boolean[] findPaths(Node node, int key1, int key2, boolean foundKey1, boolean foundKey2, List<Integer> path1, List<Integer> path2) {
        if(node == null)
            return new boolean[] {false, false};
        if(node.data == key1) {
            if(!foundKey2)
                foundKey2 = findPaths(node.left,  key1, key2, true, false, path1, path2)[1] || findPaths(node.right, key1, key2, true, false, path1, path2)[1];
            return new boolean[] {true, foundKey2};
        }
        else if(node.data == key2) {
            if(!foundKey1)
                foundKey1 = findPaths(node.left,  key1, key2, false, true, path1, path2)[0] || findPaths(node.right, key1, key2, false, true, path1, path2)[0];
            return new boolean[] {foundKey1, true};
        }
        boolean[] searchLST = findPaths(node.left,  key1, key2, foundKey1, foundKey2, path1, path2); 
        boolean[] searchRST = findPaths(node.right, key1, key2, foundKey1, foundKey2, path1, path2);
        boolean[] searchStatus = new boolean[2];
        if(searchLST[0] || searchRST[0]) {
            path1.add(node.data);
            searchStatus[0] = true;
        }
        if(searchLST[1] || searchRST[1]) {
            path2.add(node.data);
            searchStatus[1] = true;
        }
        return searchStatus;
    }

    private static class Node{
        int data;
        Node left;
        Node right;

        Node(Node left, int data, Node right){            
            this.left = left;
            this.data = data;
            this.right = right;         
        }
    }
}
