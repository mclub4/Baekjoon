import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] coin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i<T; i++){
            int m = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            coin = new int[m+1];

            for(int j = 1; j<=m; j++){
                coin[j] = Integer.parseInt(st.nextToken());
            }

            int n = Integer.parseInt(br.readLine());
            System.out.println(countCoinExchange(m,n));
        }
    }

    public static int countCoinExchange(int m, int n){
        int numComb;
        int[][] dp = new int[m+1][n+1];

        for(int i = 1; i<=m; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(j < coin[i]) numComb = 0;
                else numComb = dp[i][j-coin[i]];
                dp[i][j] = dp[i-1][j] + numComb;
            }
        }
        
        return dp[m][n];
    }
}