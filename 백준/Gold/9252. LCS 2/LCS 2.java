import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] first = br.readLine().toCharArray();
        char[] second = br.readLine().toCharArray();

        int[][] dp = new int[first.length+1][second.length+1];

        for(int i = 1; i<first.length+1; i++){
            for(int j = 1; j<second.length+1; j++){
                if(first[i-1] == second[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        
        int i = first.length;
        int j = second.length;

        while(true){
            if(dp[i][j] == 0) break;
            else if(dp[i-1][j] == dp[i][j]) i--;
            else if(dp[i][j] == dp[i][j-1]) j--;
            else{
                sb.append(first[i-1]);
                i--;
                j--;
            }
        }

        System.out.println(dp[first.length][second.length]);
        System.out.println(sb.reverse());
    }
}