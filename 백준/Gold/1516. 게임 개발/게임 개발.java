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

        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n+1];
        int[] input = new int[n+1];
        int[] result = new int[n+1];
        ArrayList<Integer>[] nodelist = new ArrayList[n+1];

        for(int i = 1; i<n+1; i++){
            nodelist[i] = new ArrayList<>();
        }

        for(int i = 1; i<n+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cost[i] = Integer.parseInt(st.nextToken());
            while(true){
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp == -1) break;
                input[i] ++;
                nodelist[tmp].add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i<n+1; i++){
            if(input[i] == 0) queue.add(i);
        }

        StringBuilder sb = new StringBuilder();

        while(!queue.isEmpty()){
            int tmp = queue.poll();

            for(int i = 0; i<nodelist[tmp].size(); i++){
                int next = nodelist[tmp].get(i);
                input[next] --;
                result[next] = Math.max(result[next], result[tmp] + cost[tmp]);
                if(input[next] == 0) queue.add(next);
            }
        }

        for(int i = 1; i<n+1; i++){
            sb.append(result[i] + cost[i] + "\n");
        }

        System.out.println(sb);
    }
}