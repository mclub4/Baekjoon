import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] money = new long[n];

        for(int i = 0; i<n; i++){
            money[i] = Integer.parseInt(br.readLine());
        }

        long count = 0;
        for(int i = money.length-1; i>=0; i--){
            long num = k/money[i];
            k -= money[i]*num;
            count += num;
            if(k == 0) break;
        }

        System.out.println(count);
    }
}