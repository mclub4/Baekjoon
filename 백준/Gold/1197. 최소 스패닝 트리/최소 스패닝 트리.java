import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node>{
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

    static int[] parent;
    static ArrayList<Node> nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        parent = new int [v+1];
        nodeList = new ArrayList<>(e);


        for(int i = 1; i<v+1; i++){
            parent[i] = i;
        }

        for(int i = 0; i<e; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            Node tmp = new Node(start, end, cost);
            nodeList.add(tmp);
        }

        Collections.sort(nodeList);

        int answer = 0;

        for(int i = 0; i<e; i++){
            Node tmp = nodeList.get(i);
            if(find(tmp.start) != find(tmp.end)){
                union(tmp.start, tmp.end);
                answer += tmp.cost;
            }
        }

        System.out.println(answer);
    }

    public static int find(int v){
        if(parent[v] == v) return v;
        return parent[v] = find(parent[v]);
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