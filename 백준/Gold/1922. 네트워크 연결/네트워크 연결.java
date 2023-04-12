import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static int result;
    static public class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<Node> nodelist = new ArrayList<>(n);
        parent = new int[n+1];

        for(int i = 0; i<n+1; i++){
            parent[i] = i;
        }

        int result = 0;
        for(int i = 0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodelist.add(new Node(start, end, cost));
        }

        Collections.sort(nodelist);

        for(int i = 0; i<nodelist.size(); i++){
            Node tmp = nodelist.get(i);
            if(find(tmp.start) != find(tmp.end)){
                union(tmp.start, tmp.end);
                result += tmp.cost;
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
            if (parent_a < parent_b) {
                parent[parent_b] = parent_a;
            } else {
                parent[parent_a] = parent_b;
            }
        }
    }
}