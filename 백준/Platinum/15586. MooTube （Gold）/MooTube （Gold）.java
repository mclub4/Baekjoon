import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node>{
        int start;
        int end;
        int usado;

        public Node(int start, int end, int usado){
            this.start = start;
            this.end = end;
            this.usado = usado;
        }

        @Override
        public int compareTo(Node o) {
            return o.usado - this.usado;
        }
    }

    public static class Query implements Comparable<Query>{
        int idx;
        int k;
        int v;

        public Query(int idx, int k, int v){
            this.idx = idx;
            this.k = k;
            this.v = v;
        }

        @Override
        public int compareTo(Query o) {
            return o.k - this.k;
        }
    }

    static int[] parent;
    static int[] count;

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int quries = Integer.parseInt(st.nextToken());

        Node[] nodelist = new Node[n-1];

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            nodelist[i] = new Node(p,q,r);
        }

        Arrays.sort(nodelist);

        Query[] querylist = new Query[quries];

        for(int i = 0; i<quries; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            querylist[i] = new Query(i, k,v);
        }

        Arrays.sort(querylist);

        parent = new int[n];
        count = new int[n];

        for(int i = 0; i<n; i++){
            parent[i] = i;
            count[i] = 1;
        }

        int idx = 0;
        int[] answer = new int[quries];
        for(int i = 0; i<quries; i++){
            while(idx<(n-1) && querylist[i].k <= nodelist[idx].usado){
                union((nodelist[idx].start-1), (nodelist[idx].end-1));
                idx ++;
            }
            answer[querylist[i].idx] = count[find(querylist[i].v-1)] - 1;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<quries; i++){
            sb.append(answer[i] + "\n");
        }

        System.out.println(sb);
    }

    public static int find(int v){
        if(parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);
        if(parent_a != parent_b){
            if(parent_a < parent_b){
                parent[parent_b] = parent_a;
                count[parent_a] += count[parent_b];
            }
            else{
                parent[parent_a] = parent_b;
                count[parent_b] += count[parent_a];
            }
        }
    }
}
