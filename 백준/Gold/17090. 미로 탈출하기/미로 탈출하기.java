import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static boolean[][] clear;
    static char[][] board;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        board = new char[n][m];
        clear = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                switch (tmp.charAt(j)) {
                    case 'U':
                        board[i][j] = 0;
                        break;
                    case 'D':
                        board[i][j] = 1;
                        break;
                    case 'L':
                        board[i][j] = 2;
                        break;
                    case 'R':
                        board[i][j] = 3;
                        break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) dfs(i, j);
            }
        }


        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (clear[i][j]) answer++;
            }
        }

        System.out.println(answer);
    }

    static int[] dirx = {-1, 1, 0, 0};
    static int[] diry = {0, 0, -1, 1};

    public static boolean dfs(int x, int y) {
        visited[x][y] = true;

        int nx = x + dirx[board[x][y]];
        int ny = y + diry[board[x][y]];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m || clear[nx][ny]) {
            clear[x][y] = true;
            return true;
        }

        if (visited[nx][ny]) return false;

        return clear[x][y] = dfs(nx, ny);
    }
}
