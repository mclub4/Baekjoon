import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String[][] graph;
    static boolean[][] visited;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static int result;
    static int result2;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        result = 0;
        result2 = 0;
        graph = new String[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i<n; i++){
            String[] st = (br.readLine()).split("");
            for(int j = 0; j<n; j++){
                graph[i][j] = st[j];
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(visited[i][j] == false){
                    bfs(i, j, false);
                }
            }
        }

        visited = new boolean[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(visited[i][j] == false){
                    bfs(i, j, true);
                }
            }
        }

        System.out.println(result + " " + result2);
    }

    public static void bfs(int i, int j, boolean disabled){
        Queue<int[]> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.add(new int[]{i,j});

        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            String value = graph[tmp[0]][tmp[1]];
            for(int k = 0; k<4; k++){
                int x = tmp[0]+dirx[k];
                int y = tmp[1]+diry[k];
                if(x<0 || x>=n || y<0 || y>=n) continue;
                if(!disabled){
                    if(graph[x][y].equals((value)) & visited[x][y] == false) {
                        queue.add(new int[]{x,y});
                        visited[x][y] = true;
                    }
                }
                else{
                    if(value.equals("R")){
                        if((graph[x][y].equals((value)) || graph[x][y].equals("G")) & visited[x][y] == false) {
                            queue.add(new int[]{x,y});
                            visited[x][y] = true;
                        }
                    }
                    else if(value.equals("G")){
                        if((graph[x][y].equals((value)) || graph[x][y].equals("R")) & visited[x][y] == false) {
                            queue.add(new int[]{x,y});
                            visited[x][y] = true;
                        }
                    }
                    else{
                        if(graph[x][y].equals((value)) & visited[x][y] == false) {
                            queue.add(new int[]{x,y});
                            visited[x][y] = true;
                        }
                    }
                }
            }
        }

        if(!disabled) result ++;
        else result2++;
    }
}