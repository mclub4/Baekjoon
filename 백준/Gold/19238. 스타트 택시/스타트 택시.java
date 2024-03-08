import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, f, c;
    static int[][] board;
    static int[][] start;
    static int[][] end;
    static int[] dirx = {1, -1, 0, 0};
    static int[] diry = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        c = m;

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Point taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        start = new int[m][2];
        end = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            start[i][0] = Integer.parseInt(st.nextToken()) - 1;
            start[i][1] = Integer.parseInt(st.nextToken()) - 1;
            board[start[i][0]][start[i][1]] = i + 2;
            end[i][0] = Integer.parseInt(st.nextToken()) - 1;
            end[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        boolean flag = false;
        for (int i = 0; i < m; i++) {
            Point next_customer = find_customer(taxi);
//            System.out.println(next_customer.x + " : " + next_customer.y);
            if (next_customer.x == -1) {
                flag = true;
                break;
            }
//            System.out.println(next_customer.x + "번째 손님을 대리고 " + next_customer.y + " 출발합니다!");
            board[start[next_customer.x][0]][start[next_customer.x][1]] = 0;
            taxi = new Point(start[next_customer.x][0], start[next_customer.x][1]);
            f -= next_customer.y;
            int go = drive(next_customer.x);
//            System.out.println(next_customer.x + " : " + go);
            if (go == -1) {
                flag = true;
                break;
            }

            f += go;
            taxi = new Point(end[next_customer.x][0], end[next_customer.x][1]);
            c--;
        }

        if (flag) System.out.println(-1);
        else System.out.println(f);
    }

    static class Customer implements Comparable<Customer> {
        int x;
        int y;
        int num;
        int dist;

        public Customer(Point point, int num, int dist) {
            this.x = point.x;
            this.y = point.y;
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Customer o) {
            if (this.dist == o.dist) {
                if (this.x == o.x) {
                    return this.y - o.y;
                } else return this.x - o.x;

            } else return this.dist - o.dist;
        }
    }

    public static Point find_customer(Point taxi) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{taxi.x, taxi.y, 0});
        PriorityQueue<Customer> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];
        visited[taxi.x][taxi.y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (board[cur[0]][cur[1]] >= 2)
                pq.add(new Customer(new Point(cur[0], cur[1]), board[cur[0]][cur[1]] - 2, cur[2]));

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dirx[i];
                int ny = cur[1] + diry[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == 1 || visited[nx][ny]) continue;
//                System.out.println((nx + 1) + ", " + (ny + 1) + " : " + (cur[2] + 1));
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, cur[2] + 1});
            }
        }

        Customer min = pq.poll();
//        System.out.println(min.dist + "와 " + min.num);
//        Customer min2 = pq.poll();
//        System.out.println(min2.dist + "와 " + min2.num);

        return min == null || min.dist >= f ? new Point(-1, -1) : new Point(min.num, min.dist);
    }

    public static int drive(int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[num][0], start[num][1], 0});
        boolean[][] visited = new boolean[n][n];
        visited[start[num][0]][start[num][1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[2] > f) return -1;
            if (cur[0] == end[num][0] && cur[1] == end[num][1]) {
//                System.out.println(cur[0] + " , " + cur[1] + "도달");
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dirx[i];
                int ny = cur[1] + diry[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == 1 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, cur[2] + 1});
            }
        }

        return -1;
    }
}