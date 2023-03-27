import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        List<String> temp = new ArrayList<>();

        for(int i = 0; i<n+1; i++){
            parent[i] = i;
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int check = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(check == 0){
                combine(a,b);
            }
            else{
                boolean result = iscombined(a, b);
                if(result) temp.add("YES");
                else temp.add("NO");
            }
        }

        for(int i = 0; i<temp.size(); i++){
            System.out.println(temp.get(i));
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
            } else {
                parent[parent_a] = parent_b;
            }
        }
    }

    public static boolean iscombined(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a == parent_b) return true;
        else return false;
    }

}
