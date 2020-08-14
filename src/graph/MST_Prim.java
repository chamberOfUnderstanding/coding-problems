package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;

/**
 * @author 47un
 *
 * Implement Prim's MST algorithm
 * 
 * http://www.geeksforgeeks.org/prims-algorithm-using-priority_queue-stl/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Priority Queue
 *            Track vertices that have been added to mst, weight at each vertex and parent of each vertex
 *            Initialize all weights to infinity (all but start)
 *            Maintain a priority queue that picks the least weighted edge originating from the current vertex
 *            Enqueue the first vertex (0 / source)
 *            As long as there are edges to scan
 *              Extract min from priority queue (destination vertex with least weighted edge) // initially it is just vertex 0 => u
 *              If u is not in mst
 *                  Scan all edges (v, w) originating from u, where v is the adjacent vertex and w is the weight (u, v is the edge)
 *                  If w < weight[v], update weight and enqueue (v, w)
 *                  Make u the parent of v
 *                  Add u to mst
 *            parent[] holds the mst
 *
 * TIME     : O(ElogV)
 * SPACE    : O(V)
 *
 * 
 */

@SuppressWarnings("unchecked")
public class MST_Prim {
    public static void main(String[] args) {
        int vertices     = 9;
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
        int[] minimumSpanningTree = mstPrim(adjacencyList, vertices);
        int i = 0;
        for(int edge : minimumSpanningTree)
            System.out.println(i++ + " " + edge + " ");
    }

    private static int[] mstPrim(List<Edge>[] adjacencyList, int vertices) {
        boolean[] inMST = new boolean[vertices];
        int[] weight    = new int[vertices];        
        int[] parent    = new int[vertices];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[0] =  0;      
        parent[0] = -1;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>((edge1, edge2) -> edge1.weight < edge2.weight? -1 : 1);             
        priorityQueue.add(new Edge(0, weight[0]));

        while(!priorityQueue.isEmpty()){
            Edge minWeightEdge = priorityQueue.remove();
            if(!inMST[minWeightEdge.to]){
                ListIterator<Edge> edges = adjacencyList[minWeightEdge.to].listIterator();
                while(edges.hasNext()){
                    Edge edge = edges.next();
                    if(!inMST[edge.to] && edge.weight < weight[edge.to]) {
                        priorityQueue.add(new Edge(edge.to, weight[edge.to] = edge.weight));
                        parent[edge.to] = minWeightEdge.to;   
                    }
                }
                inMST[minWeightEdge.to] = true;
            }
        }
        return parent;
    }

    private static class Edge{
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;               
        }

        @Override
        public String toString() {
            return new String(this.to + " : " + this.weight);
        }
    } 
}
