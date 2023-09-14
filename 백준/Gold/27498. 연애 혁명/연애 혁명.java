import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
        }

        ArrayList<Node> nodelist = new ArrayList<>();
        ArrayList<Node> love = new ArrayList<>();

        int total = 0;

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(d==0){
                nodelist.add(new Node(a,b,c));
                total += c;
            }
            else love.add(new Node(a,b,c));
        }

        for(int i = 0; i<love.size(); i++){
            Node cur = love.get(i);
            union(cur.start, cur.end);
        }

        Collections.sort(nodelist, Collections.reverseOrder());
        
        int answer = 0;

        for(int i = 0; i<nodelist.size(); i++){
            Node cur = nodelist.get(i);

            if(find(parent[cur.start]) != find(parent[cur.end])){
                union(cur.start, cur.end);
                answer += cur.cost;
            }
        }

        System.out.println(total - answer);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return find(parent[a]);
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
