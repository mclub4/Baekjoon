import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int end;
        int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[10000 + 1];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, weight));
        }

        int[] dp = new int[10000 + 1];
        Arrays.fill(dp, 10000 * 12);
        dp[0] = 0;

        for (int i = 0; i < dp.length; i++) {
            if (i != 0) dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
            for (Node node : graph[i]) {
                dp[node.end] = Math.min(dp[i] + node.weight, dp[node.end]);
            }
        }

        System.out.println(dp[d]);
    }
}