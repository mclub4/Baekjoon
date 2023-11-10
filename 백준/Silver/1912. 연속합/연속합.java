import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] array = new int[n];

        for(int i = 0; i<n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int local = 0;
        int global = Integer.MIN_VALUE;
        for(int i = 0; i<n; i++){
            local = Math.max(array[i], local+array[i]);
            if(global < local) global = local;
        }

        System.out.println(global);

    }
}