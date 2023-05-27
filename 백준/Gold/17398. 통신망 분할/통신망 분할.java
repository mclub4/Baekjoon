import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static long[] size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] edge = new int[m+1][2];

        for(int i = 1; i<m+1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
        }

        parent = new int[n+1];
        size = new long[n+1];

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
            size[i] = 1;
        }

        boolean[] visited = new boolean[m+1];
        int[] query = new int[q];

        for(int i = 1; i<q+1; i++){
            int cur = Integer.parseInt(br.readLine());
            query[i-1] = cur;
            visited[cur] = true;
        }

        for(int i = 1; i<m+1; i++){
            if(!visited[i]){
                union(edge[i][0], edge[i][1]);
            }
        }


        long cost = 0;

        for(int i = q-1; i>=0; i--){
            int a = edge[query[i]][0];
            int b = edge[query[i]][1];
            if(find(a) != find(b)) cost += size[find(a)] * size[find(b)];
            union(a,b);
        }

        System.out.println(cost);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a != parent_b){
            if(parent_a < parent_b){
                parent[parent_b] = parent_a;
                size[parent_a] += size[parent_b];
            }
            else{
                parent[parent_a] = parent_b;
                size[parent_b] += size[parent_a];
            }
        }
    }
}
