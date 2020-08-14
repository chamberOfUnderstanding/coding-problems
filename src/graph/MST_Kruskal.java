package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 47un
 *
 * Implement Kruskal's MST algo
 * 
 * http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
 * 
 * =========
 * METHOD 1
 * ===========
 * For each edge
 * Add it to the mst, check if it forms a cycle, if so remove it *            
 * Notice how the adjacency list here is not a list array, instead just a list
 *            
 * TIME     : O(v * e)
 * SPACE    : O(v)
 *
 * 
 */

public class MST_Kruskal {

    public static void main(String[] args) {
        int vertices = 9;
        List<Edge> adjList = new ArrayList<>();
        adjList.add(new Edge(0, 1, 4));
        adjList.add(new Edge(1, 2, 8));
        adjList.add(new Edge(2, 3, 7));
        adjList.add(new Edge(3, 4, 9));
        adjList.add(new Edge(4, 5, 10));
        adjList.add(new Edge(5, 3, 14));
        adjList.add(new Edge(5, 6, 2));
        adjList.add(new Edge(6, 8, 6));
        adjList.add(new Edge(8, 2, 2));
        adjList.add(new Edge(6, 7, 1));
        adjList.add(new Edge(7, 1, 11));
        adjList.add(new Edge(7, 0, 8));
        adjList.add(new Edge(7, 8, 7));
        adjList.add(new Edge(2, 5, 4));
        List<Edge> minimumSpanningTree = mstKruskal(adjList, vertices);
        System.out.println(minimumSpanningTree);        
    }
    
    private static List<Edge> mstKruskal(List<Edge> adjList, int vertices){
        List<Edge> mst = new ArrayList<>();
        Collections.sort(adjList, (edge1, edge2) -> (edge1.weight > edge2.weight)? 1 : -1);
        for(Edge edge : adjList) {
            mst.add(edge);
            if(detectCycleUnionFind(mst, vertices))
                mst.remove(edge);   
        }
        return mst;
    }

    private static boolean detectCycleUnionFind(List<Edge> adjList, int vertices) {
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);
        for(Edge edge : adjList) {
            int parentOfFrom = find(edge.from, parent);
            int parentOfTo   = find(edge.to, parent);
            if(parentOfFrom == parentOfTo)
                return true;
            union(parentOfFrom, parentOfTo, parent);
        }
        return false;
    }

    private static int find(int vertex, int[] parent) {
        return parent[vertex] == -1? vertex : find(parent[vertex], parent);
    }

    private static void union(int fromVertex, int toVertex, int[] parent) {
        parent[fromVertex] = toVertex;
    }

    private static class Edge{
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;               
        }

        @Override
        public String toString() {
            return new String("(" + this.from + "," + this.to + ") : " + this.weight);
        }
    }   
}
