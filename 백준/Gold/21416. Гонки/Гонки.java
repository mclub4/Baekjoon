import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static char[][] board;
    static int n;
    static int m;
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visited = new boolean[n][m];

        int[] start = new int[2];

        for(int i = 0; i<n; i++){
            String tmp = br.readLine();
            for(int j = 0; j<m; j++){
                if(tmp.charAt(j) == 'S'){
                    start[0] = i;
                    start[1] = j;
                }
                board[i][j] = tmp.charAt(j);
            }
        }

        System.out.println(bfs(start[0],start[1]));
    }

    public static int bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a, b, 0});
        visited[a][b] = true;

        int[] dirx = {1,-1,0,0};
        int[] diry = {0,0,1,-1};

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];

            if(board[x][y] == 'T') return time;

//            System.out.println(time + " : " + x + ", " + y);

            for(int i  = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
//                System.out.println(i + "일때 바로 직전의 상황 : " + nx + ", " + ny);

                if(nx<0 || nx>=n || ny<0 || ny>=m || board[nx][ny] == '#') continue;

                int countx = 0;
                int county = 0;

                while(true){
                    countx += dirx[i];
                    county += diry[i];
                    int tmpx = x + countx;
                    int tmpy = y + county;
                    if(tmpx<0 || tmpx>=n || tmpy<0 || tmpy>=m || board[tmpx][tmpy] == '#'){
                        countx -= dirx[i];
                        county -= diry[i];
                        break;
                    }
                }

                nx = x + countx - countx/2;
                ny = y + county - county/2;
                
//                System.out.println(x + ", " + y + "로 부터 시작해서 " + countx + "와 " + county + "한 상황");
//                System.out.println(nx + ", " + ny);

                if(!visited[nx][ny]){
//                    System.out.println(time + "번째 여정에서 " + i + "를 향해서 가는데 근본은 " + x + ", " + y + "인데 말이야...");
//                    System.out.println(nx + ", " + ny + "를 향해서 가는 여정에서 " + countx + "와 " + county + "와 함께 ");
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, time+1});
                }
            }
        }
        return -1;
    }
}