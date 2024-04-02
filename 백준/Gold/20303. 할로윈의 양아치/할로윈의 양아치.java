import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] candy;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        candy = new int[n + 1];
        count = new int[n + 1];


        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            count[i] = 1;
            candy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        

        ArrayList<Integer> group = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (count[i] != 0) group.add(i);
        }

        int[] dp = new int[k];

        for (int i : group) {
            for (int j = k - 1; j >= count[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - count[i]] + candy[i]);
            }
        }

        System.out.println(dp[k - 1]);
    }

    public static int find(int a) {
        if (parent[a] == a) return parent[a];
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        int parent_a = find(parent[a]);
        int parent_b = find(parent[b]);

        if (parent_a != parent_b) {
            if (parent_a < parent_b) {
                parent[parent_b] = parent_a;
                count[parent_a] += count[parent_b];
                candy[parent_a] += candy[parent_b];
                count[parent_b] = 0;
            } else {
                parent[parent_a] = parent_b;
                count[parent_b] += count[parent_a];
                candy[parent_b] += candy[parent_a];
                count[parent_a] = 0;
            }
        }
    }
}