import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static boolean[] visited;

    public static void dfs(int v){
        visited[v] = true;
        System.out.print(v + " ");

        for(int i = 1; i<arr.length; i++){
            if(arr[v][i] == 1 && visited[i] == false){
                dfs(i);
            }
        }
    }

    public static void bfs(int v){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);
        visited[v] = true;
        System.out.print(v + " ");

        while(!queue.isEmpty()){
            int temp = queue.poll();
            for(int i = 1; i< arr.length; i++){
                if(arr[temp][i] == 1 && visited[i] == false){
                    queue.add(i);
                    visited[i] = true;
                    System.out.print((i + " "));
                }
            }
        }
    }

    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n,m,v;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];
        visited = new boolean[n+1];

        for(int i = 0; i<m; i++){
            st =  new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[start][end] = 1;
            arr[end][start] = 1;
        }

        dfs(v);

        System.out.println("");
        visited = new boolean[n+1];

        bfs(v);
    }
}