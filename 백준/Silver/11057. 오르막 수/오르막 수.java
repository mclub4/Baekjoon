import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n  = Integer.parseInt(br.readLine());
        long[][] dp = new long[n+1][10];

        for(int i = 0; i<10; i++){
            dp[1][i] = 1;
        }

        for(int i = 2; i<=n; i++){
            for(int j = 0; j<10; j++){
                for(int k = 9; k>=j; k--){
                    dp[i][j] += (dp[i-1][k]%10007);
                    dp[i][j] %= 10007;
                }
            }
        }

        long answer = 0;

        for(int i = 0; i<10; i++){
            answer += dp[n][i]%10007;
            answer %= 10007;
        }

        System.out.println(answer);
    }


}
