import java.awt.*;
import java.util.*;

class Solution {
    static int[] dirx = {1, 0, 0, -1};
    static int[] diry = {0, -1, 1, 0};
    static Point start;
    static Point end;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        start = new Point(x-1,y-1);
        end = new Point(r-1,c-1);
        
        String optimal = bfs(n, m, k);
        String answer = "";
        
        if(optimal == null) answer = "impossible";
        else answer = optimal;
        
        return answer;
    }
    
    public static String bfs(int n, int m, int k) {
        Queue<Route> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][k+1];
        String[] route = new String[]{"d", "l", "r", "u"};
        String answer = null;
        queue.add(new Route(start.x, start.y, 0, ""));

        while (!queue.isEmpty()) {
            Route cur = queue.poll();
            if(cur.dist == k){
                if (cur.x == end.x && cur.y == end.y){
                    if(answer == null) answer = cur.route;
                    else answer = differ(answer, cur.route, k); 
                }    
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dirx[i];
                int ny = cur.y + diry[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny][cur.dist]) continue;
                visited[nx][ny][cur.dist] = true;
                queue.add(new Route(nx, ny, cur.dist + 1, cur.route + route[i]));
            }

        }

        return answer;
    }
    
    public static String differ(String str1, String str2, int k){
        for(int i=0;i<k;i++){
            if(str1.charAt(i) > str2.charAt(i)) break;
            else if(str1.charAt(i) < str2.charAt(i)) return str1;
        }
        return str2;
    }
}

class Route {
    int x;
    int y;
    int dist;
    String route;

    public Route(int x, int y, int dist, String route) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.route = route;
    }
}