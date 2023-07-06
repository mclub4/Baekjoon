import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static long[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            parent = new int[n+1];
            weight = new long[n+1];

            for(int i = 0; i<n+1; i++){
                parent[i] = i;
            }

            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine(), " ");
                char work = st.nextToken().charAt(0);

                if(work == '!'){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    long w = Long.parseLong(st.nextToken());

                    union(a, b, w);
                }
                else{
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

//                    System.out.println("하기전 : " + weight[a] + ", " + weight[b]);
                    if(find(a) != find(b)){
//                        System.out.println(weight[a] + ", " + weight[b]);
                        sb.append("UNKNOWN\n");
                    }
                    else{
//                        System.out.println(weight[a] + ", " + weight[b]);
                        sb.append((weight[b] - weight[a]) + "\n");
                    }
                }
            }
        }

        System.out.println(sb);
    }

    public static int find(int a){
        if(parent[a] == a) return a;

        int tmp = find(parent[a]);
        weight[a] += weight[parent[a]];
        return parent[a] = tmp;
    }

    public static void union(int a, int b, long w){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a == parent_b) return;

        weight[parent_b] = weight[a] - weight[b] + w;
        parent[parent_b] = parent_a;
    }

}
