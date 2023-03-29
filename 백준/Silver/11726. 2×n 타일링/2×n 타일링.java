import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        int[] dp = new int[n+1];

        for(int i = 1; i<n+1; i++){
            if(i==1) dp[i] = 1;
            else if(i==2) dp[i] = 2;
            else dp[i] = (dp[i-1] + dp[i-2]) % 10007 ;
        }


        System.out.println(dp[n]);

    }
}