import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n,m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] nodelist = new ArrayList[n+1];
        input = new int[n+1];

        for(int i = 1; i<n+1; i++){
            nodelist[i] = new ArrayList<>();
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            nodelist[start].add(end);
            input[end] ++;
        }

        for(int i = 1; i<n+1; i++){
            if(input[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int tmp = queue.poll();
//            int tmp_next = nodelist[tmp].size();
            sb.append(tmp + " ");
            for(int i = 0; i<nodelist[tmp].size(); i++){
                int next = nodelist[tmp].get(i);
                input[next] --;
                if(input[next] == 0){
                    queue.add(next);
                }
            }
        }

        System.out.println(sb);
    }

}