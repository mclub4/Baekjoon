import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean[][][] visited;
    static int n, m, k;
    static int end_x, end_y;
    static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int start_x = -1, start_y = -1;
        board = new int[n][m];
        idx = 1;

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                char cur = tmp.charAt(j);
                if (cur == 'S') {
                    board[i][j] = 0;
                    start_x = i;
                    start_y = j;
                } else if (cur == 'X') {
                    board[i][j] = idx;
                    idx++;
                } else if (cur == 'E') board[i][j] = -2;
                else if (cur == '#') board[i][j] = -1;
                else board[i][j] = 0;
            }
        }

        visited = new boolean[n][m][1 << (idx - 1)];
        System.out.println(bfs(start_x, start_y));
    }

    public static int bfs(int a, int b) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a, b, 0, 0});

        int[] dirx = {1, -1, 0, 0};
        int[] diry = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];
            int state = cur[3];
            
            if (board[x][y] == -2 && state == (1 << idx - 1) - 1) return time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || board[nx][ny] == -1) continue;

                if (board[nx][ny] > 0) {
                    int ns = (state | (1 << (board[nx][ny] - 1)));
                    if (!visited[nx][ny][ns]) {
                        visited[nx][ny][ns] = true;
                        queue.add(new int[]{nx, ny, time + 1, ns});
                    }
                } else if (!visited[nx][ny][state]) {
                    visited[nx][ny][state] = true;
                    queue.add(new int[]{nx, ny, time + 1, state});
                }
            }
        }

        return -1;
    }
}