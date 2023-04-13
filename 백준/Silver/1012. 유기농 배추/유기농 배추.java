import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] graph;
    static boolean[][] visited;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static int result;
    static int m;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while(t!=0){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            result = 0;
            graph = new int[m][n];
            visited = new boolean[m][n];

            for(int i = 0; i<k; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x][y] = 1;
            }

            for(int i = 0; i<m; i++){
                for(int j = 0; j<n; j++){
                    if(graph[i][j] == 1 & visited[i][j] == false){
                        bfs(i, j);
                    }
                }
            }

            System.out.println(result);
            t --;
        }
    }

    public static void bfs(int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.add(new int[]{i,j});
        
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            for(int k = 0; k<4; k++){
                int x = tmp[0]+dirx[k];
                int y = tmp[1]+diry[k];
                if(x<0 || x>=m || y<0 || y>=n) continue;
                if(graph[x][y] == 1 & visited[x][y] == false) {
                    queue.add(new int[]{x,y});
                    visited[x][y] = true;
                }
            }
        }
        
        result ++;
    }
}