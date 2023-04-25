import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = new int[m];
        nqueen(0);
        System.out.println(sb);
    }

    public static void nqueen(int depth){
        if(depth == m){
            for(int i = 0; i<result.length; i++){
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i<n; i++){
            result[depth] = i+1;
            nqueen(depth+1);
        }
    }
}