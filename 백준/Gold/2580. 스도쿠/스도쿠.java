import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];
    static boolean end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i<9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<9; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void dfs(int depth){
        if(depth == 81){
            end = true;
            return;
        }

        int x = depth/9;
        int y = depth%9;

        if(board[x][y] != 0) dfs(depth+1);
        else{
            for(int i = 1; i<=9; i++){
                if(!check(x,y,i)) continue;
                board[x][y] = i;
                dfs(depth+1);
                if(end) return;
                board[x][y] = 0;
            }
        }
    }

    public static boolean check(int x, int y, int v){
        for(int i = 0; i<9; i++){
            if(board[x][i] == v || board[i][y] == v) return false;
        }

        int nx = (x/3) * 3;
        int ny = (y/3) * 3;

        for(int i = nx; i<nx+3; i++){
            for(int j = ny; j<ny+3; j++){
                if(board[i][j] == v) return false;
            }
        }

        return true;
    }

}
