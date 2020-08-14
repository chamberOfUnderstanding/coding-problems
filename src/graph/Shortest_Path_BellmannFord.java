package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @author 47un
 *
 * Implement Bellmann Ford algorithm
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Relax every edge!
 *            Compared to Dijkstra's performance sucks
 *            Good for distributed systems
 *            Simple implementation
 *            Works for -ve weight graphs
 *            
 *            DYNAMIC PROGRAMMING
 *            
 * TIME     : O(VE)
 * SPACE    : O(V)
 *
 * 
 */

public class Shortest_Path_BellmannFord {
    
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
        shortestPathBellmannFord(sourceVertex, vertices, adjacencyList);
    }

    private static void shortestPathBellmannFord(int source, int vertices, List<Edge>[] adjList){
        boolean[] visited = new boolean[vertices];
        int[] distance    = new int[vertices];        
        int[] parent      = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] =  0;      
        parent[source]   = -1;
        for(int vertex = 0; vertex < vertices; vertex++) {
            if(!visited[vertex]){
                ListIterator<Edge> edges = adjList[vertex].listIterator();
                while(edges.hasNext()){
                    Edge edge = edges.next();
                    if(!visited[edge.vertex]) {
                        int newDistance = distance[vertex] + edge.weight;
                        if(newDistance < distance[edge.vertex]) {
                            parent[edge.vertex] = vertex;   
                            distance[edge.vertex] = newDistance;
                        }
                    }
                }
            }
            visited[vertex] = true;
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
