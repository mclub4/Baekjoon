import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean[][][] visited;
    static int n,m,k;
    static int end_x, end_y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        int start_x = -1, start_y = -1;
        end_x = -1;
        end_y = -1;
        board = new int[n][m];

        for(int i = 0; i<n; i++){
            String tmp = br.readLine();
            for(int j = 0; j<m; j++){
                char cur = tmp.charAt(j);
                if(cur == 'S'){
                    board[i][j] = 0;
                    start_x = i;
                    start_y = j;
                }
                else if(cur == 'H'){
                    board[i][j] = 0;
                    end_x = i;
                    end_y = j;
                }
                else if(cur == 'X') board[i][j] = -1;
                else board[i][j] = cur - '0';
            }
        }

        visited = new boolean[n][m][4];
        System.out.println(bfs(start_x, start_y));
    }

    public static int bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        //x좌표, y좌표, 이동 거리, 더 이전 역경, 이전 역경, 현재 역경
        queue.add(new int[]{a,b,0,-1});
        for(int i = 0; i<4; i++) visited[a][b][i] = true;

        int[] dirx = {1,0,0,-1};
        int[] diry = {0,-1,1,0};

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            int prev = cur[3];

            if(x == end_x && y == end_y) return dist;

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m || visited[nx][ny][i] || board[nx][ny]<0) continue;
                if(board[nx][ny]>k || i == (3-prev)) continue;
                if(prev != -1 && ((board[x-dirx[prev]][y-diry[prev]] + board[x][y] + board[nx][ny]) > k))continue;
                visited[nx][ny][i] = true;
                queue.add(new int[]{nx, ny, dist+1, i});
            }
        }

        return -1;
    }
}