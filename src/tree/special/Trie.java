package tree.special;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 *
 * Implement a Trie
 * Similar questions : Implement a data structure for a Phone Book, "swipe to make words" games, FB name suggestions
 * 
 * http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
 * http://www.geeksforgeeks.org/trie-insert-&-search/
 * 
 * =========
 * METHOD 1
 * ===========
 * Trie Node with data part & a map that holds the children 
 * Insertion : Check if the current node (initially the root) contains the char to be inserted as its child
 *             If yes, fetch that node from the children map & make that node the current node. Repeat these steps for the new node
 *             If no, add the new node as a child to this node, update current node to new node
 *                          
 * Search    : Check if the current node (initially the root) contains the char to be searched as its child
 *             If no, then the word is not present in the Trie (duh !!), so leave!
 *             If yes, fetch the node from the children map, update the current node to fetched node & repeat the above steps
 *                          
 * Delete    : Scan the string
 *             If any character is not found, quit (The string is not present in the trie)
 *             If the current node creates a fork (has > 1 child) then record this node & the current character
 *                Go down the trie by updating the current node with its child's value
 *                MUST NOT break out right after a fork is encountered because the string may not be present at all in the trie
 *             Once the string is scanned, check if the current node is a leaf
 *                If not the string is not present in the trie
 *             Check if the current node (last node of the branch containing the string to be deleted) has any children
 *                If the string to be del'd is a prefix of another string, deletion is done by making the node a non leaf node (isLeaf = false)
 *                Else remove the recorded character from the children set of the last fork
 *                        
 * Count Words : If the node is a leaf, return 1
 *               Else get the children of this node
 *               For each child, call count words & add the result to count
 *               Return count
 *                        
 * Print Trie  : If the node is a leaf, print it
 *               Else append this node's data to the string
 *               For each child, call print leaf for the updated string
 *            
 * TIME     : If n is the size of the word in consideration,
 *            Insertion : O(n) // HashMap.get(), put() are O(1) operations as the values get put into buckets
 *            Deletion  : O(n)
 *            Search    : O(n)
 *            
 * SPACE    : O(1) (Auxiliary) // Well, to store m words of size n each, it takes O(mn), but that part is obvious
 *
 * 
 */

public class Trie {

    private static Node root = new Node();

    private static class Node{
        char data;
        Map<Character, Node> children;
        boolean isLeaf;

        Node(){
            children = new HashMap<>();  
        }

        Node(char data){
            this.data = data;
            children = new HashMap<>();
        }
    }

    public static void main(String...strings){
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("world");
        trie.insert("help");
        trie.insert("helios");
        trie.insert("helena bonham carter");
        printTrie(root, "");

        trie.search("helena bonham carter");
        trie.insert("blaziken");
        trie.search("hello");
        trie.search("helios");
        trie.search("charizard");
        trie.delete("hello");
        trie.delete("monolith");
        printTrie(root, "");

        trie.search("hello");
        trie.search("helios");
        trie.search("blaziken");
        trie.search("helena bonham carter");
        trie.insert("hell");
        trie.insert("hello");        
        printTrie(root, "");

        trie.search("hell");
        trie.delete("hell");
        printTrie(root, "");

        trie.insert("xernes");
        trie.delete("xer");
        printTrie(root, "");

        trie.delete("xernes");
        trie.delete("yvetel");
        printTrie(root, "");
        System.out.println(trie.countWords(root));
    }

    private void insert(String string) {
        Node currentNode = root;
        for(int i = 0; i < string.length(); i++) {            
            char characterToBeInserted = string.charAt(i);
            if(currentNode.children.containsKey(characterToBeInserted))
                currentNode = currentNode.children.get(characterToBeInserted);
            else {
                Node newNode = new Node(characterToBeInserted);
                currentNode.children.put(characterToBeInserted, newNode);
                currentNode = newNode;
            }
        }
        currentNode.isLeaf = true;
        System.out.println("INSERTED : " + string);
    }

    private void search(String string) {
        Node currentNode = root;
        for(int i = 0; i < string.length(); i++) {
            char characterToBeSearched = string.charAt(i);
            if(!currentNode.children.containsKey(characterToBeSearched))
                break;
            currentNode = currentNode.children.get(characterToBeSearched);
        }
        System.out.println((currentNode != null && currentNode.isLeaf? "FOUND : " : "FOUND NOT : ") + string);
    }

    private void delete(String string) {
        Node currentNode = root;
        Node forkInTheTree = null;
        char childOfForkToBeRemoved = 0;
        for(int i = 0; i < string.length(); i++) {
            char characterToBeDeleted = string.charAt(i);
            if(!currentNode.children.containsKey(characterToBeDeleted)) {
                System.out.println("FOUND NOT : " + string);
                return;
            }
            if(currentNode.children.size() > 1) {
                forkInTheTree = currentNode;
                childOfForkToBeRemoved = characterToBeDeleted;                 
            }
            currentNode = currentNode.children.get(characterToBeDeleted);
        }
        if(!currentNode.isLeaf) {
            System.out.println("FOUND NOT : " + string);
            return;
        }
        if(!currentNode.children.isEmpty())
            currentNode.isLeaf = false;
        else
            forkInTheTree.children.remove(childOfForkToBeRemoved);            
        System.out.println("DELETED : " + string);        
    }

    private int countWords(Node node) {
        if(node.isLeaf)
            return 1;
        int count = 0;
        for(Character keyToAChild : node.children.keySet())
            count += countWords(node.children.get(keyToAChild));
        return count;
    }

    private static void printTrie(Node node, String word) {
        if(node.isLeaf)
            System.out.println(word + node.data);
        word += node.data;
        for(Character keyToAChild : node.children.keySet())
            printTrie(node.children.get(keyToAChild), word);
    }
}
