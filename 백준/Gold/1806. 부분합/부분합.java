import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int value = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;

        while(true) {
            if(sum >= s){
                sum -= arr[start];
                value = Math.min(end-start, value);
                start++;
            }
            else if(end == n) break;
            else{
                sum += arr[end];
                end ++;
            }
        }

        System.out.println(value != Integer.MAX_VALUE ? value:0);
    }
}