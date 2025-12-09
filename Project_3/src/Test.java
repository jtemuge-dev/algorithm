import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class MainTest {

    static class Edge {
        int to, weight;
        Edge(int to, int weight) { this.to = to; this.weight = weight; }
    }

    static class Graph {
        int V;
        List<Edge>[] adj;
        String[] labels;
        double[][] pos;

        @SuppressWarnings("unchecked")
        Graph(int V, String[] labels) {
            this.V = V;
            this.labels = labels;
            adj = new ArrayList[V];
            pos = new double[V][2];
            for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        }

        void addEdge(int u, int v, int w) {
            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));
        }

        void setPos(int node, double x, double y) {
            pos[node][0] = x;
            pos[node][1] = y;
        }
    }

    int[] dijkstra(Graph g, int src, int[] outDist) {
        int[] dist = new int[g.V];
        int[] prev = new int[g.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[src] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dist[i]));
        pq.add(src);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            for (Edge e : g.adj[u]) {
                if (dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    prev[e.to] = u;
                    pq.add(e.to);
                }
            }
        }

        System.arraycopy(dist, 0, outDist, 0, g.V);
        return prev;
    }

    Graph createTestGraph() {
        String[] labels = {"A", "B", "C", "D", "E", "F"};
        Graph g = new Graph(6, labels);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 2);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 8);
        g.addEdge(2, 4, 10);
        g.addEdge(3, 4, 2);
        g.addEdge(3, 5, 6);
        g.addEdge(4, 5, 3);

        return g;
    }

    @Test
    void testDijkstraDistances() {
        Graph g = createTestGraph();
        int[] dist = new int[g.V];
        int[] prev = dijkstra(g, 0, dist);  // start A (0)

        assertEquals(0, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(2, dist[2]);
        assertEquals(8, dist[3]);
        assertEquals(10, dist[4]);
        assertEquals(13, dist[5]);
    }

    @Test
    void testDijkstraPredecessors() {
        Graph g = createTestGraph();
        int[] dist = new int[g.V];
        int[] prev = dijkstra(g, 0, dist);

        assertEquals(-1, prev[0]);
        assertEquals(2, prev[1]);
        assertEquals(0, prev[2]);
        assertEquals(1, prev[3]);
        assertEquals(3, prev[4]);
        assertEquals(4, prev[5]);
    }

    @Test
    void testNoPath() {
        Graph g = new Graph(2, new String[]{"A", "B"});
        int[] dist = new int[g.V];
        int[] prev = dijkstra(g, 0, dist);

        assertEquals(Integer.MAX_VALUE, dist[1]);
        assertEquals(-1, prev[1]);
    }

    @Test
    void testSelfDistance() {
        Graph g = createTestGraph();
        int[] dist = new int[g.V];
        dijkstra(g, 0, dist);
        assertEquals(0, dist[0]);
    }
}
