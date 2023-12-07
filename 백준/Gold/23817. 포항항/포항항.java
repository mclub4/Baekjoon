import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static int[][] dist;
    static boolean[] dfsvisited;
    static int n,m,min,idx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        idx = 1;

        for(int i = 0; i<n; i++){
            String tmp = br.readLine();
            for(int j = 0; j<m; j++){
                char cur = tmp.charAt(j);
                if(cur == 'S') board[i][j] = 0;
                else if(cur == 'X') board[i][j] = -2;
                else if(cur == '.') board[i][j] = -1;
                else if(cur == 'K'){
                    board[i][j] = idx;
                    idx++;
                }
            }
        }

        dist = new int[idx][idx];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(board[i][j]>=0) bfs(i,j);
            }
        }

        min = Integer.MAX_VALUE;
        dfsvisited = new boolean[idx];
        dfs(0, 0, 0);

        System.out.println(min!=Integer.MAX_VALUE?min:-1);
    }

    //거리 기록
    public static void bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int[] dirx = {1,-1,0,0};
        int[] diry = {0,0,1,-1};
        queue.add(new int[]{a,b,0});
        visited[a][b] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
                if(nx<0 || ny<0 || nx>=n || ny>=m || visited[nx][ny] || board[nx][ny] == -2) continue;
                visited[nx][ny] = true;
                if(board[nx][ny] >= 0) dist[board[a][b]][board[nx][ny]] = cnt+1;
                queue.add(new int[]{nx,ny,cnt+1});
            }
        }
    }

    //백트래킹
    public static void dfs(int start, int depth, int total){
        if(depth == 5){
            min = Math.min(min, total);
            return;
        }

        for(int i = 0; i<idx; i++){
            if(!dfsvisited[i] && dist[start][i] != 0){
                dfsvisited[start] = true;
                dfs(i, depth+1, total + dist[start][i]);
                dfsvisited[start] = false;
            }
        }
    }
}