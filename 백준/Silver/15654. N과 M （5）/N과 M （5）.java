import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] result;
    static int[] number;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        result = new int[m];
        number = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(number);

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
            if(visited[i] == false){
                visited[i] = true;
                result[depth] = number[i];
                nqueen(depth+1);
                visited[i] = false;
            }
        }
    }
}