import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] edge;
    static boolean[] visited;
    static boolean[] cycle;
    static int[] distance;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        edge = new ArrayList[n+1];

        for(int i = 1; i<n+1; i++){
            edge[i] = new ArrayList<>();
        }

        cycle = new boolean[n+1];
        visited = new boolean[n+1];

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[a].add(b);
            edge[b].add(a);
        }

        for(int i = 1; i<n+1; i++){
            if(checkCycle(i,-1, i)) break;
            else visited = new boolean[n+1];
        }

        distance = new int[n+1];

        bfs();

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i<n+1; i++){
            sb.append(distance[i] + " ");
        }

        System.out.println(sb);

    }

    public static boolean checkCycle(int now, int prev, int start){
        visited[now] = true;

//        System.out.println(now + "의 장대한 모험이 시작된다.");

        for(int i = 0; i<edge[now].size(); i++){
            int next = edge[now].get(i);
            if(!visited[next]){
//                System.out.println(now + "에서" + next + "로 이어지는 숨막히는 승부");
                checkCycle(next, now, start);
//                if(checkCycle(next, now, start)){
//                    cycle[next] = true;
//                    return true;
//                }
            }
            else if(next != prev && next == start){
//                System.out.println(next + "에서 결국 사이클이 걸리고 마는데");
                cycle[next] = true;
                return true;
            }
        }

        return false;
    }

    public static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[n+1];
        for(int i = 1; i<n+1; i++){
            if(cycle[i]){
                visited[i] = true;
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int a = queue.poll();
            for(int i = 0; i<edge[a].size(); i++){
                int b = edge[a].get(i);
                if(!visited[b]){
                    queue.add(b);
                    visited[b] = true;
                    distance[b] = distance[a] + 1;
                }
            }
        }
    }

}
