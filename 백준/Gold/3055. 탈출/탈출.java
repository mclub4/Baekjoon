import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static char[][] board;
    static boolean[][] visited;
    static int[] dirx = {1,-1, 0, 0};
    static int[] diry = {0,0,1,-1};
    static Queue<int[]> curwater = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        Point start = new Point();

        for(int i = 0; i<R; i++){
            String tmp = br.readLine();
            for(int j = 0; j<C; j++){
                char cur = tmp.charAt(j);
                if(cur == 'S'){
                    start.x = i;
                    start.y = j;
                    board[i][j] = '.';
                }
                else{
                    board[i][j] = cur;
                    if(cur == '*'){
                        curwater.add(new int[]{i,j});
                    }
                }
            }
        }

        int answer = bfs(start.x, start.y);

        if(answer == -1) System.out.println("KAKTUS");
        else System.out.println(answer);
    }

    public static int bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a,b,0});

        int time = 0;

        boolean[] check = new boolean[R*C+2];

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];
            
            if(!check[t]){
                water();
                time ++;
                check[t] = true;
            }

            for(int j = 0; j<4; j++){
                int nx = x + dirx[j];
                int ny = y + diry[j];

                if(nx>=0 && nx<R && ny>=0 && ny<C){
                    if(board[nx][ny] == '.' && !visited[nx][ny]){
                        queue.add(new int[]{nx,ny,time});
                        visited[nx][ny] = true;
                    }
                    else if(board[nx][ny] == 'D') return time;
                }
            }
        }

        return -1;
    }

    public static void water(){
        int size = curwater.size();

        for(int i = 0; i<size; i++){
            int[] cur = curwater.poll();
            int x = cur[0];
            int y = cur[1];

            for(int j = 0; j<4; j++){
                int nx = x + dirx[j];
                int ny = y + diry[j];

                if(nx>=0 && nx<R && ny>=0 && ny<C){
                    if(board[nx][ny] == '.'){
                        board[nx][ny] = '*';
                        curwater.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
