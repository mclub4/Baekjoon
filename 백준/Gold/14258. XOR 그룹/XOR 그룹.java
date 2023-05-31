import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static long t;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static int[][] board;
    static boolean[][] visited;
    static int[] parent;
    static int[] xor;
    public static class Node implements Comparable<Node>{
        int x;
        int y;
        int value;

        public Node(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        board = new int[n][m];
        visited = new boolean[n][m];
        parent = new int[n*m];
        xor = new int[n*m];

        for(int i = 0; i<n*m; i++){
            parent[i] = i;
        }

        ArrayList<Node> nodelist = new ArrayList<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<m; j++){
                int value = Integer.parseInt(st.nextToken());
                board[i][j] = value;
                xor[i*m+j] = value;
                nodelist.add(new Node(i,j,value));
            }
        }

        Collections.sort(nodelist, Collections.reverseOrder());

        long max = 0;
        t = 0;
        for(Node node:nodelist){
            int x = node.x;
            int y = node.y;
            visited[x][y] = true;
//            System.out.println(x + ", " + y + " : " + node.value);

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];
                if(nx>=0 && ny>=0 && nx<n && ny<m && visited[nx][ny]){
                    union(x*m+y, nx*m+ny);
                }
            }

            t += xor[find(x*m+y)];

//            System.out.println("이때 t 값은 " + t);
            max = Math.max(max, t);
        }

        System.out.println(max);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a != parent_b){
            parent[parent_a] = parent_b;
//            System.out.println(board[a/m][a%m] + ", " + board[b/m][b%m]  + "를 넣었더니");
//            System.out.println(xor[parent_a] + "과 " + xor[parent_b]);
            t -= xor[parent_b];
            xor[parent_b] = xor[parent_a] ^ xor[parent_b];
//            System.out.println(xor[parent_a] + "과 " + xor[parent_b] + "로 변함");
        }
    }
}
