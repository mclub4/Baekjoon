import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLType;
import java.util.*;

public class Main {
    static int k;
    static int[] route;
    static int[] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        route = new int[100003];
        time = new int[100003];

        if(n == k){
            System.out.println(0);
            System.out.println(k);
        }
        else{
            System.out.println(bfs(n));
            Stack<Integer> stack = new Stack<>();
            while(true){
                stack.add(k);
                if(k == n) break;
                k = route[k];
            }

            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()){
                sb.append(stack.pop() + " ");
            }

            System.out.println(sb);
        }
    }

    public static int bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();
            int now = time[cur];

            if(cur == k) return time[cur];

            if(cur+1 <= 100002 && time[cur + 1] == 0){
                queue.add(cur+1);
                route[cur+1] = cur;
                time[cur+1] = now + 1;
            }
            if(cur-1 >=0  &&  time[cur - 1] == 0){
                queue.add(cur-1);
                route[cur-1] = cur;
                time[cur-1] = now + 1;
            }
            if(cur*2 <= 100002 &&  time[cur*2] == 0){
                queue.add(cur*2);
                route[cur*2] = cur;
                time[cur*2] = now + 1;
            }
        }

        return -1;
    }
}