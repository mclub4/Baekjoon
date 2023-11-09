import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String init = "";
        for(int i = 0; i<3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                init += st.nextToken();
            }
        }

        System.out.println(bfs(init));
    }

    public static int bfs(String start){
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(start, 0);
        queue.add(start);

        String answer = "123456780";

        while(!queue.isEmpty()){
            String pos = queue.poll();
            int zero = pos.indexOf('0');
            int x = zero/3;
            int y = zero%3;

            if(pos.equals(answer)) return map.get(pos);

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if(nx<0 || ny<0 || nx>=3 || ny>=3) continue;

                int npos = nx*3 + ny;
                char tmp = pos.charAt(npos);

                String next = pos.replace(tmp, 't');
                next = next.replace('0', tmp);
                next = next.replace('t', '0');

                if(!map.containsKey(next)){
                    queue.add(next);
                    map.put(next, map.get(pos)+1);
                }
            }
        }

        return -1;
    }
}