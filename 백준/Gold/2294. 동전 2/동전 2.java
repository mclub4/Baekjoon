import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] coin;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        coin = new int[m+1];

        for(int j = 1; j<=m; j++){
            coin[j] = Integer.parseInt(br.readLine());
        }

//        dp = new int[m+1][k+1];
//        for(int i = 0; i<m+1; i++){
//            for(int j = 0; j<k+1; j++){
//                dp[i][j] = -1;
//            }
//        }
//        System.out.println(countCoinExchange(1,k));

        System.out.println(dp(m, k));
    }

    public static int countCoinExchange(int m, int n){
        if(n <= 0) return n == 0 ? 0 : 9999999;

        int ret = dp[m][n];
        if(ret != -1) return -1;

        ret = 9999999;
        for(int i = m; i<coin.length; i++){
            ret = Math.min(ret, countCoinExchange(i, n-coin[i]) + 1);
        }

        return ret;
    }

    public static int dp(int n, int k){
        int[] memo = new int[k+1];

        for(int i = 1; i<=k; i++){
            memo[i] = 99999999;
            for(int j = 1; j<=n; j++){
                if(i - coin[j] >= 0){
                    memo[i] = Math.min(memo[i], memo[i-coin[j]] + 1);
                }
            }
        }

        return memo[k] == 99999999 ? -1 : memo[k];
    }
}