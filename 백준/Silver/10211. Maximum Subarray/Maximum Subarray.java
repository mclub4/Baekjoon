import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int s = 0; s < t; s++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] dp = new int[n+1];

            for(int i = 1; i<n+1; i++){
                int val = Integer.parseInt(st.nextToken());
                dp[i] = dp[i-1] + val;
            }

            int max = Integer.MIN_VALUE;
            for(int i=1; i<=n; i++)
                for(int j=i; j<=n; j++)
                    max = Math.max(max, dp[j] - dp[i-1]);

            System.out.println(max);
        }
    }
}