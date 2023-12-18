import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

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
            Stack<Integer> stack = new Stack<>();
            stack.push(end);
            while (true) {
                end = touch[end];
                stack.push(end);
                if (end == start) break;
            }
            System.out.println(stack.size());
            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        } else {
            System.out.println(0);
            System.out.println(1);
            System.out.println(start);
        }
    }

    static void dijkstra(int numNodes, int start) {
        boolean[] visited = new boolean[numNodes + 1];
        for (int i = 1; i <= numNodes; i++) {
            if (i == start) continue;
            touch[i] = start;
            length[i] = weight[start][i];
        }
        int repeat = numNodes;

        int vNear = -1;
        while (repeat-- > 0) {
            int minWeight = 1000 * 100000;
            for (int i = 1; i <= numNodes; i++) {
                if (i == start) continue;
                if (!visited[i] && 0 <= length[i] && length[i] < minWeight) {
                    minWeight = length[i];
                    vNear = i;
                }
            }
            for (int i = 1; i <= numNodes; i++) {
                if (i == start) continue;
                if (length[vNear] + weight[vNear][i] < length[i]) {
                    length[i] = length[vNear] + weight[vNear][i];
                    touch[i] = vNear;
                }
            }
            visited[vNear] = true;
        }
    }
}
