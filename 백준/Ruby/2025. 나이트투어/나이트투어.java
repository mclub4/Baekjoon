import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static StringBuilder sb;
    static int[][] dirx = {{2, 2, -2, -2, 1, 1, -1, -1}, {1, -1, 1, -1 ,2, -2, 2, -2}, {-1, -1, 1, 1, -2, -2, 2, 2}, {-2, 2, -2, 2, -1, 1, -1, 1},
            {1, 1, 2, 2, -1, -1, -2, -2}, {-2, -2, -1, -1, 2, 2, 1, 1}, {1, 1, -2, -2, -1, -1, 2, 2}, {2, 2, -1, -1, -2, -2, 1, 1},
            {-1, 1, -2, 2, -1, 1, -2, 2}, {2, -2, 1, -1, 2, -2, 1, -1}};

    static int[][] diry = {{1, -1, 1, -1, 2, -2, 2, -2}, {2, 2, -2, -2, 1, 1, -1, -1}, {-2, 2, -2, 2, -1, 1, -1, 1}, {-1, -1, 1, 1, -2, -2, 2, 2},
            {-2, 2, -1, 1, -2, 2, -1, 1}, {1, -1, 2, -2, 1, -1, 2, -2}, {2, -2, 1, -1, 2, -2, 1, -1}, {-1, 1, -2, 2, -1, 1, -2, 2},
            {-2, 2, -1, 1, 2, -2, 1, -1}, {-1, 1, -2, 2, 1, -1, 2, -2}};

    static int n;
    static int idx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        idx = 0;
        while(true){
            if(tour(x-1,y-1)){
                System.out.println(sb);
                break;
            }
            else{
                if(idx == 9){
                    System.out.println(-1 + " " + -1);
                    break;
                }
                idx ++;
            }
        }
    }

    public static boolean check(int x, int y){
        if(x>=0 && x<n && y>=0 && y<n){
            if(visited[x][y] == false){
                return true;
            }
        }
        return false;
    }

    public static int warnsdorff(int x, int y){
        int degree = 0;

        for(int i = 0; i<8; i++){
            int nx = x + dirx[idx][i];
            int ny = y + diry[idx][i];
            if(check(nx,ny)) degree++;
        }

        return degree;
    }

    public static boolean tour(int x, int y){
        int goal = n*n;
        int depth = 1;

        sb = new StringBuilder();
        visited = new boolean[n+1][n+1];
        sb.append((x+1) + " " + (y+1) + "\n");
        visited[x][y] = true;

        while(goal > depth){
            int minVal = 9;
            int minIdx = -1;
            int move = 0;

            for(int i = 0; i<8; i++){
                int nx = x + dirx[idx][i];
                int ny = y + diry[idx][i];

                if(check(nx, ny)){
                    move++;
                    int val = warnsdorff(nx, ny);

                    if(minVal > val){
                        minVal = val;
                        minIdx = i;
                    }
                }
            }

            if(move == 0) return false;

            x += dirx[idx][minIdx];
            y += diry[idx][minIdx];

            visited[x][y] = true;
            sb.append((x+1) + " " + (y+1) + "\n");
            depth++;
        }

        return  true;
    }
}