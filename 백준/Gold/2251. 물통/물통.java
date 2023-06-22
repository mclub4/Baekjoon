import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        visited = new boolean[a+1][b+1][c+1];
        ArrayList<Integer> answer = bfs(a,b,c);

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();

        for(int tmp : answer){
            sb.append(tmp + " ");
        }

        System.out.println(sb);
    }

    public static ArrayList<Integer> bfs(int a, int b, int c) {
        ArrayList<Integer> water = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, c});

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int aw = tmp[0];
            int bw = tmp[1];
            int cw = tmp[2];
            
            if(visited[aw][bw][cw]) continue;
            visited[aw][bw][cw] = true;

            if(aw == 0 && !water.contains(cw)) water.add(cw);

            if(aw != 0){
                //a->b
                if(aw+bw<=b) queue.add(new int[]{0,aw+bw,cw});
                else queue.add(new int[]{aw-b+bw, b, cw});
                //a->c
                if(aw+cw<=c) queue.add(new int[]{0,bw,aw+cw});
                else queue.add(new int[]{aw-c+cw, bw, c});
            }
            if(bw != 0){
                //b->a
                if(bw+aw<=a) queue.add(new int[]{bw+aw,0,cw});
                else queue.add(new int[]{a, bw-a+aw, cw});
                //b->c
                if(bw+cw<=c) queue.add(new int[]{aw,0,bw+cw});
                else queue.add(new int[]{aw, bw-c+cw, c});
            }
            if(cw != 0){
                //c->a
                if(cw+aw<=a) queue.add(new int[]{cw+aw,bw,0});
                else queue.add(new int[]{a, bw, cw-a+aw});
                //c->b
                if(cw+bw<=b) queue.add(new int[]{aw,0,cw+bw});
                else queue.add(new int[]{aw, b, cw-b+bw});
            }
        }
        return water;
    }
}