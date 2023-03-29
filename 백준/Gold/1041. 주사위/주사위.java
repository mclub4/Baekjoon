import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] min;

    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long n = Integer.parseInt(st.nextToken());

        int[] dice = new int[6];


        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<6; i++){
            dice[i] = Integer.parseInt((st.nextToken()));
        }

        min = new long[3];

        min[0] = Math.min(dice[0], dice[5]);
        min[1] = Math.min(dice[1], dice[4]);
        min[2] = Math.min(dice[2], dice[3]);

        Arrays.sort(min);

        long two = 4*(n-2) + 4*(n-1);
        long one = 4*(n-1)*(n-2) + (n-2)*(n-2);
        long sum = 0;

        if(n==1){
            Arrays.sort(dice);
            for(int i = 0; i<5; i++){
                sum += dice[i];
            }
        }
        else {
            sum += 4*(min[0] + min[1] + min[2]) + two*(min[0] + min[1]) + one*min[0];
        }
        System.out.println(sum);
    }
}
