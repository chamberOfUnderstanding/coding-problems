package graph;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * @author 47un
 *
 * Perform DFS on a graph
 * 
 * ==========
 * REFERENCE    
 * ============
 * http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : For each unvisited vertex
 *             Scan each edge and stack the destination vertex
 *             Mark this vertex as visited and add it to the dfs sequence
 *
 * TIME     : O(v + e)
 * SPACE    : O(v)
 *
 * 
 */

public class Traversal_DFS {
    
    @SuppressWarnings("unchecked")
    public static void main(String...strings){
        int vertices = 4;
        List<Integer>[] adjacencyList = new List[vertices];
        adjacencyList[0] = Arrays.asList(new Integer[]{1, 2});
        adjacencyList[1] = Arrays.asList(new Integer[]{2});
        adjacencyList[2] = Arrays.asList(new Integer[]{0, 3});
        adjacencyList[3] = Arrays.asList(new Integer[]{3}); 
        int sourceVertex = 2;
        System.out.println(depthFirstSearch(adjacencyList, sourceVertex));
    }
    
    private static String depthFirstSearch(List<Integer>[] adjacencyList, int sourceVertex) {
        StringBuilder dfs    = new StringBuilder();
        boolean[] visited    = new boolean[adjacencyList.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(sourceVertex);
        while(!stack.isEmpty()){
            int currentVertex = stack.pop();
            if(!visited[currentVertex]){
                ListIterator<Integer> edges = adjacencyList[currentVertex].listIterator();
                while(edges.hasNext())
                    stack.push(edges.next());
                visited[currentVertex] = true;
                dfs.append(currentVertex).append(",");
            }
        }
        dfs.deleteCharAt(dfs.length() - 1);
        return dfs.toString();       
    }
}
