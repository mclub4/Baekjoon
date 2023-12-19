import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int end;
        int d;

        public Node(int end, int d) {
            this.end = end;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }

    static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v + 1];

        for (int i = 1; i < v + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, d));
            list[b].add(new Node(a, d));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0));
        boolean[] visited = new boolean[v + 1];
        int weight = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visited[cur.end]) continue;
            visited[cur.end] = true;
            weight += cur.d;

            for (Node nxt : list[cur.end]) {
                if (!visited[nxt.end]) {
                    queue.add(new Node(nxt.end, nxt.d));
                }
            }
        }

        System.out.println(weight);
    }
}