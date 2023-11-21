import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] wire = new int[n][2];

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            wire[i][0] = Integer.parseInt(st.nextToken());
            wire[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(wire, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i<n; i++){
            dp[i] = 1;
            for(int j = 0; j<i; j++){
                if(wire[j][1] < wire[i][1] && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int max = 0;
        for(int i = 0; i<n; i++){
            max = Math.max(max, dp[i]);
        }

        System.out.println(n - max);
    }

}