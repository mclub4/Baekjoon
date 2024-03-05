import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Marble {
        int x;
        int y;

        public Marble(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Pair {
        Marble blue;
        Marble red;
        int cnt;

        public Pair(Marble red, Marble blue, int cnt) {
            this.red = red;
            this.blue = blue;
            this.cnt = cnt;
        }
    }

    static char[][] board;
    static boolean[][][][] visited;
    static int n, m;
    static int gx, gy;
    static Marble red;
    static Marble blue;
    static int[] dirx = {1, -1, 0, 0};
    static int[] diry = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                char now = tmp.charAt(j);
                if (now == 'R') red = new Marble(i, j);
                else if (now == 'B') blue = new Marble(i, j);
                else if (now == 'O') {
                    gx = i;
                    gy = j;
                }
                board[i][j] = now;
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        visited[red.x][red.y][blue.x][blue.y] = true;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(red, blue, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            if (pair.cnt > 10) return -1;
            if (pair.red.x == gx && pair.red.y == gy) return pair.cnt;

            for (int i = 0; i < 4; i++) {
                Marble newRed = move(pair.red, i);
                Marble newBlue = move(pair.blue, i);

                if (newBlue.x == gx && newBlue.y == gy) continue;

                if (newBlue.x == newRed.x && newBlue.y == newRed.y) {
                    if (i == 0) {
                        if (pair.blue.x > pair.red.x) newRed.x--;
                        else newBlue.x--;
                    } else if (i == 1) {
                        if (pair.blue.x > pair.red.x) newBlue.x++;
                        else newRed.x++;
                    } else if (i == 2) {
                        if (pair.blue.y > pair.red.y) newRed.y--;
                        else newBlue.y--;
                    } else if (i == 3) {
                        if (pair.blue.y > pair.red.y) newBlue.y++;
                        else newRed.y++;
                    }
                }

                if (!visited[newRed.x][newRed.y][newBlue.x][newBlue.y]) {
                    visited[newRed.x][newRed.y][newBlue.x][newBlue.y] = true;
                    queue.add(new Pair(newRed, newBlue, pair.cnt + 1));
                }
            }

        }

        return -1;
    }

    public static Marble move(Marble marble, int dir) {
        int x = marble.x;
        int y = marble.y;

        while (board[x + dirx[dir]][y + diry[dir]] != '#') {
            x += dirx[dir];
            y += diry[dir];
            if (x == gx && y == gy) break;
        }

        return new Marble(x, y);
    }
}
