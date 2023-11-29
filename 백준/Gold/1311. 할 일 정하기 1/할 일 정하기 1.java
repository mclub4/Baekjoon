import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[(1<<n)];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for (int i = 0; i < (1 << n); i++){
            int cnt = 0;
            int tmp = i;
            while (tmp > 0) {
                cnt += (tmp & 1);
                tmp >>= 1;
            }
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    dp[i | (1 << j)] = Math.min(dp[i | (1 << j)], dp[i] + cost[cnt][j]);
                }
            }
        }

        System.out.println(dp[(1<<n)-1]);

    }

}