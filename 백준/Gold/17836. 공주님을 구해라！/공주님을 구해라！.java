import org.w3c.dom.Node;

import javax.swing.text.Position;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.*;

public class Main {
    public static class Position{
        int x,y,time;
        boolean sword;

        public Position(int x, int y, int time , boolean sword){
            this.x = x;
            this.y = y;
            this.time = time;
            this.sword = sword;
        }
    }
    static int n,m,t;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static boolean[][][] visited;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m][2];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = bfs();
        System.out.println(time != -1 ? time : "Fail");
    }

    public static int bfs(){
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0,0, 0, board[0][0] == 2 ? true : false ));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Position position = queue.poll();
            int x = position.x;
            int y = position.y;
            int time = position.time;
            boolean sword = position.sword;
            if(time>t) break;
            if(x == n-1 && y == m-1) return time;

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
                if(nx<n && ny<m && nx>=0 && ny>=0){
                    if(!sword && !visited[nx][ny][0] && (board[nx][ny] == 0 || board[nx][ny] == 2)){
                        if(board[nx][ny] == 0) visited[nx][ny][0] = true;
                        if(board[nx][ny] == 2) visited[nx][ny][1] = true;
                        queue.add(new Position(nx, ny, time+1, board[nx][ny] == 2 ? true : false));
                    }
                    else if(sword && !visited[nx][ny][1] && (board[nx][ny] == 0 || board[nx][ny] == 1)){
                        visited[nx][ny][1] = true;
                        queue.add(new Position(nx, ny, time+1, true));
                    }
                }
            }
        }
        return -1;
    }
}
