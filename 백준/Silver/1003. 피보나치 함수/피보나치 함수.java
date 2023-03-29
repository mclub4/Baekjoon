import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        for(int i = 0; i<n; i++){
            int temp = Integer.parseInt(br.readLine());
            int[] zero = new int[41];
            int[] one = new int[41];

            zero[0] = 1;
            zero[1] = 0;
            one[0] = 0;
            one[1] = 1;

            for(int j = 2; j <= temp; j++){
                zero[j] = zero[j-1] + zero[j-2];
                one[j] = one[j-1] + one[j-2];
            }
            System.out.println(zero[temp] + " " + one[temp]);
        }
    }
}
