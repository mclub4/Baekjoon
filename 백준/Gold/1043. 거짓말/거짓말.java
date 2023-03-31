import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static boolean[] truth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        truth = new boolean[n+1];

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
        }



        st = new StringTokenizer(br.readLine(), " ");
        int tmp = Integer.parseInt(st.nextToken());
        for(int i = 0; i<tmp; i++){
            int exposer = Integer.parseInt(st.nextToken());
            truth[exposer] = true;
        }

        ArrayList<Integer>[] party = new ArrayList[m];
        for(int i = 0; i<m; i++){
            party[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int count = Integer.parseInt(st.nextToken());
            int pre, cur;
            if(count == 1){
                pre = Integer.parseInt(st.nextToken());
                party[i].add(pre);
            }
            else{
                pre = Integer.parseInt(st.nextToken());
                party[i].add(pre);
                for(int j = 0; j<count-1; j++){
                    cur = Integer.parseInt(st.nextToken());
                    party[i].add(cur);
                    union(pre, cur);
                    pre = cur;
                }
            }
        }

        for(int i = 1; i<n+1; i++){
            if(truth[i]){
                int exposer = find(i);
                truth[exposer] = true;
            }
        }

        int result = 0;
        for(int i = 0; i<m; i++){
            if(party[i].size()>0){
                for(int j = 0; j<party[i].size(); j++){
                    int exposer = find(party[i].get(j));
                    if(truth[exposer]) break;
                    if(j == party[i].size()-1) result ++;
                }
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
            if(parent_a<parent_b){
                parent[parent_b] = parent_a;
            }
            else{
                parent[parent_a] = parent_b;
            }
        }
    }
}