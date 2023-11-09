import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        bfs(123456789);
        int t = Integer.parseInt(br.readLine());
        while(t != 0) {
            br.readLine();
            String init = "";
            for(int i = 0; i<3; i++){
                char[] tmp = br.readLine().toCharArray();
                for(int j = 0; j<3; j++){
                    if(tmp[j] == '#') init += "9";
                    else init += tmp[j];
                }
            }
            int key = Integer.parseInt(init);
            if(map.containsKey(key)){
                map.get(key);
                sb.append(map.get(key) + "\n");
            }
            else sb.append("impossible\n");
            t--;
        }

        System.out.println(sb);
    }

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        map.put(start, 0);
        queue.add(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();
            String pos = String.valueOf(cur);
            int zero = pos.indexOf('9');
            int x = zero/3;
            int y = zero%3;

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if(nx<0 || ny<0 || nx>=3 || ny>=3) continue;

                int npos = nx*3 + ny;
                char tmp = pos.charAt(npos);

                String next = pos.replace(tmp, 't');
                next = next.replace('9', tmp);
                next = next.replace('t', '9');

                int key = Integer.parseInt(next);

                if(!map.containsKey(key)){
                    queue.add(key);
                    map.put(key, map.get(cur) + 1);
                }
            }
        }
    }
}