import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] dungeon = new int[n][2];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            dungeon[i][0] = Integer.parseInt(st.nextToken());
            dungeon[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] move = new int[n][n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                move[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[t+1][n];

        for(int i = 0; i<t; i++){
            for(int j = 0; j<n; j++){
                if(dp[i][j] >= dungeon[j][0]) dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + dungeon[j][1]);
                for(int k = 0; k<n; k++){
                    if(j == k || (i + move[j][k] > t) || dp[i][j] < dungeon[k][0]) continue;
                    dp[i + move[j][k]][k] = Math.max(dp[i + move[j][k]][k], dp[i][j]);
                }
            }
        }

        int max = 0;
        for(int i = 0; i<n; i++){
            max = Math.max(max, dp[t][i]);
        }

        System.out.println(max);
    }

}