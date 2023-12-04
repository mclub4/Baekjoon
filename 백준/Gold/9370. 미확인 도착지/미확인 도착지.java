import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Node implements Comparable<Node> {
        int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static ArrayList<Node>[] nodelist;
    static int[] dist;
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
                if ((a == g && b == h) || (a == h && b == g)) {
                    nodelist[a].add(new Node(b, d * 2 - 1));
                    nodelist[b].add(new Node(a, d * 2 - 1));
                } else {
                    nodelist[a].add(new Node(b, d * 2));
                    nodelist[b].add(new Node(a, d * 2));
                }
            }

            dist = new int[n+1];
            Arrays.fill(dist, 100000000);

            ArrayList<Integer> destination = new ArrayList<>();
            for (int j = 0; j < t; j++)
                destination.add(Integer.parseInt(br.readLine()));

            dijkstra(s);

            Collections.sort(destination);

            //만약 최종 간선의 최단 경로가 홀수다? 그럼 g-h경로를 포함한 것임 따라서 답
            for (int num : destination) {
                if (dist[num] % 2 == 1) sb.append(num + " ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    //그냥 다익스트라 알고리즘
    public static void dijkstra(int s){
        boolean[] check = new boolean[n+1];

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(s, 0));
        dist[s] = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            int end = cur.end;

            if(check[end] == true) continue;
            check[end] = true;

            for(Node node : nodelist[end]){
                if(!check[node.end] && dist[node.end] > dist[end] + node.weight){
                    dist[node.end] = dist[end] + node.weight;
                    queue.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }
}