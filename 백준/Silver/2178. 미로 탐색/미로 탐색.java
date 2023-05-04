import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] gameboard;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        gameboard = new int[n][m];

        for(int i = 0; i<n; i++){
            String[] tmp = br.readLine().split("");
            for(int j = 0; j<m; j++){
                gameboard[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        
        bfs(0,0);

        System.out.println(gameboard[n-1][m-1]);
    }

    public static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});

        while(!queue.isEmpty()){
            int[] start = queue.poll();
            int start_x = start[0];
            int start_y = start[1];

            for(int i = 0; i<4; i++){
                int next_x = start_x + dirx[i];
                int next_y = start_y + diry[i];
                if(next_x<0 || next_x>=n || next_y<0 || next_y>=m) continue;
                if(gameboard[next_x][next_y] == 0) continue;
                if(gameboard[next_x][next_y] == 1){
                    gameboard[next_x][next_y] = gameboard[start_x][start_y] + 1;
                    queue.add(new int[]{next_x,next_y});
                }
            }

        }
    }
}