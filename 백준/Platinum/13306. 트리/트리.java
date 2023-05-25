import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        q += n-1;

        parent = new int[n+1];
        int[] high = new int[n+1];

        high[1] = 1;

        for(int i = 2; i<n+1; i++){
            high[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
        }

        int[][] query = new int[q][3];

        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine(), " ");
            query[i][0] = Integer.parseInt(st.nextToken());
            query[i][1] = Integer.parseInt(st.nextToken());
            if(query[i][0] == 1) query[i][2] = Integer.parseInt(st.nextToken());
        }

        Stack<Boolean> result = new Stack<>();

        for(int i = q-1; i>=0; i--){
            if(query[i][0] == 0){
                union(query[i][1], high[query[i][1]]);
            }
            else{
                if(find(query[i][1]) == find(query[i][2])) result.push(true);
                else result.push(false);
            }
        }

        int size = result.size();
        for(int i = 0; i<size; i++){
            boolean check = result.pop();
            System.out.println(check ? "YES" : "NO");
        }
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a != parent_b){
            if(parent_a < parent_b) parent[parent_b] = parent_a;
            else parent[parent_a] = parent_b;
        }
    }
}
