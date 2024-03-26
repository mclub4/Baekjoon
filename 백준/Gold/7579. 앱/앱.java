import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int ansnwer = Integer.MAX_VALUE;
        int[] memory = new int[n];
        int[] cost = new int[n];
        int[][] dp = new int[n][100001];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st1.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
        }


        for (int i = 0; i < n; i++) {
            int cur_cost = cost[i];
            int cur_memory = memory[i];

            for (int j = 0; j <= 10000; j++) {
                if (i == 0) {
                    if (j >= cur_cost) dp[i][j] = cur_memory;
                } else {
                    if (j >= cur_cost) dp[i][j] = Math.max(dp[i - 1][j - cur_cost] + cur_memory, dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= m) ansnwer = Math.min(ansnwer, j);
            }
        }
        System.out.println(ansnwer);
    }
}