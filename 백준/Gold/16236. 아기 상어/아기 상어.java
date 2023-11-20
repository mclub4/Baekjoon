import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        int[] start = new int[2];
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp == 9){
                    start[0] = i;
                    start[1] = j;
                    board[i][j] = 0;
                }
                else{
                    board[i][j] = tmp;
                }
            }
        }


        System.out.println(bfs(start[0], start[1]));
    }

    public static int bfs(int a, int b){
        int size = 2;
        int eat = 0;
        int time = 0;

        int startx = a;
        int starty = b;
        
        while(true){
            PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[2] == o2[2]){
                        if(o1[0] == o2[0]) return o1[1] - o2[1];
                        return  o1[0] - o2[0];
                    }
                    return o1[2] - o2[2];
                }
            });
            queue.add(new int[]{startx, starty, 0});
            int[] dirx = {-1,0,1,0};
            int[] diry = {0,-1,0,1};
            boolean[][] visited = new boolean[n][n];
            visited[startx][starty] = true;
            boolean ate = false;

            while(!queue.isEmpty()){
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                if(board[x][y] < size && board[x][y] !=0){
                    ate = true;
                    eat ++;
                    time += cur[2];
                    board[x][y] = 0;
                    startx = x;
                    starty = y;
                    break;
                }

                for(int i = 0; i<4; i++){
                    int nx = x + dirx[i];
                    int ny = y + diry[i];

                    if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                    if(board[nx][ny] > size || visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, cur[2]+1});
                }
            }

            if(!ate) return time;

            if(eat == size){
                eat = 0;
                size++;
            }
        }
    }
}