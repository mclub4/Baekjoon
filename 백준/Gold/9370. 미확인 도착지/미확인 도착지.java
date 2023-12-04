import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    static ArrayList<Node>[] nodelist;
    static int[] destination;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(test-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            nodelist = new ArrayList[n+1];
            for(int i = 1; i<n+1; i++){
                nodelist[i] = new ArrayList<>();
            }

            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                nodelist[a].add(new Node(b, d));
                nodelist[b].add(new Node(a, d));
            }

            destination = new int[t];
            for(int i = 0; i<t; i++){
                destination[i] = Integer.parseInt(br.readLine());
            }

            ArrayList<Integer> answer = new ArrayList<>();
            for(int next : destination){
                int case1 = dijkstra(s,h) + dijkstra(h, g) + dijkstra(g, next);
                int case2 = dijkstra(s,g) + dijkstra(g, h) + dijkstra(h, next);
                int base = dijkstra(s, next);
                if(Math.min(case1, case2) == base) answer.add(next);
            }

            Collections.sort(answer);
            for(int a : answer){
                sb.append(a + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static int dijkstra(int s, int e){
        boolean[] check = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>();
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

        return dist[e];
    }
}