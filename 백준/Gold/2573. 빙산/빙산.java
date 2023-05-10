import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] city;
    static int n,m;
    static boolean visited[][];
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<m; j++){
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;

        while(true){
            int count = 0;
            visited = new boolean[n][m];

            for(int i = 0; i<n; i++){
                for(int j = 0; j<m; j++){
                    if(!visited[i][j] && city[i][j] != 0){
                        dfs(new int[]{i,j});
                        count ++;
                    }
                }
            }

            if(count >= 2) break;
            if(count == 0){
                year = 0;
                break;
            }

            visited = new boolean[n][m];
            bfs();
            year ++;
        }



        System.out.println(year);

    }

    public static void dfs(int[] pos){
        int x = pos[0];
        int y = pos[1];
        visited[x][y] = true;

        for(int i = 0; i<4; i++){
            int next_x = x + dirx[i];
            int next_y = y+ diry[i];
            if(next_y>=0 && next_x>=0 && next_x<n && next_y<m){
                if(city[next_x][next_y] != 0 && !visited[next_x][next_y]){
                    dfs(new int[]{next_x, next_y});
                }
            }
        }

    }

    public static void bfs(){
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(city[i][j] != 0){
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            int melt = 0;

            for(int i = 0; i<4; i++){
                int next_x = x + dirx[i];
                int next_y = y + diry[i];
                if(next_y>=0 && next_x>=0 && next_x<n && next_y<m){
                    if(!visited[next_x][next_y] && city[next_x][next_y] == 0){
                        melt ++;
                    }
                }
            }

            if(city[x][y] - melt <0) city[x][y] = 0;
            else city[x][y] -= melt;
        }
    }
}