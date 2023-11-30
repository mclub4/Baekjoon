import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] dp;
    static int[][] board;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0,0));
    }

    public static int dfs(int x, int y){
        if(x == (n-1) && y == (m-1)) return 1;
        if(dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        for(int i = 0; i<4; i++){
            int nx = x + dirx[i];
            int ny = y + diry[i];

            if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

            if(board[x][y] > board[nx][ny]){
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }
}