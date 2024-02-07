import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] array = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int[] dp2 = new int[n];
        dp2[n - 1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            dp2[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (array[j] < array[i] && dp2[i] < dp2[j] + 1) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i] + dp2[i]);
        }

        System.out.println(max - 1);
    }
}
