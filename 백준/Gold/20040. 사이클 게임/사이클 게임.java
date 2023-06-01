import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        parent = new int[n];

        for(int i = 0; i<n; i++){
            parent[i] = i;
        }

        int[][] query = new int[m][2];

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            query[i][0] = Integer.parseInt(st.nextToken());
            query[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for(int i = 0; i<m; i++){
            if(find(query[i][0]) != find(query[i][1])) union(query[i][0], query[i][1]);
            else{
                result = i+1;
                break;
            }
        }

        System.out.println(result);
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
