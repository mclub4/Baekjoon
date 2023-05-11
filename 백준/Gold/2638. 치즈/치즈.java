import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n,m;
    static int count = 0;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<int[]> cheese;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        cheese = new ArrayList<>();

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1){
                    cheese.add(new int[]{i,j});
                }
            }
        }

        int time = 0;
        while(true){
            count = cheese.size();
//            System.out.println(time + "번째 시도");
//            System.out.println(count + "개의 치즈");
//            if(time == 5) break;
            if(count <= 0) break;
            visited = new boolean[n][m];
            dfs(0,0);
            visited = new boolean[n][m];
            bfs();
            time ++;
        }

        System.out.println(time);
    }

    public static void dfs(int x, int y){
        visited[x][y] = true;
        board[x][y] = 2;
//        System.out.println(x + ", " + y + " : " + board[x][y]);

        for(int i = 0; i<4; i++){
            int nx = x + dirx[i];
            int ny = y + diry[i];
            if(nx>=0 && ny>=0 && nx<n && ny<m){
                if(!visited[nx][ny] && (board[nx][ny] == 0 || board[nx][ny] == 2)){
                    dfs(nx, ny);
                }
            }
        }
    }

    public static void bfs(){
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i<cheese.size(); i++){
            queue.add(cheese.get(i));
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            visited[x][y] = true;

            int air = 0;

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
                if(nx>=0 && ny>=0 && nx<n && ny<m){
                    if(!visited[nx][ny] && board[nx][ny] == 2){
                        air++;
                    }
                }
            }

//            System.out.println(x + ", " + y + " : " + air);
            if(air>=2){
                board[x][y] = 0;
                cheese.remove(cur);
            }
        }
    }
}
