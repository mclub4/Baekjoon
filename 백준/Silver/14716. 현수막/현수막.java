import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static boolean[][] visited;
    static int[] dirx = {1,-1,0,0,1,-1,1,-1};
    static int[] diry = {0,0,1,-1,1,-1,-1,1};
    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int width = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(!visited[i][j] && board[i][j] == 1){
                    bfs(i,j);
                    width ++;
                }
            }
        }

        System.out.println(width);
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
                if(nx>=0 && nx<n && ny>=0 && ny<m){
                    if(!visited[nx][ny] && board[nx][ny] == 1){
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }

        }
    }
}
