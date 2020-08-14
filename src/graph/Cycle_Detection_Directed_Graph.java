package graph;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * @author 47un
 *
 * Detect a cycle in a directed graph
 * 
 * http://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : The technique used in undirected graphs wont work here, coz :
 *              If an vertex is visited more than once, it could be just another way of reaching that vertex
 *              e.g. A-->B, B-->C, A-->C doesn't have a friggin cycle, but the undirected algo will report that it has one!
 *              But if a vertex that's on the stack is visited again, it's a problem!
 *
 * TIME     : O(v + e)
 * SPACE    : O(v)  // Stack, visited, stacked
 *
 * 
 */

public class Cycle_Detection_Directed_Graph {
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int vertices = 4;
        List<Integer>[] adjacencyList = new List[vertices];
        adjacencyList[0] = Arrays.asList(new Integer[]{1});
        adjacencyList[1] = Arrays.asList(new Integer[]{2});
        adjacencyList[2] = Arrays.asList(new Integer[]{3});
        adjacencyList[3] = Arrays.asList(new Integer[]{0});       
        int sourceVertex = 0;
        System.out.println("Directed : " + detectCycleDirected(adjacencyList, sourceVertex));
    }

    private static boolean detectCycleDirected(List<Integer>[] adjacencyList, int sourceVertex) {
        boolean[] visited  = new boolean[adjacencyList.length];
        boolean[] stacked  = new boolean[adjacencyList.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(sourceVertex);
        stacked[sourceVertex] = true;
        int currentVertex = sourceVertex;
        while(!stack.isEmpty()) {
            currentVertex = stack.pop();
            stacked[currentVertex] = false;
            if(!visited[currentVertex]) {
                visited[currentVertex] = true;
                ListIterator<Integer> edges = adjacencyList[currentVertex].listIterator();
                while(edges.hasNext()) {
                    int next = edges.next();
                    stack.push(next);
                    stacked[next] = true;
                }
            }
            else if(stacked[currentVertex])
                return true;
        }
        return false;
    }
}
