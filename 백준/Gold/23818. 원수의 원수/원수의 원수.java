import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Relationship {
    int connect;
    int relation;

    public Relationship(int connect, int relation) {
        this.connect = connect;
        this.relation = relation;
    }
}

public class Main {
    static ArrayList<Relationship>[] relation;
    static boolean[] visited;
    static boolean[] error;
    static int[] answer;
    static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        relation = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        answer = new int[n + 1];
        error = new boolean[n + 1];

        for (int i = 1; i < relation.length; i++) {
            relation[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relation[a].add(new Relationship(b, t));
            relation[b].add(new Relationship(a, t));
        }

        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                idx++;
                answer[i] = idx;
                dfs(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (Math.abs(answer[a]) != Math.abs(answer[b])) sb.append("Unknown\n");
            else if (error[Math.abs(answer[a])]) sb.append("Error\n");
            else if (answer[a] == answer[b]) sb.append("Friend\n");
            else if (answer[a] == -answer[b]) sb.append("Enemy\n");
        }

        System.out.println(sb);
    }

    public static void dfs(int cur) {
        for (Relationship now : relation[cur]) {
            int nxt = now.connect;
            int relation = now.relation;
            if (!visited[nxt]) {
                visited[nxt] = true;
                if (relation == 0) answer[nxt] = answer[cur];
                else answer[nxt] = -answer[cur];
                dfs(nxt);
            } else {
                if ((relation == 0 && answer[nxt] == -answer[cur]) || (relation == 1 && answer[nxt] == answer[cur])) {
                    error[idx] = true;
                }
            }
        }
    }
}