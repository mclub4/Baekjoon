import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    static int[] route;
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        route = new int[n+1];

        System.out.println(bfs(n));

        StringBuilder sb = new StringBuilder();
        int tmp = 1;
        Stack<Integer> stack = new Stack<>();
        while(true){
            stack.add(tmp);
            tmp = route[tmp];
            if(tmp == 0) break;
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb);
    }

    public static int bfs(int n){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n,0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(cur[0] == 1) return cur[1];

            if(route[cur[0]-1] == 0){
                queue.add(new int[]{cur[0]-1, cur[1] + 1});
                route[cur[0]-1] = cur[0];
            }
            if(cur[0] % 2 == 0 && route[cur[0]/2] == 0){
                queue.add(new int[]{cur[0]/2, cur[1] + 1});
                route[cur[0]/2] = cur[0];
            }
            if(cur[0] % 3 == 0 && route[cur[0]/3] == 0){
                queue.add(new int[]{cur[0]/3, cur[1] + 1});
                route[cur[0]/3] = cur[0];
            }
        }

        return -1;
    }
}