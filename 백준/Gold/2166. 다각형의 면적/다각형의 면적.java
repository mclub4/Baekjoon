import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][] position = new long[n+1][2];

        StringTokenizer st;

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            position[i][0] = Integer.parseInt(st.nextToken());
            position[i][1] = Integer.parseInt(st.nextToken());
        }

        position[n][0] = position[0][0];
        position[n][1] = position[0][1];

        long up = 0;

        for(int i = 0; i<n; i++) {
            up += position[i][0]*position[i+1][1];
        }

        long down = 0;

        for(int i = 1; i<n+1; i++) {
            down += position[i][0]*position[i-1][1];
        }

        String result = String.format("%.1f", 0.5*(Math.abs(up - down)));
        System.out.println(result);
    }

}