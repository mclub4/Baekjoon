import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int start_x = -1;
        int start_y = -1;
        board = new int[n][m];
        int idx = 1;

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                char cur = tmp.charAt(j);
                if (cur == 'S') {
                    board[i][j] = 0;
                    start_x = i;
                    start_y = j;
                } else if (cur == 'C') {
                    board[i][j] = idx;
                    idx++;
                } else if (cur == '#') {
                    board[i][j] = -1;
                } else {
                    board[i][j] = 0;
                }
            }
        }

        System.out.println(bfs(start_x, start_y));
    }

    static int bfs(int a, int b) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][][] visitied = new boolean[n][m][4][4];
        queue.add(new int[]{a, b, 0, -1, 0});

        int[] dirx = {1, -1, 0, 0};
        int[] diry = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];
            int prev = cur[3];
            int count = cur[4];

            if (count == 3) return time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visitied[nx][ny][i][count]) continue;
                if (board[nx][ny] == -1 || prev == i) continue;
                if ((count != 1 && board[nx][ny] == 1) || (count != 2 && board[nx][ny] == 2)) {
                    visitied[nx][ny][i][board[nx][ny]] = true;
                    queue.add(new int[]{nx, ny, time + 1, i, count + board[nx][ny]});
                } else {
                    visitied[nx][ny][i][count] = true;
                    queue.add(new int[]{nx, ny, time + 1, i, count});
                }
            }
        }

        return -1;
    }
}