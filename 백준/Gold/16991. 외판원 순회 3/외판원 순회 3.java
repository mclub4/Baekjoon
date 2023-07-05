import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, bitmask;
    static int MAX = 987654321;
    static double[][] w;
    static double[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[][] position = new int[n][2];

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            position[i][0] = Integer.parseInt(st.nextToken());
            position[i][1] = Integer.parseInt(st.nextToken());
        }

        w = new double[n][n];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                w[i][j] = Math.sqrt(Math.pow(position[i][0] - position[j][0], 2) + Math.pow(position[i][1] - position[j][1], 2));
            }
        }

        bitmask = (1<<n) -1;

        dp = new double[n][bitmask];
        for(int i = 0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(tsp(0,1));
    }

    public static double tsp(int v, int check){
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
