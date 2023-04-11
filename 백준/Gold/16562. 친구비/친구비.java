import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        cost = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i<n+1; i++){
            parent[i] = i;
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

        int result = Arrays.stream(cost).sum();

        if(result<=k) System.out.print(result);
        else System.out.print("Oh no");
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a != parent_b){
            if (parent_a < parent_b) {
                parent[parent_b] = parent_a;
                cost[parent_a] = Math.min(cost[parent_a], cost[parent_b]);
                cost[parent_b] = 0;
            } else {
                parent[parent_a] = parent_b;
                cost[parent_b] = Math.min(cost[parent_a], cost[parent_b]);
                cost[parent_a] = 0;
            }
        }
    }

}