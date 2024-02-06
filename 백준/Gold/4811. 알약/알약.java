import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[32][32];

        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                if (i == 0) dp[0][j] = 1;
                else if (j == 0) dp[i][j] = dp[i - 1][1];
                else dp[i][j] = dp[i - 1][j + 1] + dp[i][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            sb.append(dp[n][0] + "\n");
        }

        System.out.println(sb);
    }
}
