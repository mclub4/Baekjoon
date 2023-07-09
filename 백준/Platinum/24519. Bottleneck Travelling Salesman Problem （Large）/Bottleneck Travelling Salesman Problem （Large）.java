import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m, bitmask;
    static StringBuilder sb = new StringBuilder();
    static int[][] w;
    static int[][] dp;
    static int answer;
    static int MAX = 987654321;
    static boolean flag = false;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        w = new int[n][n];
        result = new int[n];

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            w[x-1][y-1] = Integer.parseInt(st.nextToken());
        }

        bitmask = (1<<n);

        dp = new int[n][bitmask];
        for(int i = 0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }

        answer = tsp(0,1);
        tracking(0,1, 0);
        if(flag) System.out.println(sb);
        else System.out.println(-1);
    }

    public static int tsp(int v, int check){
        if(check == (bitmask-1)){
            if(w[v][0] == 0) return dp[v][check] = MAX;
            else return dp[v][check] = w[v][0];
        }

        if(dp[v][check] != -1) return dp[v][check];
        dp[v][check] = MAX;

        for(int i = 0; i<n; i++){
            int next = check | (1<<i);
            if(w[v][i] == 0 || (check & (1<<i)) != 0) continue;
            int nxt = tsp(i,next);
            dp[v][check] = Math.min(dp[v][check], Math.max(nxt, w[v][i]));
        }

        return dp[v][check];
    }

    public static void tracking(int v, int check, int depth){
        result[depth] = v;
        if(depth == (n-1)){
            if(check == (bitmask-1) && w[v][0] != 0){
                System.out.println(answer);
                for(int i = 0; i<n; i++){
                    sb.append((result[i]+1) + " ");
                }
                flag = true;
            }
        }

        for(int i = 0; i<n; i++){
            int next = check | (1<<i);
            if(w[v][i] == 0 || (check & (1<<i)) != 0) continue;
            if(dp[v][check] == Math.max(dp[i][next], w[v][i])){
                tracking(i, next, depth+1);
                break;
            }
        }
    }
}