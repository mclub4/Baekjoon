import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int order = 1;
    static int[] discover;
    static ArrayList<int[]> cutedge;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v+1];

        for(int i = 0; i<=v; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        discover = new int[v+1];
        cutedge = new ArrayList<>();

        for(int i = 1; i<=v; i++){
            if(discover[i] == 0){
                dfs(i, 0);
            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(cutedge, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        for(int i = 0; i<cutedge.size(); i++){
            int[] tmp = cutedge.get(i);
            sb.append(tmp[0] + " " + tmp[1] + "\n");
        }
        System.out.println(cutedge.size());
        System.out.println(sb);
    }

    public static int dfs(int v, int prev){
        discover[v] = order++;
        int ret = discover[v];

        for(int now : list[v]){
            if(now == prev) continue;
            if(discover[now] == 0){
                int low = dfs(now, v);
                if(low>discover[v]) cutedge.add(new int[]{Math.min(now, v), Math.max(now, v)});
                ret = Math.min(ret, low);
            }
            else ret = Math.min(ret, discover[now]);
        }

        return ret;
    }
}
