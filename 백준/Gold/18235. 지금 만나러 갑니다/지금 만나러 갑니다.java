import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] check;
    static int n;
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        check = new int[n+1];
        System.out.println(bfs(a,b));
    }

    public static int bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a, 0});
        queue.add(new int[]{b, 0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            int jump = (int) Math.pow(2, cur[1]);

            int first = cur[0] + jump;
            int day = cur[1];
            if(first <=n){
                if(check[first] == day + 1){
                    return (day + 1);
                }
                else{
                    check[first] = day + 1;
                    queue.add(new int[]{first, day + 1});
                }
            }

            int second = cur[0] - jump;
            if(second > 0){
                if(check[second] == day + 1){
                    return (day + 1);
                }
                else{
                    check[second] = day + 1;
                    queue.add(new int[]{second, day + 1});
                }
            }
        }
        return -1;
    }
}