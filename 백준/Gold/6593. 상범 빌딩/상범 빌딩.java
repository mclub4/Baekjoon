import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][][] board;
    static boolean[][][] visited;
    static int[] dirx = {1,-1,0,0,0,0};
    static int[] diry = {0,0,1,-1,0,0};
    static int[] dirz = {0,0,0,0,1,-1};
    static int L,R,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L == 0 && R == 0 && C == 0) break;

            board = new int[R][C][L];
            visited = new boolean[R][C][L];

            int[] start = new int[3];

            for(int i = 0; i<L; i++){
                for(int j = 0; j<R; j++){
                    String line = br.readLine();
                    for(int k = 0; k<C; k++){
                        char tmp = line.charAt(k);
                        if(tmp == 'S'){
                            board[j][k][i] = -1;
                            start[0] = j;
                            start[1] = k;
                            start[2] = i;
                        }
                        else if(tmp == '.') board[j][k][i] = 0;
                        else if(tmp == '#') board[j][k][i] = 1;
                        else if(tmp == 'E') board[j][k][i] = 2;
                    }
                }
                br.readLine();
            }

            int time = bfs(start[0], start[1], start[2]);

            if(time == -1) System.out.println("Trapped!");
            else System.out.println("Escaped in " + time + " minute(s).");
        }
    }

    public static int bfs(int a, int b, int c){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a,b,c, 0});
        visited[a][b][c]= true;

        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            int z = pos[2];
            int time = pos[3];

            if(board[x][y][z] == 2) return time;

            for(int i = 0; i<6; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
                int nz = z + dirz[i];
                if(nx>=0 && ny>=0 && nz>=0 && nx<R && ny<C && nz<L){
                    if(!visited[nx][ny][nz] && (board[nx][ny][nz] == 0 || board[nx][ny][nz] == 2)){
                        queue.add(new int[]{nx,ny,nz,time+1});
                        visited[nx][ny][nz] = true;
                    }
                }
            }
        }

        return -1;
    }
}
