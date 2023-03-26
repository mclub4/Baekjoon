import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] connect;
    static boolean[] visited;
    static int sum = 0;

    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(st.nextToken());

        connect = new int[n+1][n+1];
        visited = new boolean[n+1];

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            connect[start][end] = 1;
            connect[end][start] = 1;
        }

        dfs(1);

        System.out.println(sum);
    }

    public static void dfs(int v){
        visited[v] = true;
        if(v != 1) sum ++;
        for(int i = 1; i<connect.length; i++){
            if(connect[v][i] == 1 && visited[i] == false){
                dfs(i);
            }
        }
    }
}
