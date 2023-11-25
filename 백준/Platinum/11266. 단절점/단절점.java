import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int order = 1;
    static int[] discover;
    static boolean[] cut;
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
        cut = new boolean[v+1];

        for(int i = 1; i<=v; i++){
            if(discover[i] == 0){
                dfs(i, true);
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=v; i++){
            if(cut[i]){
                cnt++;
                sb.append(i + " ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    public static int dfs(int v, boolean isRoot){
        discover[v] = order++;
        int ret = discover[v];
        int child = 0;

        for(int now : list[v]){
            if(discover[now] == 0){
                child++;
                int low = dfs(now, false);
                if(!isRoot && low>=discover[v]) cut[v] = true;
                ret = Math.min(ret, low);
            }
            else ret = Math.min(ret, discover[now]);
        }

        if(isRoot && child>=2) cut[v] = true;

        return ret;
    }
}
