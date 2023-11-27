import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node>{
        int end, weight;

        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int v,e,k;
    static ArrayList<Node>[] nodelist;
    static int[] dist;

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        nodelist = new ArrayList[v+1];
        dist = new int[v+1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i = 1; i<=v; i++){
            nodelist[i] = new ArrayList<>();
        }

        for(int i = 0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            nodelist[start].add(new Node(end, weight));
        }

        dijkstra(k);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=v; i++){
            if(dist[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(dist[i] + "\n");
        }

        System.out.println(sb);
    }

    public static void dijkstra(int s){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] check = new boolean[v+1];
        queue.add(new Node(s, 0));
        dist[s] = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            int end = cur.end;

            if(check[end] == true) continue;
            check[end] = true;

            for(Node node : nodelist[end]){
                if(dist[node.end] > dist[end] + node.weight){
                    dist[node.end] = dist[end] + node.weight;
                    queue.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }


}
