import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int s = 0; s < t; s++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[n];

            for(int i = 0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long local = Integer.MIN_VALUE;
            long global = Integer.MIN_VALUE;
            for(int i = 0; i<n; i++){
                local = Math.max(local + arr[i], arr[i]);
                if(local>global) global = local;
            }

            System.out.println(global);
        }
    }
}