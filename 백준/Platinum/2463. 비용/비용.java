import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static class Edge implements Comparable<Edge>{
        int x;
        int y;
        int w;

        public Edge(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return o.w - this.w;
        }
    }

    static int[] parent;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Edge> query = new ArrayList<>();

        int x,y,w;

        long total = 0;

        parent = new int[n+1];
        size = new int[n+1];

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
            size[i] = 1;
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            if(x>y){
                int tmp = y;
                y = x;
                x = tmp;
            }

            total += w;
            query.add(new Edge(x,y,w));
        }

        Collections.sort(query);

        long cost = 0;

        for(int i = 0; i<query.size(); i++){
            Edge cur = query.get(i);

            cost += (total*union(cur.x, cur.y));
            cost %= 1000000000;
            total -= cur.w;
        }

        System.out.println(cost);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static long union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a == parent_b) return 0;

        long cnt = (long) size[parent_a]*size[parent_b];
        parent[parent_b] = parent_a;
        size[parent_a] += size[parent_b];
        size[parent_b] = 0;

        return cnt;
    }

}