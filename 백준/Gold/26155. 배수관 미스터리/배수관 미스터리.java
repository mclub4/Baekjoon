import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] size;

    public static class Edge implements Comparable<Edge>{
        int start;
        int end;
        double cost;

        public Edge(int start, int end, double cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.cost > o.cost) return -1;
            else if(this.cost <o.cost) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        size = new int[n+1];

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
            size[i] = 1;
        }

        // 확률이 높은 순으로 배수관 정보 정렬
        ArrayList<Edge> nodelist = new ArrayList<>();
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            double cost = Double.parseDouble(st.nextToken());
            nodelist.add(new Edge(start, end, cost));
        }

        Collections.sort(nodelist);

        ArrayList<double[]> query = new ArrayList<>();

        // 확률이 높은 순으로 질문을 정렬, 그리고 다시 질문에 대한 답을 재배열 하기 위해서 질의 번호 저장
        int q = Integer.parseInt(br.readLine());
        for(int i = 0; i<q; i++){
            double percent = Double.parseDouble(br.readLine());
            query.add(new double[]{percent, i});
        }

        Collections.sort(query, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                if(o1[0] > o2[0]) return -1;
                else if(o1[0] < o2[0]) return 1;
                else return 0;
            }
        });

        int[] answer = new int[q];
        int start = 0;

        // 각 질문에 대해서 모든 배수구 정보를 돌려본다
        // 아무것도 없는 상태에서 이을 수 있는 큰 것부터 차례로 이어본다 (오프라인 쿼리)
        for(int i = 0; i<query.size(); i++){
            // 각 질문의 확률
            double standard = query.get(i)[0];

            // 각 질문의 문제 번호
            double seq = query.get(i)[1];

            for(int j = start; j<nodelist.size(); j++){
                Edge now = nodelist.get(j);
                // 우리가 배수관 정보를 확률이 큰 순으로 정렬했으므로 더 작은게 오면 더이상 볼 필요가 없다
                if(now.cost < standard) break;
                if(find(now.start) != find(now.end)){
                    union(now.start, now.end);
                    n--;
                }
                start++;
            }

            answer[(int)seq] = n;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<answer.length; i++){
            sb.append(answer[i] + "\n");
        }

        System.out.println(sb);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a == parent_b) return;
        if(parent_a < parent_b){
            parent[parent_b] = parent_a;
        }
        else{
            parent[parent_a] = parent_b;
        }
    }


}