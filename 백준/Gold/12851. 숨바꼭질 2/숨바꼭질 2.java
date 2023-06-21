import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visited = new int[200000];
        int[] answer = bfs(n,k);
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    public static int[] bfs(int n, int k) {
        int min = -1;
        int method = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0});

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int now = tmp[0];
            int time = tmp[1];

            if (now == k) {
                if (min == -1) {
                    method++;
                    min = time;
                }
                else if (min == time) method++;
            }
            else if (time > min && min != -1) return new int[]{min, method};

            int[] arr = {now - 1, now + 1, now * 2};

            for (int i = 0; i < 3; i++) {
                int next = arr[i];
                if (next < 0 || next >= 200000) continue;
                if (visited[next] == 0 || visited[next] == time) {
                    visited[next] = time;
                    queue.add(new int[]{next, time + 1});
                }
            }
        }
        return new int[]{min, method};
    }
}