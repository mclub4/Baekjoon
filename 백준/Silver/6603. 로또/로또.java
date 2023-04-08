import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int[] lottery;
    static boolean[] visit;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) break;

            lottery = new int[k];

            for(int i = 0; i<k; i++){
                lottery[i] = Integer.parseInt(st.nextToken());
            }

            visit = new boolean[k];
            result = new int[6];

            dfs(0,0);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void dfs(int start, int depth){
        if(depth == 6){
            for(int i = 0; i<6; i++){
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start; i<k; i++){
            if(!visit[i]){
                visit[i] =true;
                result[depth] = lottery[i];
                dfs(i,depth+1);
                visit[i] = false;
            }
        }
    }

}