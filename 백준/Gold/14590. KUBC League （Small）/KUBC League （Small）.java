import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, bitmask;
    static StringBuilder sb = new StringBuilder();
    static int[][] w;
    static int[][] dp;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        w = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<n; j++){
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bitmask = (1<<n);

        dp = new int[n][bitmask];
        for(int i = 0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(tsp(0,1)+1);
        sb.append(1 + " ");
        tracking(0,1);
        System.out.println(sb);
    }

    public static int tsp(int v, int check){
        if(dp[v][check] != -1) return dp[v][check];
        dp[v][check] = 0;

        for(int i = 0; i<n; i++){
            int next = check | (1<<i);
            if(w[v][i] == 0 || (check & (1<<i)) != 0) continue;
//            System.out.println(v + "와(전) "  + Integer.toBinaryString(check) + " " + dp[v][check]);
//            System.out.println("Next는? : " + Integer.toBinaryString(next));
            dp[v][check] = Math.max(dp[v][check], tsp(i,next) + 1);
//            System.out.println(v + "와 "  + Integer.toBinaryString(check) + " " + dp[v][check]);
        }

        return dp[v][check];
    }

    public static void tracking(int v, int check){
        for(int i = 0; i<n; i++){
            int next = check | (1<<i);
            if(w[v][i] == 0 || (check & (1<<i)) != 0) continue;
            if(dp[v][check] == dp[i][next] + 1){
                sb.append((i+1) + " ");
                count ++;
                tracking(i, next);
                break;
            }
        }
    }
}
