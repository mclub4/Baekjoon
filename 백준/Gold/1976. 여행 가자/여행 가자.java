import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n+1];

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
        }

        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 1; i<n+1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j<n+1; j++){
                int a = Integer.parseInt(st.nextToken());
                switch(a){
                    case 0:
                        break;
                    case 1:
                        combine(i, j);
                        break;
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());

        String result = "YES";
        for(int i = 1; i<m; i++){
            int next = Integer.parseInt(st.nextToken());
            if(!iscombined(start, next)){
                result = "NO";
                break;
            }
        }

        System.out.println(result);
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
