import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static char[][] board;
    static int[] parent;
    static boolean[] visited;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        parent = new int[n*m];
        visited = new boolean[n*m];

        for(int i = 0; i<parent.length; i++){
            parent[i] = i;
        }

        for(int i =0; i<n; i++){
            String tmp = br.readLine();
            for(int j=0; j<m; j++){
                board[i][j] = tmp.charAt(j);
            }
        }
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                int curidx = getidx(i,j);
                if(!visited[curidx]){
                    int nextidx;
                    if(board[i][j] == 'D') nextidx = getidx(i+1, j);
                    else if(board[i][j] == 'L') nextidx = getidx(i, j-1);
                    else if(board[i][j] == 'R') nextidx = getidx(i, j+1);
                    else nextidx = getidx(i-1, j);
                    if(find(curidx) != find(nextidx)){
                        union(curidx, nextidx);
                    }
                    visited[curidx] = true;
                }
            }
        }
        
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i<parent.length; i++){
            set.add(find(i));
        }

        System.out.println(set.size());
    }

    public static int getidx(int i, int j){
        return i*m + j;
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a != parent_b){
            if(parent_a<parent_b) parent[parent_b] = parent_a;
            else parent[parent_a] = parent_b;
        }
    }
}