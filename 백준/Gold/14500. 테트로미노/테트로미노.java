import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static boolean[][] visited;
    static int max, n, m;
    static int[] dirx = {1, -1, 0, 0};
    static int[] diry = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    public static void dfs(int x, int y, int sum, int count) {
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dirx[i];
            int ny = y + diry[i];
            
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
            if (count == 2) {
                visited[nx][ny] = true;
                dfs(x, y, sum + board[nx][ny], count + 1);
                visited[nx][ny] = false;
            }

            visited[nx][ny] = true;
            dfs(nx, ny, sum + board[nx][ny], count + 1);
            visited[nx][ny] = false;
        }
    }
}