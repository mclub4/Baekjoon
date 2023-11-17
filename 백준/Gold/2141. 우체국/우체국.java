import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] village = new long[n][2];

        long sum = 0;
        for(int i = 0; i <n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            village[i][0] = Long.parseLong(st.nextToken());
            long tmp = Long.parseLong(st.nextToken());
            village[i][1] = tmp;
            sum += tmp;
        }

        Arrays.sort(village, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[0] > o2[0]) return 1;
                if(o1[0] == o2[0]) return 0;
                else return -1;
            }
        });

        long sum2 = 0;
        long answer = 0;
        for(int i = 0; i<n; i++){
            sum2 += village[i][1];
            if(sum2 >= ((sum+1)/2)){
                answer = village[i][0];
                break;
            }
        }

        System.out.println(answer);
    }
}