import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] size = new int[n+1][2];

        for(int i = 1; i<n+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            size[i][0] = Integer.parseInt(st.nextToken());
            size[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][]dp = new int[n+1][n+1];

        for(int i = 1; i<n; i++){
            for(int j = 1; j+i<=n; j++){
                dp[j][j+i] = Integer.MAX_VALUE;
                for(int k = j; k<(i+j); k++){
                    dp[j][j+i] = Math.min(dp[j][j+i], dp[j][k] + dp[k+1][i+j] + size[j][0]*size[k][1]*size[i+j][1]);
                }
            }
        }

        System.out.println(dp[1][n]);
    }
}