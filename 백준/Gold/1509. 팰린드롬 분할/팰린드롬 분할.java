import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] palandrom;
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();

        int[] dp = new int[origin.length()+1];
        makeTable(origin);

        for(int i = 1; i<=origin.length(); i++){
            dp[i] = origin.length();
            for(int j = 1; j<=i; j++){
                if(palandrom[j][i]) dp[i] = Math.min(dp[i], dp[j-1] + 1);
            }
        }

        System.out.println(dp[origin.length()]);
    }

    public static void makeTable(String T){
        palandrom = new boolean[T.length()+1][T.length()+1];

        for(int i = 1; i<=T.length(); i++) palandrom[i][i] = true;
        for(int i = 1; i<T.length(); i++) if(T.charAt(i-1) == T.charAt(i))palandrom[i][i+1] = true;

        for(int i = 2; i<=T.length(); i++){
            for(int j = 1; j<i; j++){
                if(T.charAt(j-1) == T.charAt(i-1) && palandrom[j+1][i-1]){
                    palandrom[j][i] = true;
                }
            }
        }
    }
}
