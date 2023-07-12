import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        dp = new long[n+1][10][1 << 10];

        for(int i = 1; i<=9; i++){
            dp[1][i][1 << i] = 1;
        }

        for(int i= 2; i<n+1; i++){
            for(int j = 0; j<=9; j++){
                for(int k = 0; k< (1<<10); k++){
                    int cur = k | (1<<j);
                    if(j == 0) dp[i][j][cur] += dp[i-1][j+1][k]%1000000000;
                    else if(j==9) dp[i][j][cur] += dp[i-1][j-1][k]%1000000000;
                    else dp[i][j][cur] += (dp[i-1][j+1][k]%1000000000 + dp[i-1][j-1][k]%1000000000);

                    dp[i][j][cur] %= 1000000000;
                }
            }
        }

        long answer = 0;
        for(int i = 0; i<=9; i++){
            answer += dp[n][i][(1<<10)-1]%1000000000;
            answer %= 1000000000;
        }

        System.out.println(answer);
    }
}