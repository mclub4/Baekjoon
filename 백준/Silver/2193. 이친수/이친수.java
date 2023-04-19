import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new long[n];
        if(n != 0) System.out.println(fibo(n-1));
        else System.out.println(0);
    }

    public static long fibo(int n){
        if(n==0 || n==1) return 1;
        if(dp[n] != 0) return dp[n];
        dp[n] = fibo(n-1) + fibo(n-2);
        return dp[n];
    }
}