import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  
        int[] power = new int[n];   
        int[] dp = new int[n];  
        
        for(int i=n-1;i>=0;i--){
            power[i] = sc.nextInt();    
            dp[i] = 1;  
        }     

        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(power[j]<power[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max = dp[0];
        for(int i=1;i<n;i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(n-max);
    }
}