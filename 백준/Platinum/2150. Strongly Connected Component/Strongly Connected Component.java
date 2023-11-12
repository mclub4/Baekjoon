import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> rgraph = new ArrayList<>();
    static int[] check;
    static boolean[] visited;
    static Stack<Integer> stack;

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for(int i = 0; i<=v; i++){
            graph.add(new ArrayList<>());
            rgraph.add(new ArrayList<>());
        }

        for(int i = 0; i<e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            rgraph.get(b).add(a);
        }

        visited = new boolean[v+1];
        stack = new Stack<>();

        for(int i = 1; i<=v; i++){
            if(!visited[i]) dfs(i);
        }

        visited = new boolean[v+1];
        check = new int[v+1];
        int group = 1;

        while(!stack.isEmpty()){
            int cur = stack.pop();

            if(!visited[cur]){
                rdfs(cur, group);
                group++;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int i = 1; i<=v; i++){
            set.add(check[i]);
        }
        System.out.println(set.size());

        StringBuilder sb = new StringBuilder();
        visited = new boolean[v+1];
        for(int i = 1; i<=v; i++){
            if(!visited[i]){
                int tmp = check[i];
                visited[i] = true;
                sb.append(i + " ");
                for(int j = 1; j<=v; j++){
                    if(i == j) continue;

                    if(tmp == check[j]){
                        sb.append(j + " ");
                        visited[j] = true;
                    }
                }
                sb.append(-1 + "\n");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(int v){
        visited[v] = true;

        for(int cur : graph.get(v)){
            if(!visited[cur]) dfs(cur);
        }

        stack.push(v);
    }

    public static void rdfs(int v, int num){
        visited[v] = true;
        check[v] = num;
        
        for(int cur : rgraph.get(v)){
            if(!visited[cur]) rdfs(cur, num);
        }

        stack.push(v);
    }
}