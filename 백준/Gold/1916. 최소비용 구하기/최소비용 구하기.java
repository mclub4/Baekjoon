import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX_WEIGHT = 100001;
    static int[][] weight;
    static int[] touch;
    static int[] length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        weight = new int[n + 1][n + 1];
        touch = new int[n + 1];
        length = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                weight[i][j] = 1000 * 100000;
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (d < weight[a][b]) weight[a][b] = d;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        if (start != end) {
            dijkstra(n, start);
            System.out.println(length[end]);
        } else {
            System.out.println(0);
        }
    }

    public static void dijkstra(int n, int start) {
        Arrays.fill(length, 1000 * 100000);
        Arrays.fill(touch, start);

        boolean[] visited = new boolean[n + 1];

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        length[start] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visited[cur.end]) continue;
            visited[cur.end] = true;


            for (int i = 1; i <= n; i++) {
                if (visited[i] || weight[cur.end][i] == 1000 * 100000) continue;
                if (length[cur.end] + weight[cur.end][i] < length[i]) {
                    length[i] = length[cur.end] + weight[cur.end][i];
                    touch[i] = cur.end;
                    queue.add(new Node(i, length[i]));
                }
            }
        }


    }

    public static class Node implements Comparable<Node> {
        int end;
        int distance;

        public Node(int end, int distance) {
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
