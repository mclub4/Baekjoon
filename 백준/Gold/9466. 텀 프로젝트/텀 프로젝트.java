import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] favorite;
    static boolean[] visited;
    static boolean[] done;
    static int count;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            favorite = new int[n + 1];
            visited = new boolean[n + 1];
            done = new boolean[n + 1];
            count = 0;

            for (int i = 1; i <= n; i++) {
                favorite[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (done[i]) continue;
                dfs(i);
            }

            sb.append((n - count) + "\n");
        }

        System.out.println(sb);
    }

    public static void dfs(int n) {
        if (done[n]) return;
        if (visited[n]) {
            done[n] = true;
            count++;
        }
        visited[n] = true;
        dfs(favorite[n]);
        visited[n] = false;
        done[n] = true;
    }
}
