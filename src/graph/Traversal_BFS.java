package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

/**
 * @author 47un
 *
 * Perform BFS on a graph
 * 
 * http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : For each unvisited vertex
 *             Scan each edge and enqueue the destination vertex
 *             Mark this vertex as visited and add it to the bfs sequence
 *
 * TIME     : O(v + e)
 * SPACE    : O(v)
 *
 * 
 */

public class Traversal_BFS {

	@SuppressWarnings("unchecked")
    public static void main(String...strings){
		int vertices = 4;
		List<Integer>[] adjacencyList = new List[vertices];
		adjacencyList[0] = Arrays.asList(new Integer[]{1, 2});
        adjacencyList[1] = Arrays.asList(new Integer[]{2});
        adjacencyList[2] = Arrays.asList(new Integer[]{0, 3});
        adjacencyList[3] = Arrays.asList(new Integer[]{3}); 
        int sourceVertex = 2;
		System.out.println(breadthFirstSearch(adjacencyList, sourceVertex));
	}

	private static String breadthFirstSearch(List<Integer>[] adjacencyList, int sourceVertex) {
	    StringBuilder bfs    = new StringBuilder();
	    boolean[] visited    = new boolean[adjacencyList.length];
	    Queue<Integer> queue = new LinkedList<>();
	    queue.add(sourceVertex);	    
	    while(!queue.isEmpty()){
	        int currentVertex = queue.remove();
	        if(!visited[currentVertex]){
	            ListIterator<Integer> edges = adjacencyList[currentVertex].listIterator();
	            while(edges.hasNext())
	                queue.add(edges.next());
	            visited[currentVertex] = true;
	            bfs.append(currentVertex).append(",");
	        }
	    }
	    bfs.deleteCharAt(bfs.length() - 1);
	    return bfs.toString();
	}
}
