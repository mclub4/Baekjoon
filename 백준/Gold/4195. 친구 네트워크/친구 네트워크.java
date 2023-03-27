import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] count;
    static HashMap<String, Integer> connect;

    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        for(int j = 0; j<n; j++){
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());

            parent = new int[m*2];
            count = new int[m*2];
            Arrays.fill(count, 1);

            for(int i = 0; i<m*2; i++){
                parent[i] = i;
            }

            connect = new HashMap<>(m*2);

            int temp = 0;

            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine(), " ");
                String a = st.nextToken();
                String b = st.nextToken();
                if(!connect.containsKey(a)) connect.put(a, temp++);
                if(!connect.containsKey(b)) connect.put(b, temp++);
                combine(connect.get(a), connect.get(b));
                if(connect.get(a)<connect.get(b)) System.out.println(count[find(connect.get(b))]);
                else System.out.println(count[find(connect.get(a))]);
            }
        }
     }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void combine(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a != parent_b){
            if (parent_a < parent_b) {
                parent[parent_b] = parent_a;
                count[parent_a] += count[parent_b];
            } else {
                parent[parent_a] = parent_b;
                count[parent_b] += count[parent_a];
            }
        }
    }

}
