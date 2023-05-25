import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public int[] parent;
    static public int[] size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        size = new int[n+1];

        ArrayList<Integer>[] adj = new ArrayList[n+1];

        for(int i = 0; i<n+1; i++){
            parent[i] = i;
            size[i] = 1;
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] query = new int[n];

        for(int i = 0; i<n; i++){
            query[i] = Integer.parseInt(br.readLine());
        }

        Stack<Boolean> result = new Stack<>();
        boolean[] visited = new boolean[n+1];
        int farm = 0;

        for(int i = n-1; i>=0; i--){
            int cur = query[i];
            visited[cur] = true;
            farm++;

            for(int next : adj[cur]){
                if(!visited[next]) continue;
                union(cur, next);
            }
            if(farm == size[find(cur)]) result.push(true);
            else result.push(false);
        }

        int size = result.size();
        for(int i = 0; i<size; i++){
            boolean check = result.pop();
            System.out.println(check ? "CONNECT" : "DISCONNECT");
        }
        System.out.println("DISCONNECT");
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
