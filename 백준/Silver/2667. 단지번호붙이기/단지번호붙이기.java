import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n;
    static int[][] board;
    static ArrayList<Integer> house = new ArrayList<>();
    static boolean[][] visited;

    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i<n; i++){
            String tmp = br.readLine();
            for(int j = 0; j<n; j++){
                board[i][j] = Integer.parseInt(String.valueOf(tmp.charAt(j)));
            }
        }

        int count = 0;
        for(int i= 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(!visited[i][j] && board[i][j] == 1){
                    count++;
                    bfs(i,j);
                }
            }
        }

        Collections.sort(house);

        System.out.println(count);

        for(int i = 0; i<house.size(); i++){
            System.out.println(house.get(i));
        }
    }

    public static void bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a,b});
        visited[a][b] = true;

        int count = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            count ++;

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
                if(nx>=0 && ny>=0 && nx<n && ny<n){
                    if(!visited[nx][ny] && board[nx][ny] == 1){
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx,ny});
                    }
                }
            }
        }

        house.add(count);
    }
}
