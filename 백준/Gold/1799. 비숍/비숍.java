import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int tmp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        tmp = 0;
        dfs(0, 0, 0);
        answer += tmp;
        tmp = 0;
        dfs(0, 1, 0);
        answer += tmp;

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int cnt) {
        if (x >= n) {
            tmp = Math.max(tmp, cnt);
            return;
        }

        int nx = x, ny = y;
        ny += 2;
        if (ny >= n) {
            nx += 1;
            ny = ny % 2 == 0 ? 1 : 0;
        }

        if (board[x][y] == 1) {
            if (check(x, y)) {
                board[x][y] = 2;
                dfs(nx, ny, cnt + 1);
                board[x][y] = 1;
            }
        }

        dfs(nx, ny, cnt);
    }

    public static boolean check(int x, int y) {
        int[] dirx = {1, 1, -1, -1};
        int[] diry = {1, -1, 1, -1};

        for (int i = 0; i < 4; i++) {
            int dis = 1;
            while (true) {
                int nx = x + dis * dirx[i];
                int ny = y + dis * diry[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
                if (board[nx][ny] == 2) return false;
                dis++;
            }
        }

        return true;
    }
}
