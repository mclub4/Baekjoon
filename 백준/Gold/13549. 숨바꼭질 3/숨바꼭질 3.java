import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visited = new int[100001][2];
        System.out.println(bfs(n,k));
    }

    public static int bfs(int n, int k){
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        queue.add(new int[]{n,0});

        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int now = tmp[0];
            int time = tmp[1];
            
            if(now == k) return time;

            int[] arr = {now-1, now+1, now*2};

            for(int i = 0; i<3; i++){
                int next = arr[i];
                if(next<0 || next>=100001) continue;
                if(i!=2){
                    if(visited[next][0]==0 || visited[next][1]>time+1){
                        visited[next][0] = 1;
                        visited[next][1] = time+1;
                        queue.add(new int[]{next, time+1});
                    }
                }
                else{
                    if(visited[next][0]==0 || visited[next][1]>time){
                        visited[next][0] = 1;
                        visited[next][1] = time;
                        queue.add(new int[]{next, time});
                    }
                }
            }
        }

        return -1;
    }
}