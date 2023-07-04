import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[][] board;
    static Queue<int[]> fire;
    static int w;
    static int h;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new char[w][h];
        fire = new LinkedList<>();

        int[] start = new int[2];

        for(int i = 0; i<w; i++){
            String line = br.readLine();
            for(int j = 0 ; j<h; j++){
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'J'){
                    start[0] = i;
                    start[1] = j;
                }
                if (board[i][j] == 'F') {
                    fire.add(new int[]{i,j});
                }
            }
        }

        bfs(start[0], start[1]);



        System.out.println(sb);
    }

    public static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y, 0});
        int time = 0;
        boolean[] check = new boolean[1000000];
        boolean[][] visited = new boolean[w][h];

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowx = now[0];
            int nowy = now[1];
            int nowt = now[2];

            if(!check[nowt]){
                burn();
                time ++;
                check[nowt] = true;
            }

            for(int i = 0; i<4; i++){
                int nx = nowx + dirx[i];
                int ny = nowy + diry[i];

                if(nx<0 || ny<0 || nx>=w || ny>=h){
                    sb.append(time + "\n");
                    return;
                }
                else if(board[nx][ny] == '.' && !visited[nx][ny]){
                    queue.add(new int[]{nx,ny,time});
                    visited[nx][ny] = true;
                }
            }
        }

        sb.append("IMPOSSIBLE\n");
    }

    public static void burn(){
        int size = fire.size();

        for(int s = 0; s<size; s++){
            int[] now = fire.poll();
            int nowx = now[0];
            int nowy = now[1];

            for(int i = 0; i<4; i++){
                int nx = nowx + dirx[i];
                int ny = nowy + diry[i];

                if(nx<0 || ny<0 || nx>=w || ny>=h) continue;

                if(board[nx][ny] == '.' || board[nx][ny] == 'J'){
                    fire.add(new int[]{nx,ny});
                    board[nx][ny] = 'F';
                }
            }
        }
    }
}
