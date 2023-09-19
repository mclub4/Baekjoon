import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            int[][] board = new int[2][n];
            int[][] dp = new int[2][n];

            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<n; j++){
                board[0][j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<n; j++){
                board[1][j] = Integer.parseInt(st.nextToken());
            }

            dp[0][0] = board[0][0];
            dp[1][0] = board[1][0];

            if(n>2){
                dp[0][1] = board[1][0] + board[0][1];
                dp[1][1] = board[0][0] + board[1][1];

                for(int j = 2; j<n; j++){
                    dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + board[0][j];
                    dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + board[1][j];
                }


            }
            else if(n==2){
                dp[0][1] = board[1][0] + board[0][1];
                dp[1][1] = board[0][0] + board[1][1];
            }

            System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
        }
    }
}
