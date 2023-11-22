import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] sum = new int[n+1];

            for(int i = 1; i<n+1; i++){
                int now = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + now;
            }

            int[][] dp = new int[n+1][n+1];

            for(int i = 1; i<n; i++){
                for(int j = 1; j+i<=n; j++){
                    dp[j][j+i] = Integer.MAX_VALUE;
                    for(int k = j; k<j+i; k++){
                        dp[j][j+i] = Math.min(dp[j][j+i], dp[j][k] + dp[k+1][j+i] + (sum[j+i] - sum[j-1]));
                    }
                }
            }

            System.out.println(dp[1][n]);
        }
    }
}
