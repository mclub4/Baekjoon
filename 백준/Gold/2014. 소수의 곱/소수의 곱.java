import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        number = new long[k];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i<k; i++){
            number[i] = Long.parseLong(st.nextToken());
        }

        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);

        int cnt = -1;
        long answer = 0;

        while(true){
            long now = queue.poll();
            cnt ++;

            if(cnt == n){
                answer = now;
                break;
            }

            long max = getMax(now);

            for(int i = 0; i<number.length; i++){
                if(number[i] < max) continue;
                long next = now*number[i];
                queue.add(next);
            }
        }

        System.out.println(answer);

    }

    public static long getMax(long n){
        long max = -1;

        for(long i = 2; i<= Math.sqrt(n); i++){
            while(n%i == 0){
                max = Math.max(max, i);
                n /= i;
            }
        }

        if(n != 1) max = n;

        return max;
    }
}