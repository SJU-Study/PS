import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, s, e;
    private static int[] dist;
    private static int[] before;
    private static List[] cities;

    private static class Edge {
        int v;
        int c;

        public Edge(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        cities = new List[n + 1];
        dist = new int[n + 1];
        before = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            cities[i] = new ArrayList<int[]>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            String[] in = br.readLine().split(" ");
            int start = Integer.parseInt(in[0]);
            int end = Integer.parseInt(in[1]);
            int cost = Integer.parseInt(in[2]);

            cities[start].add(new Edge(end, cost));
        }
        String[] in = br.readLine().split(" ");
        s = Integer.parseInt(in[0]);
        e = Integer.parseInt(in[1]);

        dijkstra();

        int idx = e;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while(idx != 0) {
            stack.push(idx);
            idx = before[idx];
        }

        System.out.println(dist[e]);
        System.out.println(stack.size());
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);

        br.close();
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.c));
        boolean[] visited = new boolean[n + 1];
        Edge edge = new Edge(s, 0);
        pq.add(edge);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int v = now.v;
            int c = now.c;
            if (visited[v]) continue;
            visited[v] = true;

            for (Edge next : (ArrayList<Edge>) cities[v]) {
                int nV = next.v;
                int nC = next.c;
                if (dist[nV] > c + nC) {
                    dist[nV] = c + nC;
                    pq.add(new Edge(nV, c + nC));
                    before[nV] = v;
                }
            }
        }
    }
}
