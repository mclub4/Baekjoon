import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][] dp;
    static int[] weight;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        weight = new int[n];
        for(int i = 0; i<n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }
        dp = new boolean[n+1][n*500 + 1];

        backtracking(0,0);

        int k = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<k; i++){
            int cur = Integer.parseInt(st.nextToken());
            if(cur>n*500) sb.append("N ");
            else if(dp[n][cur]) sb.append("Y ");
            else sb.append("N ");
        }
        System.out.println(sb);
    }

    public static void backtracking(int idx, int sum){
        if(dp[idx][sum]) return;
        dp[idx][sum] = true;
        if(idx == n) return;

        backtracking(idx+1, sum+weight[idx]);
        backtracking(idx+1, sum);
        backtracking(idx+1, Math.abs(sum-weight[idx]));
    }
}