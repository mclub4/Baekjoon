import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static boolean[][] visited;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Integer> width = new ArrayList<>();
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(!visited[i][j] && board[i][j] == 1){
                    width.add(bfs(i,j));
                }
            }
        }

        System.out.println(width.size());

        int max = 0;
        for(int i = 0; i< width.size(); i++){
            if(max < width.get(i)) max = width.get(i);
        }

        System.out.println(max);
    }

    public static int bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a,b});
        visited[a][b] = true;
        int width = 1;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m){
                    if(!visited[nx][ny] && board[nx][ny] == 1){
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        width++;
                    }
                }
            }

        }

        return width;
    }
}
