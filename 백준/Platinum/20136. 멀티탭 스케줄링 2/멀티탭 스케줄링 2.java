import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] electronic = new int[k+1];
        Queue<Integer> queue[] = new LinkedList[k+1];
        for (int i = 0; i < k+1; i++) {
            queue[i] = new LinkedList<>();
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<k+1; i++){
            electronic[i] = Integer.parseInt(st.nextToken());
            queue[electronic[i]].add(i);
        }

        PriorityQueue<int[]> multitap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        boolean[] use = new boolean[k+1];
        int count = 0;
        int now = 0;

        for(int i = 1; i<k+1; i++){
            int cur = electronic[i];
            if(use[cur]){
                queue[cur].poll();
                if(queue[cur].isEmpty()) multitap.add(new int[]{cur, Integer.MAX_VALUE});
                else multitap.add(new int[]{cur, queue[cur].peek()});
            }
            else if(now < n){
                use[cur] = true;
                queue[cur].poll();
                now ++;
                if(queue[cur].isEmpty()) multitap.add(new int[]{cur, Integer.MAX_VALUE});
                else multitap.add(new int[]{cur, queue[cur].peek()});
            }
            else{
                int out = multitap.peek()[0];
                multitap.poll();
                use[out] = false;
                queue[cur].poll();
                if(queue[cur].isEmpty()) multitap.add(new int[]{cur, Integer.MAX_VALUE});
                else multitap.add(new int[]{cur, queue[cur].peek()});
                use[cur] = true;
                count ++;
            }
        }

        System.out.println(count);
    }
}