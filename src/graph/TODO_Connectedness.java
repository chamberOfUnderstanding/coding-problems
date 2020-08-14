package graph;

import java.util.Stack;

/**
 * @author 47un
 *
 * Check if a graph is connected
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : 
 *
 * TIME     : O()
 * SPACE    : O()
 *
 * 
 */

public class TODO_Connectedness {
    
    public static void main(String[] args) {
        
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
}
