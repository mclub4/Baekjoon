import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n,k;

    // LCA 변수
    static int[] depth;
    static int[][] sparsetable; // parent[K][V] : 정점 V의 2^k번째 조상정점 번호

    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        k = 0;

        for(int i=1; i<n+1; i*=2){
            k++;
        }

        depth = new int[n+1];
        sparsetable = new int[k][n+1];
        tree = new ArrayList[n+1];

        for(int i=1; i<n+1; i++){
            tree[i] = new ArrayList<Integer>();
        }

        for(int i=1; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1,1);
        maketable();

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a, b)+"\n");
        }

        System.out.println(sb);
    }

    public static void dfs(int v, int cnt) {
        depth[v] = cnt;

        for(int i=0; i<tree[v].size(); i++) {
            int next = tree[v].get(i);
            if(depth[next] == 0) {
                dfs(next, cnt+1);
                sparsetable[0][next] = v;
            }
        }
    }

    public static void maketable() {
        for(int i=1; i<k; i++) {
            for(int j=1; j<n+1; j++) {
                sparsetable[i][j] = sparsetable[i-1][sparsetable[i-1][j]];
            }
        }
    }

    public static int LCA(int a, int b) {
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        for(int i=k-1; i>=0 ; i--) {
            if(Math.pow(2, i) <= depth[a]-depth[b])
                a = sparsetable[i][a];
        }
        if(a==b) return a;
        for(int i=k-1; i>=0; i--) {
            if(sparsetable[i][a] != sparsetable[i][b]) {
                a = sparsetable[i][a];
                b = sparsetable[i][b];
            }
        }

        return sparsetable[0][a];
    }
}