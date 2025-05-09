import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] consult = new int[n + 1][2];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            consult[i][0] = Integer.parseInt(st.nextToken());
            consult[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            if (i + consult[i][0] - 1 <= n) {
                dp[i + consult[i][0] - 1] = Math.max(dp[i + consult[i][0] - 1], dp[i - 1] + consult[i][1]);
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        System.out.println(dp[n]);
    }

}
