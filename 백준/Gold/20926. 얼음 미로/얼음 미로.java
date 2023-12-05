import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node>{
        int x,y,d;

        public Node(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }
    static int n,m;
    static char[][] board;
    static boolean[][] visited;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[m][n];
        dist = new int[m][n];
        visited = new boolean[m][n];

        for(int i = 0; i<m; i++){
            Arrays.fill(dist[i], 500*500*9);
        }

        int start_x = -1, start_y = -1, end_x = -1, end_y = -1;

        for(int i = 0; i<m; i++){
            String tmp = br.readLine();
            for(int j = 0; j<n; j++){
                char cur = tmp.charAt(j);
                board[i][j] = cur;
                if(cur == 'T'){
                    start_x = i;
                    start_y = j;
                }
                else if(cur == 'E'){
                    end_x = i;
                    end_y = j;
                }
            }
        }

        dijkstra(start_x, start_y);
        System.out.println(dist[end_x][end_y] == 500*500*9 ? -1 : dist[end_x][end_y]);
    }

    public static void dijkstra(int start_x, int start_y){
        int[] dirx = {1,-1,0,0};
        int[] diry = {0,0,1,-1};

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start_x,start_y,0));
        dist[start_x][start_y] = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;

            for(int i = 0; i<4; i++){
                int check = slide(cur.x,cur.y, dirx[i], diry[i]);
                if(check == -1) continue;

                int distance = 0;

                for(int j = 1; j< check+1; j++){
                    if(board[cur.x + j*dirx[i]][cur.y + j*diry[i]] == 'E') break;
                    distance += (board[cur.x + j*dirx[i]][cur.y + j*diry[i]] - '0');
                }

                int nx = cur.x + check*dirx[i];
                int ny = cur.y + check*diry[i];
                if(!visited[nx][ny] && dist[nx][ny] > dist[cur.x][cur.y] + distance){
                    dist[nx][ny] = dist[cur.x][cur.y] + distance;
                    if(board[nx][ny] != 'E') queue.add(new Node(nx, ny, dist[nx][ny]));
                }

            }
        }
    }

    public static int slide(int x, int y, int dirx, int diry){
        int count = 1;
        while(true){
            int nx = x + dirx*count;
            int ny = y + diry*count;

            if(nx<0 || ny<0 || nx>=m || ny>=n || board[nx][ny] == 'H'){
                count = -1;
                break;
            }
            if(board[nx][ny] == 'R'){
                count -= 1;
                break;
            }
            if(board[nx][ny] == 'E') break;
            count ++;
        }

        return count;
    }
}