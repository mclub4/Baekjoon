import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int w,h;
    static int[][] board;
    static boolean[][] visited;

    static int[] dirx = {1,-1,0,0,1,1,-1,-1};
    static int[] diry = {0,0,1,-1,1,-1,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            board = new int[h][w];
            visited = new boolean[h][w];

            for(int i = 0; i<h; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j<w; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for(int i= 0; i<h; i++){
                for(int j = 0; j<w; j++){
                    if(!visited[i][j] && board[i][j] == 1){
                        count++;
                        bfs(i,j);
                    }
                }
            }

            System.out.println(count);
        }
    }

    public static void bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a,b});
        visited[a][b] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i = 0; i<8; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
                if(nx>=0 && ny>=0 && nx<h && ny<w){
                    if(!visited[nx][ny] && board[nx][ny] == 1){
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx,ny});
                    }
                }
            }
        }

    }
}
