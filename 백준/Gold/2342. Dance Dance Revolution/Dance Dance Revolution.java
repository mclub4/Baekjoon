import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Integer> query = new ArrayList<>();

        while(true){
            int tmp = Integer.parseInt(st.nextToken());
            if(tmp == 0) break;
            query.add(tmp);
        }

        int n = query.size();
        int[][][] dp = new int[n+1][5][5];

        for(int i = 0; i<n+1; i++){
            for(int j = 0; j<5; j++){
                for(int k = 0; k<5; k++){
                    dp[i][j][k] = 2147483643;
                }
            }
        }

        dp[0][0][0] = 0;

        for(int i = 1; i<n+1; i++){
            int next = query.get(i-1);

            for(int j = 0; j<5; j++){
                for(int k = 0; k<5; k++){
                    dp[i][next][k] = Math.min(dp[i][next][k], dp[i-1][j][k] + distance(j, next));
                    dp[i][j][next] = Math.min(dp[i][j][next], dp[i-1][j][k] + distance(k, next));
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for(int j = 0; j<5; j++){
            for(int k = 0; k<5; k++){
                min = Math.min(dp[n][j][k],min);
            }
        }


        System.out.println(min);
    }

    public static int distance(int l, int r){
        if(l == r) return 1;
        else if(l == 0) return 2;
        else if(Math.abs(l-r) == 2) return 4;
        else return 3;
    }
}
