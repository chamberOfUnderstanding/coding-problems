package graph;

/**
 * @author 47un
 *
 * Find the shortest path between all pairs of vertices in a graph
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Dynamic Programming
 *            Initialize distance[][] with the same values as the weights in the given graph
 *            For each vertex pair i, j check if there is a shortest path through k
 *             If yes, update the path with the new distance
 *            
 *            ! Now if infinity is chosen as Integer.MAX_VALUE then add a condition before updating the distance
 *              that ignores the vertex pair if either is INF. Or else it will lead to an arithmetic overflow!
 *              
 *            ! Dijkstra's algorithm gives O(ElogV) so if we run it for all the vertices, it will take O(VElogV)
 *              This can go up to O(V^3logV) if the graph is complete (n(n-1)/2) edges
 *              Hence the dp approach is better than Dijkstra's for all vertices
 *
 * TIME     : O(V^3)
 * SPACE    : O(1)
 *
 * 
 */

public class All_Pairs_Shortest_Path_Floyd_Warshall {

    final static int INF = 9999999;

    public static void main(String[] args) {
        int vertices  = 4;
        int[][] graph = 
            { {0,   5,  INF, 10},
              {INF, 0,   3, INF},
              {INF, INF, 0,   1},
              {INF, INF, INF, 0}};
        allPairsShortestPath(graph, vertices);       
    }

    private static void allPairsShortestPath(int[][] graph, int vertices) {
        int[][] distance = new int[vertices][vertices];
        for(int i = 0; i < vertices; i++)
            System.arraycopy(graph[i], 0, distance[i], 0, vertices);
        for(int i = 0; i < vertices; i++)
            for(int j = 0; j < vertices; j++)
                for(int k = 0; k < vertices; k++)
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
        printAllPairsShortestPath(distance);
    }

    private static void printAllPairsShortestPath(int[][] distance) {
        for(int[] i : distance) {
            for(int j : i)
                System.out.format("%5s", j == INF? "INF" : j);
            System.out.println();
        }
    }
}
