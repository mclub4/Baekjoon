import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] parent;
    static ArrayList<Edge> edgelist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        edgelist = new ArrayList<>(m);

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgelist.add(new Edge(start, end, cost));
        }

        Collections.sort(edgelist);

        int result = 1;


        for(int i = 0; i<m; i++){
            Edge tmp = edgelist.get(i);
            if(tmp.cost != (i+1)) break;
            if(find(tmp.start) != find(tmp.end)){
                union(tmp.start, tmp.end);
                result++;
            }
            else break;
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
            if (parent_a < parent_b) {
                parent[parent_b] = parent_a;
            } else {
                parent[parent_a] = parent_b;
            }
        }
    }
}