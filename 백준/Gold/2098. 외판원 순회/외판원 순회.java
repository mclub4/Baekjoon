import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, bitmask;
    static int MAX = 987654321;
    static int[][] w;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        w = new int[n][n];

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<n; j++){
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bitmask = (1<<n) -1;

        dp = new int[n][bitmask];
        for(int i = 0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(tsp(0,1));
    }

    public static int tsp(int v, int check){
        if(check == bitmask){
            if(w[v][0] == 0) return MAX;
            else return w[v][0];
        }

        if(dp[v][check] != -1) return dp[v][check];
        dp[v][check] = MAX;

        for(int i = 0; i<n; i++){
            int next = check | (1<<i);
            if(w[v][i] == 0 || (check & (1<<i)) != 0) continue;
            dp[v][check] = Math.min(dp[v][check], tsp(i,next) + w[v][i]);
        }

        return dp[v][check];
    }
}
