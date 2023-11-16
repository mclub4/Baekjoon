import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean[][][] visited;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m][2];

        for(int i = 0; i<n; i++){
            String tmp = br.readLine();
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(String.valueOf(tmp.charAt(j)));
            }
        }

        System.out.println(bfs(0,0));

    }

    public static int bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a,b,1, 0});

        int[] dirx = new int[]{1,-1,0,0};
        int[] diry = new int[]{0,0,1,-1};

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int w = cur[2];
            int z = cur[3];

            if(x == n-1 && y == m-1) return w;

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(z == 1 && board[nx][ny] == 1) continue;

                if(z == 0 && !visited[nx][ny][0]){
                    if(board[nx][ny] == 1){
                        visited[nx][ny][1] = true;
                        queue.add(new int[]{nx,ny,w+1,1});
                    }
                    else{
                        visited[nx][ny][0] = true;
                        queue.add(new int[]{nx,ny,w+1,0});
                    }
                }

                if(z == 1 && !visited[nx][ny][1] && board[nx][ny] == 0){
                    visited[nx][ny][1] = true;
                    queue.add(new int[]{nx,ny,w+1,1});
                }
            }
        }

        return -1;
    }
}