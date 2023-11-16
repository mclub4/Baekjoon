import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] city;
    static int n;
    static int m;
    static boolean visited[][];
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        city = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i<n; i++){
            String[] strings = br.readLine().split("");
            for(int j = 0; j<m; j++){
                city[i][j] = Integer.parseInt(strings[j]);
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(0,0,0));
        visited[0][0] = true;


        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.x == (n-1) && cur.y == (m-1)) return cur.count;

            for(int i = 0; i<4; i++){
                int next_x = cur.x + dirx[i];
                int next_y = cur.y + diry[i];
                if(next_y>=0 && next_x>=0 && next_x<n && next_y<m){
                    if(!visited[next_x][next_y] && city[next_x][next_y] == 0){
                        visited[next_x][next_y] = true;
                        queue.add(new Node(next_x, next_y, cur.count));
                    }
                    else if(!visited[next_x][next_y] && city[next_x][next_y] == 1){
                        visited[next_x][next_y] = true;
                        queue.add(new Node(next_x, next_y, cur.count+1));
                    }
                }
            }
        }

        return 0;
    }

    public static class Node implements Comparable<Node>{
        int x;
        int y;
        int count;
        int color;

        public Node(int x,int y,int count){
            this.x = x;
            this.y = y;
            this.count = count;
            this.color = city[x][y];
        }

        @Override
        public int compareTo(Node o){
            if(this.color == o.color) return this.count - o.count;
            return this.color - o.color;
        }
    }
}