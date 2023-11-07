import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static Stack<String> stack;
    static int[] dirx = {-2,-2,-1,1,2,2,1,-1};
    static int[] diry = {-1,1,2,2,1,-1,-2,-2};
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());


        visited = new boolean[n+1][n+1];
        if((x-1)>=0 && (x-1)<n && (y-1)>=0 && (y-1)<n){
            visited[x][y] = true;
            stack = new Stack<>();
            if(dfs((x-1),(y-1),1)){
                StringBuilder sb = new StringBuilder();
                sb.append(x + " " + y + "\n");
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                System.out.println(sb);
            }
            else System.out.println(-1 + " " + -1);
        }
        else System.out.println(-1 + " " + -1);
    }

    public static boolean dfs(int x, int y, int depth){
        if(depth == ((n-1)*(n-1))){
            return true;
        }

        int minVal = 8;
        int minIdx = -1;

        for(int i = 0; i<8; i++){
            int nx = x + dirx[i];
            int ny = y + diry[i];
            int val = 0;

            if(check(nx, ny)){
                for(int j = 0; j<8; j++){
                    int tx = nx + dirx[j];
                    int ty = ny + diry[j];

                    if(check(tx, ty)) val++;
                }

                if(minVal > val){
                    minVal = val;
                    minIdx = i;
                }
            }
        }

        if(minIdx != -1){
            int nx = x + dirx[minIdx];
            int ny = y + diry[minIdx];

            if(check(nx, ny)){
                visited[x][y] = true;
                if(dfs(nx, ny, depth+1)){
                    stack.add((nx+1) + " " + (ny+1) + "\n");
                    return true;
                }
                visited[x][y] = false;
            }
        }

        return false;
    }

    public static boolean check(int x, int y){
        if(x>=0 && x<n && y>=0 && y<n){
            if(visited[x][y] == false){
                return true;
            }
        }
        return false;
    }
}