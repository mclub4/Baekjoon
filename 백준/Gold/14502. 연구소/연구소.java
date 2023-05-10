import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] city;
    static int n,m;
    static int result = 0;
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

        dfs(0);

        System.out.println(result);

    }

    public static void dfs(int depth){
        if(depth == 3){
            result = Math.max(result, bfs());
            return;
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(city[i][j] == 0){
                    city[i][j] = 1;
                    dfs(depth+1);
                    city[i][j] = 0;
                }
            }
        }

    }

    public static int bfs(){
        Queue<int[]> queue = new LinkedList<>();

        int[][] copy = new int[n][m];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                copy[i][j] = city[i][j];
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(copy[i][j] == 2){
                    queue.add(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i = 0; i<4; i++){
                int next_x = cur[0] + dirx[i];
                int next_y = cur[1] + diry[i];
                if(next_y>=0 && next_x>=0 && next_x<n && next_y<m){
                    if(copy[next_x][next_y] == 0){
                        queue.add(new int[]{next_x, next_y});
                        copy[next_x][next_y] = 2;
                    }
                }
            }
        }

        int count = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(copy[i][j] == 0) count++;
            }
        }

        return count;
    }
}