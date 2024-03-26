import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Robot {
    int x;
    int y;
    int dir;
    int count;

    public Robot(int x, int y, int dir, int count) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.count = count;
    }
}

public class Main {
    static int m;
    static int n;
    static int[][] board;
    static boolean[][][] visited;
    static Robot start;
    static Robot finish;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[m][n];
        visited = new boolean[m][n][4];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        start = new Robot(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        st = new StringTokenizer(br.readLine());
        finish = new Robot(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Robot> queue = new LinkedList<>();
        visited[start.x][start.y][start.dir] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Robot cur = queue.poll();

            if (cur.x == finish.x && cur.y == finish.y && cur.dir == finish.dir) return cur.count;

            for (int i = 1; i <= 3; i++) {
                int nx = cur.x + (dx[cur.dir] * i);
                int ny = cur.y + (dy[cur.dir] * i);

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (board[nx][ny] == 1) break;

                if (!visited[nx][ny][cur.dir]) {
                    visited[nx][ny][cur.dir] = true;
                    queue.add(new Robot(nx, ny, cur.dir, cur.count + 1));
                }
            }

            for (int i = 0; i < 4; i++) {
                int[] tmp = {0, 2, 1, 3};
                if (cur.dir == i) continue;
                int command = Math.abs(tmp[cur.dir] - tmp[i]);
                if (command == 3) command = 1;
                if (!visited[cur.x][cur.y][i]) {
                    visited[cur.x][cur.y][i] = true;
                    queue.add(new Robot(cur.x, cur.y, i, cur.count + command));
                }
            }
        }

        return 0;
    }
}