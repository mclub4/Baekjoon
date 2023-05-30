import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    public static class Node implements Comparable<Node>{
        int start;
        int end;
        double cost;

        public Node(int start, int end, double cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            double tmp = this.cost-o.cost;
            if(tmp>=0) return 1;
            else return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i = 1; i<n+1; i++){
            parent[i] = i;
        }

        ArrayList<Node> nodelist = new ArrayList<>();
        double[][] star = new double[n][2];


        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            star[i][0] = x;
            star[i][1] = y;
        }

        for(int i = 0; i<n-1; i++){
            for(int j = i+1; j<n; j++){
                double x = star[j][0] - star[i][0];
                double y = star[j][1] - star[i][1];
                double distance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
                nodelist.add(new Node(i,j, distance));
            }
        }

        Collections.sort(nodelist);

        double cost = 0;

        for(Node node:nodelist){
            if(find(node.start) != find(node.end)){
                union(node.start, node.end);
                cost += node.cost;
            }
        }

        String format = String.format("%.2f", cost);
        System.out.println(format);
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
