import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int max = 0;
    static int n, m;
    static ArrayList<Point> cctv;
    static int[] dirx = {1, -1, 0, 0};
    static int[] diry = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        cctv = new ArrayList<Point>();

        int answer = n * m;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= board[i][j] && board[i][j] < 6) {
                    answer--;
                    cctv.add(new Point(i, j));
                }
                if (board[i][j] == 6) answer--;
            }
        }

        backtracking(0, board, 0);
//        System.out.println(answer + "는 전체 개수");
        System.out.println(answer - max);
    }

    public static void backtracking(int depth, int[][] map, int count) {
        if (depth == cctv.size()) {
//            if (count >= max) System.out.println("지금이 최대야");
            max = Math.max(max, count);
//            System.out.println(count + "가 나왔고 " + max + "가 최대임");
            return;
        }

        Point cur = cctv.get(depth);
        switch (board[cur.x][cur.y]) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    int[][] copy = copyboard(map);
                    int tmp = count + observe(cur, i, copy);
//                    System.out.println("case " + 1);
//                    System.out.println(i + "번째 진입");
//                    for (int a = 0; a < n; a++) {
//                        for (int b = 0; b < m; b++) {
//                            System.out.print(copy[a][b] + " ");
//                        }
//                        System.out.println("");
//                    }
                    backtracking(depth + 1, copy, tmp);
                }
                break;
            case 2:
                for (int i = 0; i < 4; i += 2) {
                    int[][] copy = copyboard(map);
                    int tmp = count + observe(cur, i, copy) + observe(cur, i + 1, copy);
//                    System.out.println("case " + 2);
//                    System.out.println(i + "번째 진입");
//                    for (int a = 0; a < n; a++) {
//                        for (int b = 0; b < m; b++) {
//                            System.out.print(copy[a][b] + " ");
//                        }
//                        System.out.println("");
//                    }
//                    System.out.println("현재 개수 " + tmp);
                    backtracking(depth + 1, copy, tmp);
                }
                break;
            case 3:
                for (int i = 0; i < 2; i++) {
                    for (int j = 2; j < 4; j++) {
                        int[][] copy = copyboard(map);
                        int tmp = count + observe(cur, i, copy) + observe(cur, j, copy);
//                        System.out.println("case " + 3);
//                        System.out.println(i + "와 " + j + "번째 진입");
//                        for (int a = 0; a < n; a++) {
//                            for (int b = 0; b < m; b++) {
//                                System.out.print(copy[a][b] + " ");
//                            }
//                            System.out.println("");
//                        }
                        backtracking(depth + 1, copy, tmp);
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    int[][] copy = copyboard(map);
                    int tmp = count;
                    for (int j = 0; j < 4; j++) {
                        if (j == i) continue;
                        tmp += observe(cur, j, copy);
                    }
//                    System.out.println("case " + 4);
//                    System.out.println(i + "번째 진입");
//                    for (int a = 0; a < n; a++) {
//                        for (int b = 0; b < m; b++) {
//                            System.out.print(copy[a][b] + " ");
//                        }
//                        System.out.println("");
//                    }
//                    System.out.println("현재 개수 " + tmp);
                    backtracking(depth + 1, copy, tmp);
                }
                break;
            case 5:
//                System.out.println("case " + 5);
                int[][] copy = copyboard(map);
                int tmp = count;
                for (int i = 0; i < 4; i++) tmp += observe(cur, i, copy);
//                for (int a = 0; a < n; a++) {
//                    for (int b = 0; b < m; b++) {
//                        System.out.print(copy[a][b] + " ");
//                    }
//                    System.out.println("");
//                }
                backtracking(depth + 1, copy, tmp);
                break;
        }
    }

    public static int observe(Point cctv, int dir, int[][] map) {
        int ret = 1;
        int count = 0;
        while (true) {
            int nx = cctv.x + dirx[dir] * ret;
            int ny = cctv.y + diry[dir] * ret;
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) break;
            if (map[nx][ny] == 6) break;
            ret++;
            if (map[nx][ny] != 0) continue;
            count++;
            map[nx][ny] = 7;
        }
        return count;
    }

    public static int[][] copyboard(int[][] map) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }

        return copy;
    }
}