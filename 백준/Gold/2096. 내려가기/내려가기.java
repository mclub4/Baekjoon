import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] maxdp = new int[n][3];
        int[][] mindp = new int[n][3];


        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int x3 = Integer.parseInt(st.nextToken());

            if(i == 0){
                maxdp[i][0] = mindp[i][0] = x1;
                maxdp[i][1] = mindp[i][1] = x2;
                maxdp[i][2] = mindp[i][2] = x3;
            }
            else{
                maxdp[i][0] = Math.max(maxdp[i-1][0], maxdp[i-1][1]) + x1;
                maxdp[i][1] = Math.max(maxdp[i-1][0], Math.max(maxdp[i-1][1], maxdp[i-1][2])) + x2;
                maxdp[i][2] = Math.max(maxdp[i-1][1], maxdp[i-1][2]) + x3;
                mindp[i][0] = Math.min(mindp[i-1][0], mindp[i-1][1]) + x1;
                mindp[i][1] = Math.min(mindp[i-1][0], Math.min(mindp[i-1][1], mindp[i-1][2])) + x2;
                mindp[i][2] = Math.min(mindp[i-1][1], mindp[i-1][2]) + x3;
            }
        }

        int max = Math.max(maxdp[n-1][0], Math.max(maxdp[n-1][1], maxdp[n-1][2]));
        int min = Math.min(mindp[n-1][0], Math.min(mindp[n-1][1], mindp[n-1][2]));
        System.out.println(max + " " + min);

    }

}