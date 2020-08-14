package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;

/**
 * @author 47un
 *
 * Implement Dijkstra's algorithm 
 * 
 * http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
 * http://www.geeksforgeeks.org/greedy-algorithms-set-7-dijkstras-algorithm-for-adjacency-list-representation/
 * http://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Use a priority queue
 *            The priority queue (min heap) will ensure that the edge with the smallest weight floats up
 *            Now enqueue the source vertex and distance 0
 *            Until the priority queue becomes empty
 *             Remove a vertex, distance pair from the queue, say vertex u
 *             If u has'nt been visited yet
 *               Scan all the edges of u
 *               For each un-visited adjacent vertex, v (vertex with which u makes an edge)
 *                If the distance to v from source (distance[v]) is > distance from source to u + weight of edge from u to v, then
 *                 update distance[v] and add this to the priority queue
 *                Also, mark v's parent as u. This is to record the shortest path
 *             Mark u as visited
 *             
 *             GREEDY
 *             WONT WORK FOR GRAPHS WITH NEGATIVE WEIGHT EDGES
 *
 * TIME     : O(E * logV) 
 *              // For each vertex, the edge with the least weight gets added to the priority queue
 *              // So we can expect v edges in the priority queue. Sink operation costs logV
 *              // At most there can be E edges in the priority queue :'(
 * SPACE    : O(V)        // for visited, distance, parent arrays 
 *
 * 
 */

public class Shortest_Path_Dijkstra {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int vertices     = 9;
        int sourceVertex = 0;
        List<Edge>[] adjacencyList = new List[vertices];
        String[] input = 
            {"0 1 4",
                    "1 2 8",
                    "1 7 11",
                    "2 3 7",
                    "2 8 2",
                    "2 5 4",
                    "3 4 9",
                    "3 5 14",
                    "4 5 10",
                    "5 6 2",
                    "6 8 6",
                    "6 7 1",
                    "7 8 7",
            "7 0 8"};
        for(String data : input) {
            String[] value = data.split(" "); 
            int from   = Integer.parseInt(value[0]);
            int to     = Integer.parseInt(value[1]);
            int weight = Integer.parseInt(value[2]);
            if(adjacencyList[from] == null)
                adjacencyList[from] = new ArrayList<>();
            if(adjacencyList[to] == null)
                adjacencyList[to] = new ArrayList<>();
            adjacencyList[from].add(new Edge(to, weight));
            adjacencyList[to].add(new Edge(from, weight));
        }
        shortestPathDijkstra(sourceVertex, vertices, adjacencyList);
    }

    private static void shortestPathDijkstra(int source, int vertices, List<Edge>[] adjList){
        boolean[] visited = new boolean[vertices];
        int[] distance    = new int[vertices];        
        int[] parent      = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] =  0;		
        parent[source]   = -1;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>((edge1, edge2) -> edge1.weight < edge2.weight? -1 : 1);				
        priorityQueue.add(new Edge(source, distance[source]));
        while(!priorityQueue.isEmpty()){
            Edge minWeightEdge = priorityQueue.remove();
            if(!visited[minWeightEdge.vertex]){
                ListIterator<Edge> edges = adjList[minWeightEdge.vertex].listIterator();
                while(edges.hasNext()){
                    Edge edge = edges.next();
                    if(!visited[edge.vertex]) {
                        int newDistance = distance[minWeightEdge.vertex] + edge.weight;
                        if(newDistance < distance[edge.vertex]) {
                            parent[edge.vertex] = minWeightEdge.vertex;   
                            priorityQueue.add(new Edge(edge.vertex, distance[edge.vertex] = newDistance));
                        }
                    }
                }
            }
            visited[minWeightEdge.vertex] = true;
        }
        System.out.format("%4s %4s %7s %4s", "From", "To", "Distance", "Path");        
        for(int vertex = 0; vertex < vertices; vertex++) {
            System.out.format("\n%4d %4d %7d ", source, vertex, distance[vertex]);
            printPath(vertex, parent);
        }
    }

    private static void printPath(int vertex, int[] parent) {
        if(parent[vertex] != -1) {            
            printPath(parent[vertex], parent);
            System.out.print(vertex + " ");
        }        
    }

    private static class Edge{
        int vertex;
        int weight;

        public Edge(int to, int weight) {
            this.vertex     = to;
            this.weight = weight;               
        }

        @Override
        public String toString() {
            return new String(this.vertex + " : " + this.weight);
        }
    }   
}
