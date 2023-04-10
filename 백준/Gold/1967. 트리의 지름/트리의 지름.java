import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static class Node{
        int end;
        int cost;

        public Node(int end, int cost){
            this.end = end;
            this.cost = cost;
        }
    }

    static int n;
    static ArrayList<Node>[] nodelist;
    static boolean visited[];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        nodelist = new ArrayList[n+1];

        for(int i = 0; i<n+1; i++){
            nodelist[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodelist[start].add(new Node(end, cost));
            nodelist[end].add(new Node(start, cost));
        }

        for(int i=0; i<n; i++){
            visited = new boolean[n+1];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.print(result);
    }

    public static void dfs(int start, int sum){
            result = Math.max(sum, result);

            for(int i = 0; i<nodelist[start].size(); i++){
                int tmp = nodelist[start].get(i).end;
                if(!visited[tmp]){
                    visited[tmp] = true;
                    dfs(tmp, nodelist[start].get(i).cost + sum);
                    visited[tmp] = false;
                }
            }
    }

}