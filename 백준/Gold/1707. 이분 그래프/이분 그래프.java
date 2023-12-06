import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList <Integer>[] list;
    static boolean[] visited;
    static int[] color;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list = new ArrayList[v+1];
            visited = new boolean[v+1];
            color = new int[v+1];
            for(int i = 0; i<v+1; i++){
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i<e; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list[u].add(y);
                list[y].add(u);
            }

            boolean flag = false;

            for(int i = 1; i<v+1; i++){
                if(!visited[i]){
                    color[i] = 1;
                    if(dfs(i) == false){
                        flag = true;
                        break;
                    }
                }
            }

            if(flag) System.out.println("NO");
            else System.out.println("YES");
        }
    }

    public static boolean dfs(int s){
        visited[s] = true;
        for(int nxt : list[s]){
            if(color[nxt] == color[s]) return false;
            if(visited[nxt]) continue;
            color[nxt] = color[s]*(-1);
            if(dfs(nxt) == false) return false;
        }

        return true;
    }
}