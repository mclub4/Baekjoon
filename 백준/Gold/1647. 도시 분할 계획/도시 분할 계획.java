import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    public static class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i<n+1; i++){
            parent[i] = i;
        }

        ArrayList<Node> nodelist = new ArrayList<>();

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodelist.add(new Node(start, end, cost));
        }

        Collections.sort(nodelist);

        int cost = 0;
        int max = 0;

        for(Node node:nodelist){
            if(find(node.start) != find(node.end)){
                union(node.start, node.end);
                max = node.cost;
                cost += node.cost;
            }
        }

        System.out.println(cost - max);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a != parent_b){
            if(parent_a<parent_b) parent[parent_b] = parent_a;
            else parent[parent_a] = parent_b;
        }
    }
}
