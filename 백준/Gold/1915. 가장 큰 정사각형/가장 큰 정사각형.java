import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n][m];
        int max = 0;

        for(int i = 0; i<n; i++){
            String tmp = br.readLine();
            for(int j = 0; j<m; j++){
                dp[i][j] = tmp.charAt(j) - '0';
                if(max == 0 && dp[i][j] == 1) max = 1;
            }
        }


        for(int i = 1; i<n; i++){
            for(int j = 1; j<m; j++){
                if(dp[i][j] == 0) continue;
                int first = dp[i-1][j];
                int second = dp[i][j-1];
                int third = dp[i-1][j-1];
                if(first == 0 || second == 0 || third == 0) continue;
                dp[i][j] = Math.min(first, Math.min(second, third)) + 1;
                max = Math.max(dp[i][j], max);
            }
        }

        System.out.println((int)Math.pow(max, 2));
    }
}
