package graph;

import java.util.BitSet;
import java.util.Stack;

/**
 * @author 47un
 *
 * Given an array of strings, find if the given strings can be chained to form a circle.
 * A string X can be put before another string Y in circle if the last character of X is same as first character of Y.
 * 
 * http://www.geeksforgeeks.org/find-array-strings-can-chained-form-circle-set-2/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : DFS, degrees
 *            Make a graph out of the starting and ending characters of all words
 *            So for the word, geek, an edge will be present in the graph from 'g' to 'k'. Hence at most there can be 26 vertices
 *            Prepare the graph, calculate in degree and out degree of each vertex
 *            Also count the number of vertices // BitSet.cardinality()
 *            Check if the in degree and out degree of each vertex is the same
 *            Check if the graph is connected.
 *              For this perform DFS, if ALL vertices are visited after DFS, then the graph is connected
 *            If last two checks are successful, return true
 *
 * TIME     : O(w) // Most of the operations are of constant time (worst case, all 26 vertices are present)
 * SPACE    : O(1)
 *
 * 
 */

public class Circle_Of_Words {

    public static void main(String[] args) {
        String[] words = {"for", "geek", "rig", "kaf"};
        System.out.println(isCirclable(words)? "Yes" : "No");
    }

    private static boolean isCirclable(String[] words) {
        boolean[][] graph = new boolean[26][26];
        int[] inDegree    = new int[26];
        int[] outDegree   = new int[26];
        BitSet vertices   = new BitSet(26);
        for(String word : words) {
            int firstCharacter = word.charAt(0) - 97;
            int lastCharacter  = word.charAt(word.length() - 1) - 97;
            inDegree[firstCharacter]++;
            outDegree[lastCharacter]++;
            graph[firstCharacter][lastCharacter] = true;
            vertices.set(firstCharacter);
            vertices.set(lastCharacter);
        }        
        return areDegreesEqual(inDegree, outDegree) && isConnected(words[0].charAt(0) - 97, vertices.cardinality(), graph);
    }

    private static boolean isConnected(int sourceVertex, int vertices, boolean[][] graph) {
        boolean[] visited    = new boolean[26];
        Stack<Integer> stack = new Stack<>();
        stack.push(sourceVertex);
        while(!stack.isEmpty()) {
            int vertex = stack.pop();
            if(!visited[vertex]) {
                for(int adjVertex = 0; adjVertex < 26; adjVertex++)
                    if(graph[vertex][adjVertex])
                        stack.push(adjVertex);
            }
            visited[vertex] = true;
        }
        for(boolean visitedVertex : visited)
            if(visitedVertex)
                vertices--;
        return vertices == 0;
    }

    private static boolean areDegreesEqual(int[] inDegree, int[] outDegree) {
        for(int vertex = 0; vertex < 26; vertex++)
            if(inDegree[vertex] != outDegree[vertex])
                return false;
        return true;
    }
}
