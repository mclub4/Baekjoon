import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] time = new int[T + 1];

        for (int i = 1; i <= T; i++) {
            time[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[T + 1][W + 2][3]; //X위치에 있을 때, T 시간이고 W이동횟수를 했을 때 자두 최대값

        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= W + 1; j++) {
                if (time[i] == 1) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]) + 1;
                    dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2]);
                } else {
                    if (i == 1 && j == 1) continue;
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]);
                    dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2]) + 1;
                }
            }
        }

        int max = 0;

        for (int i = 1; i <= W + 1; i++) {
            max = Math.max(max, Math.max(dp[T][i][1], dp[T][i][2]));
        }

        System.out.println(max);
    }
}


