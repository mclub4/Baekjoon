import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] input = new int[n+1];

            st = new StringTokenizer(br.readLine());
            int[] time = new int[n+1];
            int[] result = new int[n+1];
            ArrayList<Integer>[] edge = new ArrayList[n+1];

            for(int i = 1; i<n+1; i++){
                time[i] = Integer.parseInt(st.nextToken());
                edge[i] = new ArrayList<>();
            }

            for(int i = 0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                edge[start].add(end);
                input[end] ++;
            }

            Queue<Integer> queue = new LinkedList<>();
            for(int i = 1; i<n+1; i++){
                if(input[i] == 0){
                    result[i] = time[i];
                    queue.add(i);
                }
            }

            int w = Integer.parseInt(br.readLine());

            while(!queue.isEmpty()){
                int now = queue.poll();
                for(int i = 0; i<edge[now].size(); i++){
                    int next = edge[now].get(i);
                    input[next] --;
                    result[next] = Math.max(result[next], result[now] + time[next]);
                    if(input[next]==0) queue.add(next);
                }
            }

            System.out.println(result[w]);
        }
    }
}