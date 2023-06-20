import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visited = new boolean[200000];
        System.out.println(bfs(n,k));
    }

    public static int bfs(int n, int k){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n,0});
        visited[n] = true;

        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int now = tmp[0];
            int time = tmp[1];

            if(now == k) return time;

            if(now-1>=0 && !visited[now-1]){
                visited[now-1] = true;
                queue.add(new int[]{now-1, time+1});
            }
            if(now+1<200000 && !visited[now+1]){
                visited[now+1] = true;
                queue.add(new int[]{now+1, time+1});
            }
            if(now*2<200000 && !visited[now*2]){
                visited[now*2] = true;
                queue.add(new int[]{now*2, time+1});
            }
        }

        return -1;
    }
}