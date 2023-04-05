import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Planet{
        int num;
        int x;
        int y;
        int z;

        public Planet(int x, int y, int z, int num){
            this.num = num;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Node implements Comparable<Node> {
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
            return this.cost - o.cost;
        }
    }

    static int[] parent;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    static ArrayList<Planet> planetList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        parent = new int [n+1];
        planetList = new ArrayList<>(n);

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Planet planet = new Planet(x, y, z, i);
            planetList.add(planet);
        }

        Comparator<Planet> cmp = (o1, o2) -> o1.x -o2.x;
        Collections.sort(planetList, cmp);
        for(int i = 1; i<n; i++){
            Planet cur = planetList.get(i-1);
            Planet next = planetList.get(i);
            int cost = Math.abs(cur.x - next.x);
            Node edge = new Node(cur.num, next.num, cost);
            queue.add(edge);
        }

        cmp = (o1, o2) -> o1.y -o2.y;
        Collections.sort(planetList, cmp);
        for(int i = 1; i<n; i++){
            Planet cur = planetList.get(i-1);
            Planet next = planetList.get(i);
            int cost = Math.abs(cur.y - next.y);
            Node edge = new Node(cur.num, next.num, cost);
            queue.add(edge);
        }

        cmp = (o1, o2) -> o1.z -o2.z;
        Collections.sort(planetList, cmp);
        for(int i = 1; i<n; i++){
            Planet cur = planetList.get(i-1);
            Planet next = planetList.get(i);
            int cost = Math.abs(cur.z - next.z);
            Node edge = new Node(cur.num, next.num, cost);
            queue.add(edge);
        }

        int answer = 0;
        int cnt = 0;

        while(!queue.isEmpty()){
            Node tmp = queue.poll();
            if(find(tmp.start) != find(tmp.end)){
                union(tmp.start, tmp.end);
                answer += tmp.cost;
                if(++cnt == n-1) break;
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