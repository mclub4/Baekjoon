import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t, n;
        t = Integer.parseInt(br.readLine());

        for(int i = 0; i<t; i++){
            n = Integer.parseInt(br.readLine());
            parent = new int[n+1];
            distance = new int[n+1];

            for(int j = 1; j<n+1; j++){
                parent[j] = j;
            }

            StringTokenizer st;

            while(true){
                st = new StringTokenizer(br.readLine(), " ");
                String tmp = st.nextToken();

                if(tmp.equals("E")){
                    int a = Integer.parseInt(st.nextToken());
                    find(a);
                    System.out.println(distance[a]);
                }
                else if(tmp.equals("I")){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    combine(a,b);
                }
                else if(tmp.equals("O")){
                    break;
                }
            }

        }
    }

    public static int find(int a){
        if(parent[a] == a) return a;

        int tmp = find(parent[a]);
        distance[a] += distance[parent[a]];
        parent[a] = tmp;
        return tmp;
    }

    public static void combine(int a, int b){
        parent[a] = b;
        distance[a] = Math.abs(a-b)%1000;
    }
}