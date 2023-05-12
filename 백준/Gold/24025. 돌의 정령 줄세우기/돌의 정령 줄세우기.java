import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int u = n;
        int d = 1;

        int[] height = new int[n];
        int[] answer = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++){
            height[i] = Integer.parseInt(st.nextToken());
            if(height[i] >0) answer[i] = u--;
            else answer[i] = d++;
        }

        StringBuilder sb = new StringBuilder();
        if(height[n-1] < 0) System.out.println(-1);
        else{
            for(int i = 0; i<n; i++){
                sb.append(answer[i] + " ");
            }
        }

        System.out.println(sb.toString());
    }
}
