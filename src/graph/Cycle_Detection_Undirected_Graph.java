package graph;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * @author 47un
 *
 * Detect a cycle in an undirected graph
 * 
 * http://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 * http://www.geeksforgeeks.org/union-find/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Perform DFS
 *            Track the vertices that have been visited
 *            If a visited vertex is visited again, there is a cycle!
 *
 * TIME     : O(v + e)
 * SPACE    : O(2v)   // Stack, visited
 *
 * METHOD 2 : Union Find
 *            Record the parent vertices of each visited vertex
 *            If both vertices belong to the same set, find results in same parent value, there is a cycle
 *            Else union these vertices, set 'from' vertex's parent as the 'to' vertex's parent
 *  
 * TIME     : O(v)
 * SPACE    : O(v)
 *            
 */

public class Cycle_Detection_Undirected_Graph {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int vertices = 4;
        List<Integer>[] adjacencyList = new List[vertices];
        adjacencyList[0] = Arrays.asList(new Integer[]{1});
        adjacencyList[1] = Arrays.asList(new Integer[]{2});
        adjacencyList[2] = Arrays.asList(new Integer[]{3});
        adjacencyList[3] = Arrays.asList(new Integer[]{0});       
        int sourceVertex = 0;
        System.out.println("Undirected : " + detectCycleUndirected_DFS(adjacencyList, sourceVertex));
        System.out.println("Union Find : " + detectCycleUndirected_UnionFind(adjacencyList));
    }

    private static boolean detectCycleUndirected_DFS(List<Integer>[] adjacencyList, int sourceVertex) {
        boolean[] visited  = new boolean[adjacencyList.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(sourceVertex);     
        int currentVertex = sourceVertex;
        while(!stack.isEmpty()) {
            currentVertex = stack.pop();
            if(!visited[currentVertex]) {
                visited[currentVertex] = true;
                ListIterator<Integer> edges = adjacencyList[currentVertex].listIterator();
                while(edges.hasNext()) {
                    int destinationVertex = edges.next();
                    stack.push(destinationVertex);                   
                }
            }
            else
                return true;
        }
        return false;
    }

    private static boolean detectCycleUndirected_UnionFind(List<Integer>[] adjacencyList) {
        int[] parent = new int[adjacencyList.length];
        Arrays.fill(parent, -1);
        for(int vertex = 0; vertex < adjacencyList.length; vertex++) {
            ListIterator<Integer> edges = adjacencyList[vertex].listIterator();
            while(edges.hasNext()) {
                int from = find(vertex, parent);
                int to   = find(edges.next(), parent);
                if(from == to)
                    return true;
                union(parent, from, to);
            }
        }
        return false;
    }

    private static int find(int vertex, int[] parent) {
        return parent[vertex] == -1? vertex : find(parent[vertex], parent);
    }

    private static void union(int[] parent, int from, int to) {
        parent[from] = to;
    }
}
