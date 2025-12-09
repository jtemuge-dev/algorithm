import javax.swing.*;
import java.awt.*;
import java.util.*;

class Edge {
    int to;
    int weight;
    Edge(int to, int weight) { this.to = to; this.weight = weight; }
}

class Graph {
    int V;
    java.util.List<Edge>[] adj;
    double[][] pos;
    String[] labels;

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

public class Main extends JPanel {

    private Graph g;
    private int[] prev;
    private static final int START_NODE = 0;
    private static final int NODE_RADIUS = 20;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dijkstra Shortest Path Visualizer (Swing)");
        Main panel = new Main();
        panel.initGraph();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void initGraph() {
        g = createSampleGraph();
        prev = dijkstra(g, START_NODE);
        setBackground(Color.WHITE);
    }

    Graph createSampleGraph() {
        // Node labels are like locations in a small town
        String[] labels = {"Home", "School", "Store", "Park", "Office", "Cafe"};
        Graph g = new Graph(6, labels);

        // Add edges with weights (distance or cost)
        g.addEdge(0, 1, 5);   // Home - School
        g.addEdge(0, 2, 3);   // Home - Store
        g.addEdge(1, 2, 2);   // School - Store
        g.addEdge(1, 3, 6);   // School - Park
        g.addEdge(2, 3, 4);   // Store - Park
        g.addEdge(2, 4, 7);   // Store - Office
        g.addEdge(3, 4, 3);   // Park - Office
        g.addEdge(3, 5, 5);   // Park - Cafe
        g.addEdge(4, 5, 2);   // Office - Cafe

        // Node positions for visualization (x, y)
        g.setPos(0, 150, 250); // Home
        g.setPos(1, 300, 100); // School
        g.setPos(2, 350, 300); // Store
        g.setPos(3, 500, 200); // Park
        g.setPos(4, 600, 350); // Office
        g.setPos(5, 450, 400); // Cafe

        return g;
    }



    int[] dijkstra(Graph g, int src) {
        int[] dist = new int[g.V];
        int[] prev = new int[g.V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[src] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dist[i]));
        pq.add(src);

        while (!pq.isEmpty()) {
            int u = pq.poll();

            if (dist[u] == Integer.MAX_VALUE) continue;

            for (Edge e : g.adj[u]) {
                if (dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    prev[e.to] = u;
                    pq.add(e.to);
                }
            }
        }

        System.out.println("Dijkstra Results:");
        for (int i = 0; i < g.V; i++)
            System.out.println(g.labels[i] + " <- " + (prev[i] == -1 ? "-" : g.labels[prev[i]]));

        return prev;
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2 = (Graphics2D) gr;

        g2.setStroke(new BasicStroke(2));
        g2.setFont(new Font("Arial", Font.BOLD, 14));

        g2.setColor(Color.GRAY.darker());
        for (int u = 0; u < g.V; u++) {
            for (Edge e : g.adj[u]) {
                if (u < e.to) {
                    double x1 = g.pos[u][0], y1 = g.pos[u][1];
                    double x2 = g.pos[e.to][0], y2 = g.pos[e.to][1];

                    g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

                    g2.drawString(String.valueOf(e.weight),
                            (int) ((x1 + x2) / 2 + 5),
                            (int) ((y1 + y2) / 2 - 5));
                }
            }
        }

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(4));
        for (int v = 0; v < g.V; v++) {
            int p = prev[v];
            if (p != -1) {
                double x1 = g.pos[v][0], y1 = g.pos[v][1];
                double x2 = g.pos[p][0], y2 = g.pos[p][1];
                g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
            }
        }

        for (int i = 0; i < g.V; i++) {
            double x = g.pos[i][0];
            double y = g.pos[i][1];

            if (i == START_NODE)
                g2.setColor(Color.ORANGE);
            else
                g2.setColor(new Color(135, 206, 250)); // light blue

            g2.fillOval((int) (x - NODE_RADIUS), (int) (y - NODE_RADIUS),
                    NODE_RADIUS * 2, NODE_RADIUS * 2);

            g2.setColor(Color.BLUE.darker());
            g2.setStroke(new BasicStroke(2));
            g2.drawOval((int) (x - NODE_RADIUS), (int) (y - NODE_RADIUS),
                    NODE_RADIUS * 2, NODE_RADIUS * 2);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.drawString(g.labels[i], (int) (x - 6), (int) (y + 6));
        }
    }
}
